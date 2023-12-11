package com.sec.account.repo;

import java.util.List;

import com.sec.account.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {
	
	List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}