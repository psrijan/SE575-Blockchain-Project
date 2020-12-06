package com.crypto.backend.core;

import com.crypto.backend.helper.SHA256Utility;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Phat Ngo
 * [1.27112020]
 *
 * SE 575 - Software Design
 * Blockchain
 *
 */

@Getter
@Setter
public class Block
{
	/* Block Details */
	private String hash;
	private String previousHash;
	private Integer blockId;
	private String data;
	private long timestamp;
	private int nonce;
	private Long executionTime;
	private boolean isValid;

	public Block() {

	}

	public Block(String data) {
		this.data = data;
	}


	/* Block Constructor */
	public Block(Integer id, String data, String previousHash)
	{
		this.blockId = id;
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	/* Function to calculate the hash with Block Details
	 * Calls on the SHA256Utility class to calculate
	 */
	public String calculateHash()
	{
		String calculatehash = SHA256Utility.applySHA256(previousHash + Long.toString(timestamp) + Integer.toString(nonce) + "Block" + getBlockId() + data);
		return calculatehash;
	}

	/* Method to mine the blocks and adds the number of '0's based on set difficulty
	 */
	public void mineBlock(String difficultyString, int limit)
	{
		int difficulty = difficultyString.length();
		String target = new String(new char[difficulty]).replace('\0', '0');
		System.out.println("Target: " + target);
		while(nonce < limit && !hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		isValid = true;
	}
}
