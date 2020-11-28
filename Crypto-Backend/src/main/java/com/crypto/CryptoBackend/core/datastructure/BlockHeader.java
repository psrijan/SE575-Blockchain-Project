package com.crypto.CryptoBackend.core.datastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlockHeader {
    private String nonce;
    private String parenthash;
    private Date timestamp;
}
