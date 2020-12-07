package com.crypto.CryptoBackend.service;

import com.crypto.CryptoBackend.core.Block;
import com.crypto.CryptoBackend.core.BlockchainCore;
import com.crypto.CryptoBackend.dto.AddBlockRequest;
import com.crypto.CryptoBackend.dto.AddBlockResponse;
import com.crypto.CryptoBackend.dto.BaseResponse;
import com.crypto.CryptoBackend.dto.BlockResponse;
import com.crypto.CryptoBackend.helper.MyModelMapper;
import com.crypto.CryptoBackend.repository.IBaseRepository;
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

    public AddBlockResponse addNewBlock(AddBlockRequest blockRequest) {
        String blockName = "Block " + BlockchainCore.index;
        Block block = new Block(blockName, blockRequest.data, BlockchainCore.mostRecentHash);
        int index = blockchainCore.addNewBlock(block, blockRequest.difficulty, blockRequest.attempts);
        AddBlockResponse response = modelMapper.map(block, AddBlockResponse.class);
        response.setSuccess(index != -1);
        response.setIndex(index);
        return response;
    }

    public List<BlockResponse> getAllBlocks() {
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
        boolean valid = blockchainCore.isValid();
        String msg = valid? "Blockchain is Valid" : "Invalid Blockchain!";
        BaseResponse baseResponse = new BaseResponse(valid, msg);
        return baseResponse;
    }
}
