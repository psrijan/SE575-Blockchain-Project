package com.blockchain.backend.service;

import com.blockchain.backend.core.Block;
import com.blockchain.backend.core.InmemoryBlockchainCore;
import com.blockchain.backend.dto.BaseResponse;
import com.blockchain.backend.dto.request.AddBlockRequest;
import com.blockchain.backend.dto.request.UpdateBlockRequest;
import com.blockchain.backend.dto.response.BlockResponse;
import com.blockchain.backend.dto.server.ServerDTO;
import com.blockchain.backend.helper.MyModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BlockchainService {

    @Autowired
    private MyModelMapper modelMapper;

    // Configuration to have inmemory blockchain core used. A dummy stub implementation
    // of persisted database can be used by changing the qualifier to db. This is just for
    // demonstration of how the application will be extended.
    @Autowired
    @Qualifier("inmemory")
    private InmemoryBlockchainCore blockchainCore;

    private Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    public BaseResponse addNewBlock(AddBlockRequest blockRequest) {
        logger.debug("Entering Add New Block Service...");
        Block block = new Block(blockchainCore.getIndex(), blockRequest.getData(),
                blockchainCore.getMostRecentHash(), blockRequest.getAttempts(), blockRequest.getDifficulty());
        block.setDifficulty(blockRequest.getDifficulty());
        int index = blockchainCore.addNewBlock(block, blockRequest.getDifficulty(), blockRequest.getAttempts());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        baseResponse.setMessage(String.format("Successfully Added Block with id %d", index));
        return baseResponse;
    }

    public List<BlockResponse> getAllBlocks() {
        logger.debug("Entering Get All Blocks Service...");
        List<Block> blockList = blockchainCore.getAllBlocks();
        if (blockList != null) {
            return IntStream.range(0, blockList.size()).mapToObj(i -> {
                BlockResponse blockResponse = modelMapper.map(blockList.get(i), BlockResponse.class);
                blockResponse.setBlockName(blockList.get(i).getBlockId());
                blockResponse.setIndex(i);
                return blockResponse;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public BaseResponse updateBlocks(Integer id, UpdateBlockRequest updateBlockRequest) {
        logger.debug("Entering Update Block Service...");
        ServerDTO serverDTO = blockchainCore.updateBlock(id, updateBlockRequest.getData(), updateBlockRequest.getAttempts());
        return modelMapper.map(serverDTO, BaseResponse.class);
    }

    public BaseResponse clearBlockchain() {
        logger.debug("Entering Clear Blockchain Service...");
        int clearedSize = blockchainCore.clearBlockchain();
        BaseResponse response = new BaseResponse(true, String.format("Cleared %d Blocks form the Blockchain!", clearedSize));
        return response;
    }
}
