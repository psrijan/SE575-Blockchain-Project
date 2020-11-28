package com.crypto.CryptoBackend.core.datastructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockData {

    private String username;
    private String toUser;
    private Character txnType; //buy/sell
    private String amount;
}
