package com.crypto.CryptoBackend.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Phat Ngo
 * [1.27112020]
 * 
 * SE 575 - Software Design
 * Blockchain
 *  
 */
@Component
public class BlockchainCore
{
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static int index = 0;
	/* Set difficulty that must be solved */
	public static int difficulty = 5;

	public static String mostRecentHash = "0";

	public List<Block> getAllBlocks() {
	    return blockchain;
	}

	/**
	 * Returns index value and -1 if there is a vailure
	 * @param block
	 * @return
	 */
	public int addNewBlock(Block block, String difficulty) {
		BlockchainCore.difficulty = difficulty.length();
		block.mineBlock(difficulty);
		blockchain.add(block);
		if (!isValid()) {
			blockchain.remove(index);
			return -1;
		}
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
		
		/* Checks the hashes by looping through the blockchain */
		for(int i = 1; i < blockchain.size(); i++)
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			/* If-Statements to compare the hashes*/
			//Current Hash vs. Calculated Hash
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes are not equal");
				return false;
			}
			//Previous Hash vs.previous Current Hash
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes are not equal");
				return false;
			}
			/* Checks if Hash has been solved */
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block is not yet mined");
				return false;
			}
		}
		return true;
	}
}
