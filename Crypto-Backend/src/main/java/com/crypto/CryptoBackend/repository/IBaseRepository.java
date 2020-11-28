package com.crypto.CryptoBackend.repository;

import com.crypto.CryptoBackend.entities.BaseMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository extends CrudRepository<BaseMessage, Long> {
}
