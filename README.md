# SendDirect

A peer-to-peer file sharing web application built with WebRTC and Spring Boot. Files are transferred directly between browsers — the server is only used for signaling (establishing the connection) and never touches the actual file data.

## Live Demo

[senddirect-production.up.railway.app](https://senddirect-production.up.railway.app)

---

## How It Works

1. **Sender** selects a file and clicks "Send File" — a unique 6-character share code is generated
2. **Receiver** clicks "Receive File" and enters the share code
3. A WebRTC connection is established between the two browsers via a signaling handshake
4. The file is transferred directly peer-to-peer — the Spring Boot server is no longer involved
5. The receiver's browser automatically downloads the file once the transfer completes

```
Sender ──── signaling (STOMP/WebSocket) ────► Spring Boot ────► Receiver
Sender ◄─────────────── WebRTC P2P (direct) ───────────────────► Receiver
```

---

## Tech Stack

**Backend**
- Java 21
- Spring Boot 3
- Spring WebSocket with STOMP protocol (signaling server)
- In-memory session store (`ConcurrentHashMap`)

**Frontend**
- Vanilla HTML, CSS, JavaScript
- WebRTC (`RTCPeerConnection`, `RTCDataChannel`)
- SockJS + STOMP.js (WebSocket client)

**Infrastructure**
- Deployed on Railway (with automatic HTTPS)
- STUN: Google public STUN server
- TURN: Metered.ca (India region — fallback for symmetric NAT)

---

## Architecture

```
┌─────────────────────────────────────────────────────┐
│                   Spring Boot Server                 │
│                                                      │
│  POST /api/session/create  →  generates share code  │
│  GET  /api/session/join    →  validates share code  │
│  /ws  (WebSocket/STOMP)    →  relays WebRTC signals │
│                                                      │
│  SignalController → broadcasts to /topic/session/   │
│  {shareCode} without inspecting message content     │
└─────────────────────────────────────────────────────┘
          ▲ signaling only (offer/answer/ICE)
          │
┌─────────┴──────────┐           ┌────────────────────┐
│   Sender Browser   │◄─────────►│  Receiver Browser  │
│                    │  WebRTC   │                    │
│  createDataChannel │  P2P      │  ondatachannel     │
│  sendFile()        │  direct   │  reassemble Blob   │
│  16 KB chunks      │           │  trigger download  │
└────────────────────┘           └────────────────────┘
```

**Session lifecycle:**
- Sessions stored in `ConcurrentHashMap` (thread-safe, no database needed)
- `@Scheduled` cleanup runs every hour, removes sessions older than 2 hours
- Spring Boot's embedded Tomcat handles concurrent users with a default thread pool of 200 threads

---

## Running Locally

**Prerequisites:** Java 21, Maven

```bash
# Clone the repo
git clone https://github.com/your-username/SendDirect.git
cd SendDirect

# Run the application
mvn spring-boot:run
```

Open `http://localhost:8080` in two browser tabs or two devices on the same network.

---

## Project Structure

```
src/
└── main/
    ├── java/com/anirudh/senddirect/
    │   ├── configs/
    │   │   ├── WebConfig.java          # CORS configuration
    │   │   └── WebSocketConfig.java    # STOMP + SockJS setup
    │   ├── controllers/
    │   │   ├── SessionCreateController.java
    │   │   └── JoinSessionController.java
    │   ├── websocket/
    │   │   └── SignalController.java   # WebRTC signaling relay
    │   ├── service/
    │   │   └── TransferSessionService.java
    │   ├── repositories/
    │   │   └── TransferSessionRepository.java
    │   ├── models/
    │   │   ├── TransferSession.java
    │   │   └── TransferStatus.java
    │   ├── dto/
    │   │   ├── CreateSessionResponseDTO.java
    │   │   └── JoinSessionResponseDTO.java
    │   ├── util/
    │   │   └── ShareCodeGenerator.java
    │   └── exceptions/
    │       └── SessionNotFoundException.java
    └── resources/
        └── static/
            └── index.html              # Frontend (single file)
```

---

## Key Implementation Details

**Why `ConcurrentHashMap` instead of a database?**
Sessions are short-lived (minutes) and only need to exist during the WebRTC handshake. A database would add unnecessary complexity — `ConcurrentHashMap` is thread-safe and sufficient for this use case.

**Why is the server not involved in file transfer?**
Once the WebRTC `RTCDataChannel` is open, data flows directly between browsers. The Spring Boot server only relays the initial SDP offer/answer and ICE candidates — after that it is completely out of the loop.

**How are large files handled?**
Files are sliced into 16 KB chunks. A back-pressure mechanism checks `channel.bufferedAmount` and pauses sending when it exceeds 256 KB, preventing the WebRTC send buffer from overflowing.

**What happens when direct P2P fails?**
When STUN-based NAT traversal fails (e.g. symmetric NAT on corporate/mobile networks), the connection falls back to a TURN relay server (Metered.ca, India region).

---

## Planned Improvements

- [ ] Serve TURN credentials from the backend instead of hardcoding in HTML
- [ ] Docker containerization with multi-stage build
- [ ] Persistent session storage with TTL (Redis)
- [ ] Transfer multiple files in one session
- [ ] QR code for share code

---

## License

MIT
