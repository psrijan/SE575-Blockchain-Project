package com.crypto.CryptoBackend.service;

import com.crypto.CryptoBackend.dto.BaseMessageDTO;
import com.crypto.CryptoBackend.entities.BaseMessage;
import com.crypto.CryptoBackend.helper.BaseMessageHelper;
import com.crypto.CryptoBackend.repository.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    @Autowired
    private IBaseRepository baseRepository;
    private String str;

    public BaseMessageDTO getMessage() {
        BaseMessage b = new BaseMessage();
        b.setRawMessage("Hello World");
        b.setDate(new Date());
        baseRepository.save(b);
        return BaseMessageHelper.convertBaseMessageToDTO(b);
    }
}
