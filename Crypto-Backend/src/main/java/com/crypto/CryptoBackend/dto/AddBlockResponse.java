package com.crypto.CryptoBackend.dto;

import com.crypto.CryptoBackend.entities.BaseMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBlockResponse extends BaseResponse {
    private int index;
    private String hash;
    private String previousHash;
    private String blockName;
    private String data;
    private long timestamp;
    private int nonce;
}
