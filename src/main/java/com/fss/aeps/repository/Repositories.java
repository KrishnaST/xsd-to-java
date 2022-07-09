package com.fss.aeps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fss.aeps.jpa.CbsToNpciResponseCodes;
import com.fss.aeps.jpa.ImpsConfig;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.jpa.ImpsTransactionId;
import com.fss.aeps.jpa.NpciToAcquirerResponseCodes;

@Repository
public class Repositories {

	public interface NpciToAcquirerResponseCodesRepository extends CrudRepository<NpciToAcquirerResponseCodes, String> {
		public List<NpciToAcquirerResponseCodes> findAll();
	}

	public interface CbsToNpciResponseCodesRepository extends CrudRepository<CbsToNpciResponseCodes, String> {
		public List<CbsToNpciResponseCodes> findAll();
	}

	public interface ConfigRepository extends CrudRepository<ImpsConfig, String> {
		public List<ImpsConfig> findAll();
	}

	public interface TransactionRepository extends CrudRepository<ImpsTransaction, ImpsTransactionId> {
		
		public Optional<ImpsTransaction> findTopByCustRefAndOrgIdNotOrderByMsgTsDesc(final String custRef, final String orgId);

		public Optional<ImpsTransaction> findTopByIdTxnIdAndOrgIdOrderByMsgTsDesc(String txnId, String orgId);
		
	}
}
