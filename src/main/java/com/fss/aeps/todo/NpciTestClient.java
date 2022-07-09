package com.fss.aeps.todo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NpciTestClient {

	/*
	 * @Autowired public WebClient webClient;
	 * 
	 * public final Mono<Ack> sendTest() throws IOException {
	 * 
	 * return webClient.get().uri("/test").retrieve().bodyToMono(Ack.class);//3510 }
	 */
}
