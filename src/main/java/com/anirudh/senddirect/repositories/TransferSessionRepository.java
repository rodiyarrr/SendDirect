package com.anirudh.senddirect.repositories;

import com.anirudh.senddirect.models.TransferSession;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferSessionRepository {

    private final Map<String,TransferSession> sessions =new ConcurrentHashMap<>();

    public void save(TransferSession session){
        sessions.put(session.getShareCode(),session);
    }

    public TransferSession findByShareCode(String shareCode){
        return sessions.get(shareCode);
    }
}
