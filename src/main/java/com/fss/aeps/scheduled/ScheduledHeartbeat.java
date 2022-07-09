package com.fss.aeps.scheduled;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fss.aeps.AppConfig;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HbtMsgType;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.PayConstant;
import com.fss.aeps.jaxb.PayTrans;
import com.fss.aeps.jaxb.ProdType;
import com.fss.aeps.jaxb.ReqHbt;
import com.fss.aeps.jaxb.ReqHbt.HbtMsg;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.util.Generator;

import reactor.core.publisher.Mono;

@Configuration
@EnableScheduling
@ConditionalOnProperty(value = "heartbeat.enabled", havingValue = "true")
public class ScheduledHeartbeat {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledHeartbeat.class);

	@Autowired
	private NpciClient npciWebClient;
	
	@Autowired
	private AppConfig appConfig;

	
	@Scheduled(initialDelayString = "${hearbeat.initialDelay}", fixedDelayString = "${hearbeat.fixedDelay}")
	public void heartbeat() {
		try {
			final String refUrl = "http://npci.org/upi/schema/";

			final ReqHbt hbt = new ReqHbt();
			final HbtMsg hbtMsg = new HbtMsg();
			hbtMsg.setType(HbtMsgType.ALIVE);
			hbtMsg.setValue("NA");

			final HeadType headType = new HeadType();
			headType.setMsgId(Generator.newRandomTxnId(appConfig.participationCode));
			headType.setOrgId(appConfig.orgId);
			headType.setTs(new Date());
			headType.setVer(appConfig.impsVersion);
			headType.setProdType(ProdType.IMPS);

			final PayTrans payTrans = new PayTrans();
			payTrans.setRefId(Generator.newRandomTxnId(appConfig.participationCode));
			payTrans.setId(Generator.newRandomTxnId(appConfig.participationCode));
			payTrans.setNote("Heart beat");
			payTrans.setRefUrl(refUrl);
			payTrans.setType(PayConstant.HBT);
			payTrans.setTs(headType.getTs());

			hbt.setHbtMsg(hbtMsg);
			hbt.setHead(headType);
			hbt.setTxn(payTrans);
			final Mono<Ack> mono = npciWebClient.heartbeat(hbt);
			mono.subscribe(ack -> {
				//todo
				logger.info("response of beneficiary transaction sent." + ack);
			});
		} catch (Exception e) {logger.error("error accured while sending heartbeat", e);}
	}
}
