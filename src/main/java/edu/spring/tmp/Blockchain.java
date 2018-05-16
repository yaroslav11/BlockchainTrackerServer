package edu.spring.tmp;

import java.util.LinkedList;

public class Blockchain {
    LinkedList<Block> chain = new LinkedList<Block>();
    private final int difficulty = 1;

    public void addBlock(String data){
        String latestHash = this.getLattestHash();
        Block block = new Block(data, latestHash);
        this.insertBlock(block);
    }

    private void insertBlock(Block block) {
        block.mineBlock(difficulty);
        chain.add(block);
    }

    private String getLattestHash(){
        if (this.chain.size() > 0) {
            return this.chain.getLast().getHash();
        }else{
            return "0";
        }
    }

    public Boolean isValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }
}
