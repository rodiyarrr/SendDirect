package com.anirudh.senddirect.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferSession {

    private String shareCode;
    private String senderSessionId;
    private String receiverSessionId;
    private TransferStatus status ;

}
