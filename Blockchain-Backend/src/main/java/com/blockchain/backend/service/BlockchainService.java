package com.blockchain.backend.service;

import com.blockchain.backend.core.Block;
import com.blockchain.backend.dto.BaseResponse;
import com.blockchain.backend.dto.request.AddBlockRequest;
import com.blockchain.backend.dto.response.BlockResponse;
import com.blockchain.backend.core.InmemoryBlockchainCore;
import com.blockchain.backend.dto.request.UpdateBlockRequest;
import com.blockchain.backend.dto.server.ServerDTO;
import com.blockchain.backend.helper.MyModelMapper;
import com.blockchain.backend.repository.IBaseRepository;
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
    private IBaseRepository baseRepository;

    @Autowired
    private MyModelMapper modelMapper;

    @Autowired
    @Qualifier("inmemory")
    private InmemoryBlockchainCore blockchainCore;

    private Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    public BaseResponse addNewBlock(AddBlockRequest blockRequest) {
        logger.debug("Entering Add New Block Service...");
        if (blockRequest.getData().equals("hello"))
           throw new RuntimeException();
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

    public BaseResponse deleteBlocks() {
        logger.debug("Entering Delete Block Service...");
        blockchainCore.deleteBlocks();
        return new BaseResponse(true, "Successfully Cleared Blockchain");
    }

    public BaseResponse isBlockchainValid() {
        logger.debug("Entering Is Block Valid Service...");
        boolean valid = blockchainCore.isValid();
        String msg = valid? "Blockchain is Valid" : "Invalid Blockchain!";
        BaseResponse baseResponse = new BaseResponse(valid, msg);
        return baseResponse;
    }

    public BaseResponse updateBlocks (Integer id, UpdateBlockRequest updateBlockRequest) {
        logger.debug("Entering Update Block Service...");
        ServerDTO serverDTO = blockchainCore.updateBlock(id, updateBlockRequest.getData(), updateBlockRequest.getAttempts());
        return modelMapper.map(serverDTO, BaseResponse.class);
    }
}
