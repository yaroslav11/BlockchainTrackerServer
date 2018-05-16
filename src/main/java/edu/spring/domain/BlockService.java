package edu.spring.domain;

import edu.spring.domain.model.Block;

public interface BlockService {

    Block get(long id);

    Block create(Block block);

    void delete (long accountId);

    long getLattestIndex();

    boolean checkExistenceByInfo(String info);
}
