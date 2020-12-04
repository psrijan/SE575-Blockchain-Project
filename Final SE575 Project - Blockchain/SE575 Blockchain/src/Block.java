import java.util.Date;

/**
 * [1.27112020]
 * 
 * SE 575 - Software Design
 * Blockchain
 *  
 */

public class Block 
{
	/* Block Details */
	public String hash;
	public String previousHash;
	private String blockName;
	private String data;
	private long timestamp;
	private int nonce;
	
	
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
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!hash.substring(0, difficulty).equals(target))
		{
			nonce++;
			
			hash = calculateHash();
		}
		
		System.out.println("Block Mined : " + hash);
		
	}
}
