package com.anirudh.senddirect.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ShareCodeGenerator {
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final Random random = new Random();

    public String generateCode(){

        StringBuilder code=new StringBuilder();

        for (int i = 0; i < 6; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return code.toString();
    }
}
