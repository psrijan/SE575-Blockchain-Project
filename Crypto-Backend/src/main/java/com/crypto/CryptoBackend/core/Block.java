package com.crypto.CryptoBackend.core;

import com.crypto.CryptoBackend.helper.SHA256Utility;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	public String hash;
	public String previousHash;
	private String blockName;
	private String data;
	private long timestamp;
	private int nonce;

	public Block() {

	}

	/* Block Constructor */
	public Block(String blockName, String data, String previousHash)
	{
		this.blockName = blockName;
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
		String calculatehash = SHA256Utility.applySHA256(previousHash + Long.toString(timestamp) + Integer.toString(nonce) + blockName + data);
		return calculatehash;
	}

	/* Method to mine the blocks and adds the number of '0's based on set difficulty
	 */
	public void mineBlock(String difficultyString)
	{
		int difficulty = difficultyString.length();
		String target = new String(new char[difficulty]).replace('\0', '0');
		System.out.println("Target: " + target);
		while(!hash.substring(0, difficulty).equals(target)){
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined : " + hash);
	}
}
