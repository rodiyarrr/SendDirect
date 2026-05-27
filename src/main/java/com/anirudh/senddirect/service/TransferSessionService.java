package com.anirudh.senddirect.service;

import com.anirudh.senddirect.exceptions.SessionNotFoundException;
import com.anirudh.senddirect.models.TransferSession;
import com.anirudh.senddirect.models.TransferStatus;
import com.anirudh.senddirect.repositories.TransferSessionRepository;
import com.anirudh.senddirect.util.ShareCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransferSessionService {
    private final TransferSessionRepository repository;
    private final ShareCodeGenerator codeGenerator;

    public String createSession(){

        TransferSession session=new TransferSession();
        String code = codeGenerator.generateCode();
        session.setShareCode(code);
        session.setStatus(TransferStatus.WAITING);

        repository.save(session);

        return code;
    }

    public TransferSession joinSession(String shareCode){
        TransferSession session=repository.findByShareCode(shareCode);
        if (session==null){
            throw new SessionNotFoundException("Session Not Found");
        }
        session.setStatus(TransferStatus.CONNECTED);
        repository.save(session);
        return session;
    }

    @Scheduled(fixedDelay = 3600000)
    public void cleanExpiredSessions(){
        repository.deleteOlderThan(Duration.ofHours(2));
    }

}
