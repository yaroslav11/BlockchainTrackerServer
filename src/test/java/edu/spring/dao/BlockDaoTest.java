package edu.spring.dao;

import edu.spring.domain.model.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockDaoTest {
    @Autowired
    private BlockDao dao;

    @Test
    public void testGet() throws Exception {
        Block block = dao.get(1L);
        assertNotNull(block);
        assertEquals(1L, block.getId());
        assertEquals("1", block.getHash());
        assertEquals("1", block.getPrevious_hash());
        assertEquals("1", block.getInfo());
        assertEquals(1L, block.getNonce());
    }

    @Test
    public void testGetNotExisted() throws Exception {
        assertNull(dao.get(-1L));
    }

//    @Test
//    public void testInsert() throws Exception {
//        Block account = new Block("Jane Doe", 100);
//        long newId = dao.insert(account).getId();
//        Block savedAccount = dao.get(newId);
//        assertNotNull(savedAccount);
//        assertEquals(newId, savedAccount.getId());
//        assertEquals("Jane Doe", savedAccount.getHolder());
//        assertEquals(100, savedAccount.getBalance());
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        Block account = dao.get(1L);
//        account.deposit(100L);
//        account.setHolder("Mike Smith");
//        dao.update(account);
//        Block updatedAccount = dao.get(1L);
//        assertEquals(account.getId(), updatedAccount.getId());
//        assertEquals(account.getHolder(), updatedAccount.getHolder());
//        assertEquals(account.getBalance(), updatedAccount.getBalance());
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        long newId = dao.insert(new Block("Temp", 200L)).getId();
//        dao.delete(newId);
//        Block deleted = dao.get(newId);
//        assertNull(deleted);
//    }
}
