package com.anirudh.senddirect.controllers;

import com.anirudh.senddirect.dto.CreateSessionResponseDTO;
import com.anirudh.senddirect.service.TransferSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionCreateController {
    private final TransferSessionService service;


    @PostMapping("/create")
    public CreateSessionResponseDTO createSession(){
        String code= service.createSession();

        CreateSessionResponseDTO responseDTO=new CreateSessionResponseDTO();
        responseDTO.setShareCode(code);

        return responseDTO;
    }
}
