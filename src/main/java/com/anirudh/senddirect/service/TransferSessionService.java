package com.anirudh.senddirect.service;

import com.anirudh.senddirect.models.TransferSession;
import com.anirudh.senddirect.models.TransferStatus;
import com.anirudh.senddirect.repositories.TransferSessionRepository;
import com.anirudh.senddirect.util.ShareCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
