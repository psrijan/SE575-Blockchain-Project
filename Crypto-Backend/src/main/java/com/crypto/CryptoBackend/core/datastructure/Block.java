package com.crypto.CryptoBackend.core.datastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Block {
    /* This should technically be Block Header | Still don't know th difference between block and block header */
    private BlockHeader blockHeader;
    private List<BlockData> data;

    private String getParentHash() {
        return blockHeader.getParenthash();
    }

}
