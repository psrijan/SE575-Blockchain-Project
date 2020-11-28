import java.util.ArrayList;
import com.google.gson.GsonBuilder;

/**
 * @author Phat Ngo
 * [1.27112020]
 * 
 * SE 575 - Software Design
 * Blockchain
 *  
 */

public class Blockchain 
{
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	
	/* Set difficulty that must be solved */
	public static int difficulty = 5;
	
	
	public static void main(String[] args)
	{
		/* Test Code */
		/*
		Block blockOne = new Block("Block 1", "0");
		System.out.println("Block 1 Hash: " + blockOne.hash);
		
		Block blockTwo = new Block("Block 2", blockOne.hash);
		System.out.println("Block 2 Hash: " + blockTwo.hash);
		
		Block blockThree = new Block("Block 3", blockTwo.hash);
		System.out.println("Block 3 Hash: " + blockThree.hash);
		*/
		
		/* Adds Block Details
		 * Outputs out Mined Blocks
		 */
		blockchain.add(new Block("Block 1", "Hello, I am Block 1", "0"));
		System.out.println("Block 1 mining attempt ...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Block 2", "Block 2, hey", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Block 2 mining attempt ...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Block 3", "Grettings, Block 3 here", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Block 3 mining attempt ...");
		blockchain.get(2).mineBlock(difficulty);
		
		blockchain.add(new Block("Block 4", "Bonjour, Je suis Block 4", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Block 4 mining attempt ...");
		blockchain.get(3).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isValid());
		
		
		/** Outputs the Blocks in the chain in a nice format **/
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe Blockchain: ");
		System.out.println(blockchainJson);
		
	}
	
	
	/* Method to verify/compare hashes of the the blocks in the chain
	 * Returns whether or not the chain is valid and untampered
	 */
	public static Boolean isValid()
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
			if(!currentBlock.hash.equals(currentBlock.calculateHash()))
			{
				System.out.println("Current Hashes are not equal");
				
				return false;
			}
			
			//Previous Hash vs.previous Current Hash
			if(!previousBlock.hash.equals(currentBlock.previousHash))
			{
				System.out.println("Previous Hashes are not equal");
				
				return false;
			}
			
			/* Checks if Hash has been solved */
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget))
			{
				System.out.println("This block is not yet mined");
				
				return false;
			}
		}
		
		
		return true;
	}
}
