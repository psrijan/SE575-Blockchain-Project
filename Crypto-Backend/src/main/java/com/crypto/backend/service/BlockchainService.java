package com.crypto.backend.service;

import com.crypto.backend.core.Block;
import com.crypto.backend.core.BlockchainCore;
import com.crypto.backend.dto.*;
import com.crypto.backend.dto.request.AddBlockRequest;
import com.crypto.backend.dto.request.UpdateBlockRequest;
import com.crypto.backend.dto.response.AddBlockResponse;
import com.crypto.backend.dto.response.BlockResponse;
import com.crypto.backend.dto.response.UpdateBlockResponse;
import com.crypto.backend.helper.MyModelMapper;
import com.crypto.backend.repository.IBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BlockchainService {

    @Autowired
    private IBaseRepository baseRepository;

    @Autowired
    private MyModelMapper modelMapper;

    @Autowired
    private BlockchainCore blockchainCore;

    private Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    public AddBlockResponse addNewBlock(AddBlockRequest blockRequest) {
        logger.debug("Entering Add New Block Service...");
        Block block = new Block(blockchainCore.getIndex(), blockRequest.getData(), blockchainCore.getMostRecentHash());
        int index = blockchainCore.addNewBlock(block, blockRequest.getDifficulty());
        AddBlockResponse response = modelMapper.map(block, AddBlockResponse.class);
        response.setSuccess(index != -1);
        response.setIndex(index);
        return response;
    }

    public List<BlockResponse> getAllBlocks() {
        logger.debug("Entering Get All Blocks Service...");
        List<Block> blockList = blockchainCore.getAllBlocks();
        if (blockList != null) {
            List<BlockResponse> responses = IntStream.range(0, blockList.size()).mapToObj(i -> {
                            BlockResponse blockResponse = modelMapper.map(blockList.get(i), BlockResponse.class);
                            blockResponse.setIndex(i);
                            return blockResponse;
                            }).collect(Collectors.toList());
            return responses;
        }
        return new ArrayList<>();
    }

    public BaseResponse isBlockchainValid() {
        logger.debug("Entering Is Block Valid Service...");
        boolean valid = blockchainCore.isValid();
        String msg = valid? "Blockchain is Valid" : "Invalid Blockchain!";
        BaseResponse baseResponse = new BaseResponse(valid, msg);
        return baseResponse;
    }

    public UpdateBlockResponse updateBlocks (Integer id, UpdateBlockRequest updateBlockRequest) {
        logger.debug("Entering Update Block Service...");
        Block curBlock = new Block(updateBlockRequest.getData());
        Block updatedBlock = blockchainCore.updateBlock(id, curBlock);
        return new UpdateBlockResponse();
    }
}
