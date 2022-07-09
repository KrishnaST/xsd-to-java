package com.fss.aeps.util;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.fss.aeps.jaxb.AccountType.Detail;


public final class AccountIFSCCollector implements Collector<Detail, String[], AccountIFSC> {

	private static final AccountIFSCCollector collector = new AccountIFSCCollector();
	
	public static final AccountIFSCCollector getInstance() {
		return collector;
	} 
	
	private AccountIFSCCollector() {}

	@Override
	public final Supplier<String[]> supplier() {
		return () -> new String[3];
	}

	@Override
	public final BiConsumer<String[], Detail> accumulator() {
		return (s, d) -> {
			if (d.getName() == null) return;
			switch (d.getName()) {
				case ACNUM : s[0] = d.getValue();break;
				case IFSC : s[1] = d.getValue();break;
				case ACTYPE : s[2] = d.getValue();break;
				default : {}
			}
		};
	}

	@Override
	public final BinaryOperator<String[]> combiner() {
		return (s1, s2) -> {
			final String[] s3 = new String[2];
			s3[0] = s1[0] == null ? s2[0] : s1[0];
			s3[1] = s1[1] == null ? s2[1] : s1[1];
			s3[2] = s1[2] == null ? s2[2] : s1[2];
			return s3;
		};

	}

	@Override
	public final Function<String[], AccountIFSC> finisher() {
		return s -> new AccountIFSC(s[0], s[1]).setAccountType(s[2]);
	}

	@Override
	public final Set<Characteristics> characteristics() {
		return Set.of(Characteristics.UNORDERED);
	}
	
	public static void main(String[] args) {
		System.out.println("abc@#$%^&* ".replaceAll("[^a-zA-Z0-9 ]", "-"));
	}
}
