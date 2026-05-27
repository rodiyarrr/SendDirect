package com.anirudh.senddirect.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferSession {
    private Instant createdAt=Instant.now();
    private String shareCode;
    private String senderSessionId;
    private String receiverSessionId;
    private TransferStatus status ;

}
