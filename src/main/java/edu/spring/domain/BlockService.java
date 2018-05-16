package edu.spring.domain;

import edu.spring.domain.model.Block;

public interface BlockService {

    Block get(long id);

    Block create(Block block);

//    void changeMeta(long accountId, String hash, long nonce);

    void delete (long accountId);

    long getLattestIndex();
}
