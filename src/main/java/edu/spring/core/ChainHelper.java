package edu.spring.core;

import edu.spring.domain.BlockService;
import edu.spring.domain.model.Block;
import edu.spring.rest.BlockDTO;
import edu.spring.rest.PositionDTO;

import java.security.MessageDigest;

public class ChainHelper {
    private static int difficulty = 4;

    public static Block generateBlockFromPositionDto(BlockService service, PositionDTO positionDTO){
        long lattestIndex = service.getLattestIndex();
        Block block = new Block();
        block.setInfo(positionDTO.getPosition());
        block.setId(lattestIndex+1);
        block.setPrevious_hash(service.get(lattestIndex).getHash());
        return block;
    }

    public static BlockDTO addBlockToChainAndReturnBlockDto(BlockService service, Block block){
        Block blockFromTable = service.create(block);
        BlockDTO dtoBlockFromTable = BlockDTO.toDto(blockFromTable);
        return dtoBlockFromTable;
    }

    public static void mineBlock(Block block){
        String target = getDificultyString(difficulty); //Create a string with difficulty * "0"
        String calculatedHash = calculatedHash(block);
        block.setHash(calculatedHash);
        while(!block.getHash().substring( 0, difficulty).equals(target)) {
            block.setNonce(block.getNonce()+1);
//            calculateHash(block);
            block.setHash(calculatedHash(block));
        }
        return;
    }



    public static Boolean isValid(BlockService service) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = getDificultyString(difficulty);
//        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        long minIndex = 1;
        long maxIndex = service.getLattestIndex();
        if (maxIndex < minIndex) maxIndex = minIndex;

        //loop through blockchain to check hashes:
        for(long i=minIndex+1; i < maxIndex; i++) {
            currentBlock = service.get(i);
            previousBlock = service.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(calculatedHash(currentBlock))){
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPrevious_hash()) ) {
                return false;
            }
            //check if hash is solved
            if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
                return false;
            }
        }
        return true;
    }


    public static Boolean isGuilty(BlockService service, PositionDTO positionDTO){
        String suspectData = positionDTO.getPosition();
        if (!isValid(service)){
            return false;//"Blockchain is compromised (not valid)";
        }

        if (service.checkExistenceByInfo(suspectData)) {
            return true;//"The person seems to be suspect...";
        }

        return false;//"Person is not guilty))";
    }

    //Applies Sha256 to a string and returns the result.
    public static String applySha256(String input){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public static String calculatedHash(Block block) {
        String calculatedhash = applySha256(block.getBlockWithoutHashAsString());
        return calculatedhash;
    }
}
