package edu.spring.core;

import java.util.List;

public class MainChain {
	
	public List<Block> blockchain = (new Blockchain()).chain;

	public static void main(String[] args) {
	    //create block chain
        Blockchain blockchain = new Blockchain();

        //add our blocks to the blockchain ArrayList:
        for (int i=1; i<=3; i++){
            System.out.println("Trying to Mine block "+ String.valueOf(i)+ "...");
            blockchain.addBlock((new PrivateData().getHashedData()));
        }

        System.out.println("\nBlockchain is Valid: " + blockchain.isValid());
		String blockchainJson = StringUtil.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);

        System.out.println((Officer.checkGuilty(blockchain, new PrivateData("MIPT Student", "GPS", "Time"))));
	}
	

}
