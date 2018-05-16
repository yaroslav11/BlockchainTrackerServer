package edu.spring.dao;

import edu.spring.domain.model.Block;

public interface BlockDao {

    Block get(long id);

    Block insert(Block block);

    void update(Block block);

    void delete(long id);

    long getLatestIndex();
}
