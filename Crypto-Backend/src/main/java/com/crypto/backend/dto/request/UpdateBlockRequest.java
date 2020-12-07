package com.crypto.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateBlockRequest {
    private String data;
    private int limit;
}
