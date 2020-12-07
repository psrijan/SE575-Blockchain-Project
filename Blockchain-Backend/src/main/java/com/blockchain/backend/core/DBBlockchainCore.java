package com.blockchain.backend.core;

import com.blockchain.backend.dto.server.ServerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/*
Dummy class to show how a persistent version of the blockchain core would be implementedf
 */
@Component("db")
public class DBBlockchainCore implements IBlockchainCore {

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
    public void deleteBlocks() {
        return;
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


}
