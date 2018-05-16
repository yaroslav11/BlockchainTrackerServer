package edu.spring.dao;

import edu.spring.domain.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BlockDaoImpl implements BlockDao {

    private static final String SELECT_ACCOUNT_BY_ID =
            "SELECT * FROM chain WHERE id=:id";
    private static final String UPDATE_ACCOUNT =
            "UPDATE chain SET hash=:hash, nonce=:nonce WHERE id=:id";
    private static final String DELETE_ACCOUNT =
            "DELETE FROM chain WHERE id=:id";
    private static final String LATEST_INDEX =
            "SELECT MAX(id) FROM chain ";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcOperations namedJdbcTemplate;

    @Autowired
    public BlockDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcOperations namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Block get(long id) {
        return namedJdbcTemplate.query(
                SELECT_ACCOUNT_BY_ID, Collections.singletonMap("id", id), (resultSet, i) -> {
                    final long id1 = resultSet.getLong("id");
                    final String hash = resultSet.getString("hash");
                    final String previous_hash = resultSet.getString("previous_hash");
                    final String info = resultSet.getString("info");
                    final long nonce = resultSet.getLong("nonce");

                    return new Block(id1, hash, previous_hash, info, nonce);
                }
        ).stream().findFirst().orElse(null);
    }

    @Override
    public Block insert(Block block) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", block.getId());
        params.put("hash", block.getHash());
        params.put("previous_hash", block.getPrevious_hash());
        params.put("info", block.getInfo());
        params.put("nonce", block.getNonce());

        long newId = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("chain")
//                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params).longValue();
        block.setId(newId);
        return block;
    }

    @Override
    public void update(Block block) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", block.getId());
        params.put("hash", block.getHash());
        params.put("previous_hash", block.getPrevious_hash());
        params.put("info", block.getInfo());
        params.put("nonce", block.getNonce());
        namedJdbcTemplate.update(UPDATE_ACCOUNT, params);
    }

    @Override
    public void delete(long id) {
        namedJdbcTemplate.update(DELETE_ACCOUNT, Collections.singletonMap("id", id));
    }

    @Override
    public long getLatestIndex() {

        return namedJdbcTemplate.query(
                LATEST_INDEX, (resultSet, i) -> resultSet.getLong("id")
        ).stream().findFirst().orElse(0L);
    }
}

