package com.zhang.txjta.dao;

 import com.zhang.txjta.domain.Customer;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer > {
}
