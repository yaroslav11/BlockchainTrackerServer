package edu.spring.rest;

import edu.spring.core.ChainHelper;
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

//    // add new block
//    @RequestMapping(
//        value = "/add_block",
//        method = RequestMethod.POST
//    )
//    public @ResponseBody
//    BlockDTO create(
//        @RequestBody BlockDTO dto
//    ) {
//        Block block = BlockDTO.toDomainObject(dto);
//        Block blockFromTable = service.create(block);
//        return BlockDTO.toDto(blockFromTable);
//    }

    // add new block
    @RequestMapping(
            value = "/add_geotag",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public @ResponseBody
    BlockDTO addGeotag(
            @RequestBody PositionDTO positionDTO
    ) {
        Block block = ChainHelper.generateBlockFromPositionDto(service, positionDTO);
        ChainHelper.mineBlock(block);
        BlockDTO blockDTO = ChainHelper.addBlockToChainAndReturnBlockDto(service, block);
        return blockDTO;
    }

    // check validity
    @RequestMapping(
            value = "/check_validity",
            method = RequestMethod.GET
    )
    public String checkChainValidity() {
        return String.valueOf(ChainHelper.isValid(service));
    }

    // check guilty
    @RequestMapping(
            value = "/check_guilty",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public @ResponseBody
    String checkGuilty(
            @RequestBody PositionDTO positionDTO
    ) {
        return ChainHelper.isGuilty(service, positionDTO);
    }


}

/*
* /add_block {    "id":"4",   "hash":"4",   "previous_hash":"4",   "info":"4",   "nonce":"4"   }
* /add_geotag  {   "position":"info" }
* /check_guilty {   "position":"info" }
* */
