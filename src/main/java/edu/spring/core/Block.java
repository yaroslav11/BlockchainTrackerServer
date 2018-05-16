package edu.spring.core;

public class Block {

	private String hash;
	private String previousHash;
	private String info;
	private int nonce;
	
	//Block Constructor.
	public Block(String info, String previousHash ) {
		this.info = info;
		this.previousHash = previousHash;

//		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
		        previousHash + info + Integer.toString(nonce)
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		hash = calculateHash();
        while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getInfo() {
        return info;
    }
}
