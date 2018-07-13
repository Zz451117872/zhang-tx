package com.zhang.txjmsdb.dao;

import com.zhang.txjmsdb.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aa on 2018/7/13.
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
}
