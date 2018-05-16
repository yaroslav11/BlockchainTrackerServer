package edu.spring.domain;

import edu.spring.dao.BlockDao;
import edu.spring.domain.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BlockService {

    private final BlockDao dao;

    @Autowired
    public BlockServiceImpl(BlockDao dao) {
        this.dao = dao;
    }

    @Override
    public Block get(long id) {
        return dao.get(id);
    }

    @Override
    public Block create(Block block) {
        return dao.insert(block);
    }

    @Override
    public void delete(long accountId) {
        dao.delete(accountId);
    }

    @Override
    public long getLattestIndex() {
        return dao.getLatestIndex();
    }
}
