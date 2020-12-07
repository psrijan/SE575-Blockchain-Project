package com.crypto.backend.core;

import com.crypto.backend.dto.server.ServerDTO;

import java.util.List;

public interface IBlockchainCore {
    List<Block> getAllBlocks();

    String getMostRecentHash();

    int getIndex();

    int addNewBlock(Block block, String difficulty);

    /* Method to verify/compare hashes of the the blocks in the chain
     * Returns whether or not the chain is valid and untampered
     */
    Boolean isValid();

    ServerDTO updateBlock(Integer id, String data);
}
