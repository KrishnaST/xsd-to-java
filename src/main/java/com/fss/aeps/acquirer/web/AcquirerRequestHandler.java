package com.fss.aeps.acquirer.web;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.util.Generator;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("acquirer")
public class AcquirerRequestHandler {

	@GetMapping("test")
	@PostMapping("test")
	public Ack test() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Ack ack = new Ack();
		ack.setReqMsgId(Generator.newRandomTxnId("DTY"));
		ack.setTs(new Date());
		ack.setApi("test");
		return ack;
	}
	
	
}
