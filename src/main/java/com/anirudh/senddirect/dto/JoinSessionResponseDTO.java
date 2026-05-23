package com.anirudh.senddirect.dto;

import com.anirudh.senddirect.models.TransferSession;
import com.anirudh.senddirect.models.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinSessionResponseDTO {

    private String shareCode;
    private TransferStatus status;
}
