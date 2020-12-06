package com.crypto.backend.controller;

import com.crypto.backend.dto.BaseResponse;
import com.crypto.backend.dto.request.AddBlockRequest;
import com.crypto.backend.dto.request.UpdateBlockRequest;
import com.crypto.backend.dto.response.BlockResponse;
import com.crypto.backend.helper.MyModelMapper;
import com.crypto.backend.service.BlockchainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(BlockchainController.class);

    @GetMapping()
    private String getStats() {
        log.debug("Get Stat Message...");
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        return "Crypto-Backend Up Since: " + dateFormat.format(startup);
    }

    @GetMapping("blocks")
    private List<BlockResponse> getAllBlocks() {
        log.debug("Get All Blocks...");
        return baseService.getAllBlocks();
    }

    @PostMapping("block/new")
    public BaseResponse addBlock(@RequestBody AddBlockRequest addBlockRequest) {
        log.debug("Add New Blocks... AddBlockRequest: {}", addBlockRequest.toString());
        return baseService.addNewBlock(addBlockRequest);
    }

    @GetMapping("valid")
    public BaseResponse isBlockchainValid() {
        log.debug("Check Block Valid...");
        return baseService.isBlockchainValid();
    }

    @PutMapping("update/block/{id}")
    public BaseResponse updateBlock(@PathVariable("id") Integer blockId, @RequestBody UpdateBlockRequest blockRequest) {
        log.debug("Update Block Response... BlockId: {} Message: {}", blockId, blockRequest);
        return baseService.updateBlocks(blockId, blockRequest);
    }

//    @PostMapping("block/double/spend/{hash}")
//    public BaseResponse doubleSpend(@PathParam("hash") String hash, @RequestBody AddBlockRequest addBlockRequest ) {
//       log.debug("Double Spend Block...");
//    }
}
