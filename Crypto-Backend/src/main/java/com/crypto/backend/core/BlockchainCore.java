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
 * 
 * SE 575 - Software Design
 * Blockchain
 *  
 */
@ApplicationScope
@Component
public class BlockchainCore
{
	private ArrayList<Block> blockchain = new ArrayList<Block>();
	private int index = 0;
	/* Set difficulty that must be solved */
	private int difficulty = 5;
	private String mostRecentHash = "0";

	public List<Block> getAllBlocks() {
	    return blockchain;
	}
	public String getMostRecentHash() {
		return mostRecentHash;
	}

	public int getIndex() {
		return index++;
	}
	/**
	 * Returns index value and -1 if there is a vailure
	 * @param block
	 * @return
	 */
	public int addNewBlock(Block block, String difficulty) {
		Long startTime = System.currentTimeMillis();
		this.difficulty = difficulty.length();
		block.mineBlock(difficulty);
		blockchain.add(block);
		if (!isValid()) {
			blockchain.remove(--index);
			return -1;
		}
		long endTime = System.currentTimeMillis();
		block.setExecutionTime(endTime - startTime);
		index++;
		mostRecentHash = block.hash;
		return index;
	}

	/* Method to verify/compare hashes of the the blocks in the chain
	 * Returns whether or not the chain is valid and untampered
	 */
	public Boolean isValid()
	{
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		boolean isBlockChainValid = true;
		/* Checks the hashes by looping through the blockchain */
		for(int i = 1; i < blockchain.size(); i++)
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);

			/* If-Statements to compare the hashes*/
			//Current Hash vs. Calculated Hash
			if(!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes are not equal");
				isBlockChainValid = false;
				return false;
			}
			//Previous Hash vs.previous Current Hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				System.out.println("Previous Hashes are not equal");
				return false;
			}
			/* Checks if Hash has been solved */
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block is not yet mined");
				return false;
			}
		}
		return true;
	}

	public ServerDTO updateBlock(Integer id, String data) {
		Optional<Block> optBlock = blockchain.stream().filter(block -> block.getBlockId().equals(id)).findFirst();
		if (optBlock.isPresent()) {
			Block block = optBlock.get();
			String blockData = block.getData();
			block.setData(data);
			block.mineBlock("00000");
			boolean isValid = isValid();

			String message = isValid? "Successfully updated the block to the blockchain": "Sorry this combination of data and hash makes the blockchain invalid. Reverting to previous state! ";

					return new ServerDTO(true, ): new ServerDTO(false, "" );
			System.out.println(" THe current Block is " + isValid);
		}
		return null;
	}
}
