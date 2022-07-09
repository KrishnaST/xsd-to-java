package com.fss.aeps.acquirer.iso8583;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.util.iso8583.EncoderDecoder;
import org.util.iso8583.ISO8583Message;
import org.util.iso8583.api.ISO8583Exception;
import org.util.iso8583.api.ISOFormat;
import org.util.iso8583.format.CBSFormat;

import com.fss.aeps.util.Tlv;

@Component
public class ISO8583AcquirerServer implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ISO8583AcquirerServer.class);
	private static final ISOFormat cbsFormat = CBSFormat.getInstance();
	
	@Value("${acquirer.port}")
	public int acquirerPort;
	
	@Autowired
	@Qualifier(value = "threadpool")
	private ThreadPoolExecutor executor;
	
	@Autowired
	private ApplicationContext context;

	@Override
	public final void run() {
		try(ServerSocket serverSocket = new ServerSocket(acquirerPort);) {
			while (true) {
				final Socket socket = serverSocket.accept();
				executor.execute(new ISO8583TransactionDispatcher(socket));
				logger.info("acquirer transaction scheduled for socket : "+socket);
			}
		} catch (Exception e) {
			logger.error("error in iso8583 acquirer server", e);
		}
	}

	
	private class ISO8583TransactionDispatcher implements Runnable {

		private Socket socket;
		
		public ISO8583TransactionDispatcher(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				final ISO8583Message isoRequest = EncoderDecoder.readMessage(cbsFormat, socket.getInputStream());
				logger.info("processing acquirer transaction : "+isoRequest.get(37));
				logger.info("iso8583 request : \r\n"+EncoderDecoder.log(isoRequest));
				System.out.println("iso8583 request : \r\n"+EncoderDecoder.log(isoRequest));
				final Tlv tlv = Tlv.parse(isoRequest.get(120));
				
				final String txnType = tlv.get("001");
				if("48".equals(txnType)) {
					final ISO8583Transaction transaction = context.getBean(ISO8583Transaction.class, socket, isoRequest);
					executor.execute(transaction);
				}
				else if("34".equals(txnType)) {
					final ISO8583Verification verification = context.getBean(ISO8583Verification.class, socket, isoRequest);
					executor.execute(verification);
				}
				else if("70".equals(txnType)) {
					final ISO8583NameEnquiry transaction = context.getBean(ISO8583NameEnquiry.class, socket, isoRequest);
					executor.execute(transaction);
				}
				else {
					logger.error("unknown iso8583 transaction request received. type : "+txnType);
				}
			}
			catch (ISO8583Exception e) {
				logger.error("error in iso8583 acquirer transaction dispatcher : "+e.getMessage());
			}
			catch (Exception e) {
				logger.error("error in iso8583 acquirer transaction dispatcher", e);
			}
		}
	}

	
}
