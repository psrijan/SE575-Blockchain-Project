package com.crypto.CryptoBackend.controller;

import com.crypto.CryptoBackend.core.Block;
import com.crypto.CryptoBackend.dto.AddBlockRequest;
import com.crypto.CryptoBackend.dto.AddBlockResponse;
import com.crypto.CryptoBackend.dto.BaseResponse;
import com.crypto.CryptoBackend.dto.BlockResponse;
import com.crypto.CryptoBackend.helper.MyModelMapper;
import com.crypto.CryptoBackend.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*") // Allow all origins to talk to this controller
@RequestMapping("crypto")
@RestController
public class BlockchainController {

    @Autowired
    private BlockchainService baseService;

    private static Date startup = new Date();

    @Autowired
    private MyModelMapper modelMapper;

    @GetMapping()
    private String getStats() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        return "Crypto-Backend Up Since: " + dateFormat.format(startup);
    }

    @GetMapping("all")
    private List<BlockResponse> getAllBlocks() {
        return baseService.getAllBlocks();
    }

    @PostMapping("add")
    public AddBlockResponse addBlock(@RequestBody AddBlockRequest addBlockRequest) {
        return baseService.addNewBlock(addBlockRequest);
    }

    @GetMapping("valid")
    public BaseResponse isBlockchainValid() {
        return baseService.isBlockchainValid();
    }

}
