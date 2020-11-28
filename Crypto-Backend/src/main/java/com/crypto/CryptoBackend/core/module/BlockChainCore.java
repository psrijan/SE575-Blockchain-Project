package com.crypto.CryptoBackend.core.module;

import com.crypto.CryptoBackend.core.datastructure.Block;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BlockChainCore {

    private static final Block genesisBlock = null;

    public void testSha() {
        String hash = DigestUtils.sha256Hex("hello world");
        System.out.println("Hash:" + hash);
    }




}
