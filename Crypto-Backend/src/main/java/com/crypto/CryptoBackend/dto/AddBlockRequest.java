package com.crypto.CryptoBackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddBlockRequest {
    public String data;
    public String difficulty;
    public long attempts;
}
