package com.crypto.backend.repository;

import com.crypto.backend.entities.BaseMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository extends CrudRepository<BaseMessage, Long> {
}
