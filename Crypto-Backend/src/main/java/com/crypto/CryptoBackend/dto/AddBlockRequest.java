package com.crypto.CryptoBackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddBlockRequest {
    public String hash;
    public String previousHash;
    private String blockName;
    private String data;
    private long timestamp;
    private int nonce;
}
