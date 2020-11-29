package com.crypto.CryptoBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockResponse {
    private int index;
    private String hash;
    private String previousHash;
    private String blockName;
    private String data;
    private long timestamp;
    private int nonce;
}
