package com.blockchain.backend.controller;

import com.blockchain.backend.dto.response.BlockResponse;
import com.blockchain.backend.dto.BaseResponse;
import com.blockchain.backend.dto.request.AddBlockRequest;
import com.blockchain.backend.dto.request.UpdateBlockRequest;
import com.blockchain.backend.helper.MyModelMapper;
import com.blockchain.backend.service.BlockchainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*") // Allow all origins to talk to this controller
@RequestMapping("blockchain")
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
        return "Blockchain-Backend Up Since: " + dateFormat.format(startup);
    }

    @GetMapping("blocks")
    private List<BlockResponse> getAllBlocks() {
        log.debug("Get All Blocks...");
        return baseService.getAllBlocks();
    }

    @PostMapping("block")
    public BaseResponse addBlock(@RequestBody AddBlockRequest addBlockRequest) {
        log.debug("Add New Blocks... AddBlockRequest: {}", addBlockRequest.toString());
        return baseService.addNewBlock(addBlockRequest);
    }

    @GetMapping("valid")
    public BaseResponse isBlockchainValid() {
        log.debug("Check Block Valid...");
        return baseService.isBlockchainValid();
    }

    @PutMapping("block/{id}")
    public BaseResponse updateBlock(@PathVariable("id") Integer blockId, @RequestBody UpdateBlockRequest blockRequest) {
        log.debug("Update Block Response... BlockId: {} Message: {}", blockId, blockRequest);
        return baseService.updateBlocks(blockId, blockRequest);
    }

}
