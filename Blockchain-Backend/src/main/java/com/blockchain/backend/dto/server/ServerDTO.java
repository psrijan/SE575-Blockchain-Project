package com.blockchain.backend.dto.server;

import com.blockchain.backend.dto.BaseResponse;
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
