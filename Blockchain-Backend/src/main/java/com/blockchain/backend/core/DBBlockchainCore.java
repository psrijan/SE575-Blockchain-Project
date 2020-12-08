package com.blockchain.backend.core;

import com.blockchain.backend.dto.server.ServerDTO;
import com.blockchain.backend.repository.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
Dummy class to show how a persistent version of the blockchain core would be implemented
This can replace the InMemoryBlockchainCore by having a stereotype annotation changed to db
 */
@Component("db")
public class DBBlockchainCore implements IBlockchainCore {

    @Autowired
    private IBaseRepository repository;

    @Override
    public List<Block> getAllBlocks() {
        return null;
    }

    @Override
    public String getMostRecentHash() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public int addNewBlock(Block block, String difficulty, int limit) {
        return 0;
    }

    @Override
    public Boolean isValid() {
        return null;
    }

    @Override
    public ServerDTO updateBlock(Integer id, String data, int limit) {
        return null;
    }

    @Override
    public int clearBlockchain() {
        return 0;
    }
}
