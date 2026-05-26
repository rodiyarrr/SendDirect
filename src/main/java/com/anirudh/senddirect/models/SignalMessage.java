package com.anirudh.senddirect.models;

import lombok.Data;

@Data
public class SignalMessage {
    private String sender;
    private String shareCode;
    private String content;
    private String type;

}
