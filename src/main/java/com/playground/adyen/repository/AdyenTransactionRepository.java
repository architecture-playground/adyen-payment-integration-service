package com.playground.adyen.repository;

import com.playground.adyen.model.AdyenTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdyenTransactionRepository extends JpaRepository<AdyenTransaction, UUID> {
}
