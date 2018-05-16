package edu.spring.rest;

import edu.spring.domain.BlockService;
import edu.spring.domain.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockController {
    private final BlockService service;

    @Autowired
    public BlockController(BlockService service) {
        this.service = service;
    }

    // delete block by id
    @DeleteMapping("/delete_block/{id}")
    public void delete(
        @PathVariable("id") long id
    ) {
        service.delete(id);
    }

    // view block by id
    @RequestMapping(
        value = "/get_block/{id}",
        method = RequestMethod.GET
    )
    public BlockDTO get(
        @PathVariable("id") long id
    ) {
        Block block = service.get(id);
        return BlockDTO.toDto(block);
    }

    // add new block
    @RequestMapping(
        value = "/add_block/",
        method = RequestMethod.POST
    )
    public @ResponseBody
    BlockDTO create(
        @RequestBody BlockDTO dto
    ) {
        Block block = BlockDTO.toDomainObject(dto);
        Block blockWithId = service.create(block);
        return BlockDTO.toDto(blockWithId);
    }


//    @PutMapping("/account/{id}/holder")
//    public void changeMeta(
//        @PathVariable("id") long id,
//        @RequestParam("hash") String hash,
//        @RequestParam("nonce") long nonce
//    ) {
//        service.changeMeta(id, hash, nonce);
//    }

}
