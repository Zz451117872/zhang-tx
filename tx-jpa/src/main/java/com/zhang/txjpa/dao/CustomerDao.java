package com.zhang.txjpa.dao;

import com.zhang.txjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer , Integer > {
}
