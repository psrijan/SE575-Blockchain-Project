package com.crypto.CryptoBackend.controller;

import com.crypto.CryptoBackend.dto.AddNewBlockDTO;
import com.crypto.CryptoBackend.dto.BaseMessageDTO;
import com.crypto.CryptoBackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("crypto")
@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    @GetMapping("one")
    public BaseMessageDTO helloWorld() {
       return baseService.getMessage();
    }

    @PostMapping("add")
    public void addBlock(AddNewBlockDTO newBlockDTO) {

    }


}
