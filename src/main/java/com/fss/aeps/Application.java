package com.fss.aeps;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { 
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class })
@ComponentScan
@ServletComponentScan
@EnableJpaRepositories(considerNestedRepositories = true)
public class Application {

	public static void main(String[] args) throws IOException {
		System.setProperty("jdk.tls.ephemeralDHKeySize", "2048");
		System.setProperty("jdk.tls.rejectClientInitiatedRenegotiation", "true");
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "false");
		ApplicationContext context = new SpringApplicationBuilder(Application.class)
				//.initializers(new ApplicationInitializer())
				.run();
		System.out.println(context);
	}
}
