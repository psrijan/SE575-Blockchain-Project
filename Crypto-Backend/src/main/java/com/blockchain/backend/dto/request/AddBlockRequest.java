package com.crypto.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddBlockRequest {
    public String data;
    public String difficulty;
    public int attempts;
}
