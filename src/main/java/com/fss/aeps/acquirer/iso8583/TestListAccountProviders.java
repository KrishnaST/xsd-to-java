package com.fss.aeps.acquirer.iso8583;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fss.aeps.AppConfig;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.PayConstant;
import com.fss.aeps.jaxb.PayTrans;
import com.fss.aeps.jaxb.ReqListAccPvd;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.util.Generator;

import reactor.core.publisher.Mono;

@Component
public class TestListAccountProviders implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TestListAccountProviders.class);
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private NpciClient npciWebClient;
	
	@Override
	public void run() {
		final ReqListAccPvd request = new ReqListAccPvd();
		final HeadType head = context.getBean(HeadType.class);
		final PayTrans txn = new PayTrans();
		request.setHead(head);
		request.setTxn(txn);
		txn.setId(Generator.newRandomTxnId(appConfig.participationCode));
		txn.setNote("123456088"); 
		txn.setRefId(String.format("%06d", new Random().nextInt(999999)));
		txn.setRefUrl("https://www.npci.org.in/");
		txn.setTs(new Date());
		txn.setType(PayConstant.LIST_ACC_PVD);
		
		
		final Mono<Ack> mono = npciWebClient.listAccountProviders(request);
		mono.subscribe(ack -> {
			//todo	
			logger.info("response of beneficiary transaction sent." + ack);
		});
		
	}
	
}
