package com.crypto.backend.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crypto.backend.dto.server.ServerDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * @author Phat Ngo
 * [1.27112020]
 * <p>
 * SE 575 - Software Design
 * Blockchain
 */
@Component
public class BlockchainCore {
    static private ArrayList<Block> blockchain = new ArrayList<Block>();
    /* Set difficulty that must be solved */
    private int difficulty = 5;

    public List<Block> getAllBlocks() {
        isValid();
        return blockchain;
    }

    public String getMostRecentHash() {
        return (blockchain.size()== 0) ? "0" : blockchain.get(blockchain.size() -1).getHash();
    }

    public int getIndex() {
        return blockchain.size();
    }

    /**
     * Returns index value and -1 if there is a vailure
     *
     * @param block
     * @return
     */
    public int addNewBlock(Block block, String difficulty) {
        Long startTime = System.currentTimeMillis();
        this.difficulty = difficulty.length();
        block.mineBlock(difficulty, false);
        blockchain.add(block);
        long endTime = System.currentTimeMillis();
        block.setExecutionTime(endTime - startTime);
        return blockchain.size() - 1;
    }

    /* Method to verify/compare hashes of the the blocks in the chain
     * Returns whether or not the chain is valid and untampered
     */
    public Boolean isValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        boolean isBlockChainValid = true;
        /* Checks the hashes by looping through the blockchain */
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            /* If-Statements to compare the hashes*/
            //Current Hash vs. Calculated Hash
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes are not equal");
                isBlockChainValid = false;
                currentBlock.setValid(false);
            }
            //Previous Hash vs.previous Current Hash
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes are not equal");
                isBlockChainValid = false;
                currentBlock.setValid(false);
            }
            /* Checks if Hash has been solved */
            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block is not yet mined");
                isBlockChainValid = false;
                currentBlock.setValid(false);
            }
        }
        return isBlockChainValid;
    }

    public ServerDTO updateBlock(Integer id, String data) {
        int pid;
        Optional<Block> optBlock = blockchain.stream().filter(block -> block.getBlockId().equals(id)).findFirst();
        if (optBlock.isPresent()) {
            Block block = optBlock.get();
            block.setData(data);
            pid = block.getBlockId();
            String parentHash = (id <= 0)? "0": blockchain.get(pid - 1).getHash();
            block.setPreviousHash(parentHash); // there is a remote chance that the parent could have been changed as well. Which would cause the new hash to also mismatch because we will be using the older parents hash.
            block.mineBlock(block.getDifficulty(), true);
            isValid();
            return new ServerDTO(true, "Successfully updated the block" );
        }
        return new ServerDTO(false, String.format("Cannot find the block of ID: %d", id));
    }
}
