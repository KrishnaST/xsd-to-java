package com.fss.aeps;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;

import com.fss.aeps.acquirer.iso8583.ISO8583AcquirerServer;
import com.fss.aeps.cbsclient.CBSClient;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.ProdType;
import com.fss.aeps.repository.Repositories.CbsToNpciResponseCodesRepository;
import com.fss.aeps.repository.Repositories.ConfigRepository;
import com.fss.aeps.repository.Repositories.NpciToAcquirerResponseCodesRepository;
import com.fss.aeps.util.Generator;
import com.fss.aeps.util.Mapper;
import com.fss.aeps.util.XMLSigner;
import com.fss.aeps.webapi.SignatureFilter;

@Configuration
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Autowired
	private ApplicationContext context;

	@Value("${product.version}")
	public String productVersion;

	@Value("${orgId}")
	public String orgId;

	@Value("${participationCode}")
	public String participationCode;

	@Value("${bank.mmid}")
	public String bankmmid;

	@Value("${npci.baseurl}")
	public String npciBaseUrl;

	@Value("${npci.readTimeout}")
	public int npciReadTimeout;

	@Value("${npci.connectTimeout}")
	public int npciConnectTimeout;

	@Value("${signer.keystore.location}")
	Resource signerKeystoreLocation;

	@Value("${signer.keystore.password}")
	private String signerKeystorePassword;

	@Value("${signer.alias}")
	private String signerKeyAlias;

	@Value("${npci.signer.certificate.location}")
	Resource npciSignerCertificateLocation;

	@Value("${cbs.baseurl}")
	public String cbsBaseUrl;

	@Value("${cbs.readTimeout}")
	public int cbsReadTimeout;

	@Value("${cbs.connectTimeout}")
	public int cbsConnectTimeout;

	@Value("${server.http.port:#{0}}")
	int optionalHttpPort;

	//@Autowired
	public ConfigRepository configRepository;
	
	//@Autowired
	private CbsToNpciResponseCodesRepository cbsToNpciResponseCodesRepository;

	//@Autowired
	private NpciToAcquirerResponseCodesRepository npciToAcquirerResponseCodesRepository;

	@Bean("cbsToNpciResponseMapper")
	public Mapper getCbsToNpciResponseMapper() throws IOException {
		Map<String, String> map = new HashMap<>();
		//cbsToNpciResponseCodesRepository.findAll().forEach(c -> map.put(c.getCbsCode(), c.getNpciCode()));
		return new Mapper(map);
	}

	@Bean("npciToAcquirerResponseMapper")
	public Mapper getNpciToAcquirerResponseCodesMapper() throws IOException {
		Map<String, String> map = new HashMap<>();
		//npciToAcquirerResponseCodesRepository.findAll().forEach(c -> map.put(c.getNpciCode(), c.getAcquirerCode()));
		return new Mapper(map);
	}

	@Bean
	public XMLSigner getXMLSigner() {
		return new XMLSigner(signerKeystoreLocation, signerKeystorePassword, signerKeyAlias);
	}



	@Bean(name = "threadpool")
	public ThreadPoolExecutor getExecutor() {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

	@Bean(name = "npciSignerPublicKey")
	public PublicKey getNpciSignerPublicKey() {
		try (final InputStream is = npciSignerCertificateLocation.getInputStream()) {
			final CertificateFactory fact = CertificateFactory.getInstance("X.509");
			final X509Certificate certificate = (X509Certificate) fact.generateCertificate(is);
			return certificate.getPublicKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean(name = "cbsClient")
	public CBSClient getCBSClient(@Value("${cbs.type}") String cbsType) {
		return context.getBean(cbsType, CBSClient.class);
	}

	@Bean
	public FilterRegistrationBean<SignatureFilter> getSignatureFilter(XMLSigner xmlSigner,
			PublicKey npciSignerPublicKey) {
		SignatureFilter filter = new SignatureFilter(xmlSigner, npciSignerPublicKey);
		FilterRegistrationBean<SignatureFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(filter);
		bean.setUrlPatterns(Arrays.asList("/*", "/imps", "/imps/*"));
		return bean;
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		if (optionalHttpPort != 0) {
			logger.info("http service started on optionalHttpPort provided  : " + optionalHttpPort);
			Http11Nio2Protocol protocol = new Http11Nio2Protocol();
			Connector connector = new Connector(protocol);
			connector.setScheme("http");
			connector.setPort(optionalHttpPort);
			connector.setSecure(false);
			tomcat.addAdditionalTomcatConnectors(connector);
		} else
			logger.info("http service not started as optionalHttpPortnot provided.");

		return tomcat;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public HeadType getHead() {
		final HeadType head = new HeadType();
		head.setMsgId(Generator.newRandomTxnId(participationCode));
		head.setOrgId(orgId);
		head.setTs(new Date());
		head.setVer(productVersion);
		head.setProdType(ProdType.AEPS);
		return head;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startAcquirerServer() {
		ISO8583AcquirerServer acquirerServer = context.getBean(ISO8583AcquirerServer.class);
		new Thread(acquirerServer).start();
	}
}
