package com.crypto.CryptoBackend.helper;

import com.crypto.CryptoBackend.dto.BaseMessageDTO;
import com.crypto.CryptoBackend.entities.BaseMessage;

public class BaseMessageHelper {

    public static BaseMessageDTO convertBaseMessageToDTO(BaseMessage baseMessage) {
        BaseMessageDTO baseMessageDTO = new BaseMessageDTO();
        baseMessageDTO.setMessage(baseMessage.getRawMessage());
        return baseMessageDTO;
    }
}
