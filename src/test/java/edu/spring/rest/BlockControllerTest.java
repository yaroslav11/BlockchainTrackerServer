package edu.spring.rest;

import edu.spring.domain.BlockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BlockController.class)
public class BlockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService service;

    @Test
    public void simple404Test() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().is4xxClientError());
    }

    // TODO*: add tests here
}
