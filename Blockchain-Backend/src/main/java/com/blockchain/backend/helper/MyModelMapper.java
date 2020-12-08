package com.blockchain.backend.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Utility to handle mapping of dtos. This extends
 * spring ModelMapper. This way ModelMapper is identified
 * as a spring bean by the container and can be injected.
 */
@Component
public class MyModelMapper extends ModelMapper {
}
