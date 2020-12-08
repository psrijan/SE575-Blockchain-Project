package com.blockchain.backend.repository;

import com.blockchain.backend.entities.RequestMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository extends CrudRepository<RequestMessage, Long> {
}
