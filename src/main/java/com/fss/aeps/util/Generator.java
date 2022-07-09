package com.fss.aeps.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


public class Generator {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	
	private static final DateTimeFormatter CRN_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

	private static final ThreadLocal<DecimalFormat> decimalFormat = ThreadLocal.withInitial(() -> new DecimalFormat("#0.00"));
	private static final ThreadLocal<SimpleDateFormat> rrnSuffixFormat    = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyDDDHH"));
	
	public static final String newRandomTxnId(String participationCode) {
		return participationCode + UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static final String newRandomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static final String zonedDateTimeToISOString(final ZonedDateTime zonedDateTime) {
		return zonedDateTime.truncatedTo(ChronoUnit.MILLIS).format(ISO_FORMATTER);
	}

	public static final String amountToDecimalString(final double amount) {
		return decimalFormat.get().format(amount);
	}
	
	public static final String amountToDecimalString(final BigDecimal amount) {
		return decimalFormat.get().format(amount);
	}

	public static void main(String[] args) {
		System.out.println(generateCRN());
	}

	private static final String generateCRN() {
		return "UPI"+LocalDate.now().format(CRN_FORMATTER)+String.format("%07d", new SecureRandom().nextInt(9999999));
	}
	
	public static final String getRRNSuffix(final Date time) {
		return rrnSuffixFormat.get().format(time).substring(3);
	}
	
	public static final String getNewRRN(final Date time) {
		return rrnSuffixFormat.get().format(time).substring(3)+String.format("%06d", SECURE_RANDOM.nextInt(999999));
	}
}
