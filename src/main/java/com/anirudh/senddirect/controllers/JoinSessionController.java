package com.anirudh.senddirect.controllers;

import com.anirudh.senddirect.dto.JoinSessionResponseDTO;
import com.anirudh.senddirect.models.TransferSession;
import com.anirudh.senddirect.service.TransferSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class JoinSessionController {

    private final TransferSessionService service;

    @GetMapping("/join/{shareCode}")
    public JoinSessionResponseDTO joinSession(@PathVariable String shareCode){
        JoinSessionResponseDTO responseDTO=new JoinSessionResponseDTO();

        TransferSession session=service.joinSession(shareCode);
        responseDTO.setShareCode(session.getShareCode());
        responseDTO.setStatus(session.getStatus());

        return responseDTO;
    }
}
