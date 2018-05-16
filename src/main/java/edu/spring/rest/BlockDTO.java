/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */
package edu.spring.rest;

import edu.spring.domain.model.Block;

/**
 * DTO that represents Block
 */
@SuppressWarnings("all")
public class BlockDTO {


    private long id = -1;
    private String hash;
    private String previous_hash;
    private String info;
    private long nonce;

//    private long id = -1;
//    private String holder;
//    private long balance;

    public BlockDTO() {
    }

    public BlockDTO(long id, String hash, String previous_hash, String info, long nonce) {
        this.id = id;
        this.hash = hash;
        this.previous_hash = previous_hash;
        this.info = info;
        this.nonce = nonce;
    }

    //    public BlockDTO(long id, String holder, long balance) {
//        this.id = id;
//        this.holder = holder;
//        this.balance = balance;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public static Block toDomainObject(BlockDTO dto) {
        return new Block(dto.getId(), dto.getHash(),
                dto.getPrevious_hash(), dto.getInfo(), dto.getNonce());
    }

    public static BlockDTO toDto(Block block) {
        return new BlockDTO(block.getId(), block.getHash(),
                block.getPrevious_hash(), block.getInfo(), block.getNonce());
    }
}
