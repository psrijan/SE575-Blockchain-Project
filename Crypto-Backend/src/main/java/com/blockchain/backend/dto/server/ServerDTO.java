package com.crypto.backend.dto.server;

import com.crypto.backend.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServerDTO extends BaseResponse {

    public ServerDTO() {

    }

    public ServerDTO(boolean success, String message) {
        super(success, message);
    }
}
