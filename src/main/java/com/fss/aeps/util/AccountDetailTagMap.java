package com.fss.aeps.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fss.aeps.jaxb.AccountDetailType;
import com.fss.aeps.jaxb.AccountType;


public final class AccountDetailTagMap {

	public static final String toTlvString(final List<AccountType.Detail> tags) {
		final StringBuilder sb = new StringBuilder();
		for (final AccountType.Detail tag : tags) {
			final String name = tag.getName().name();
			final String value = tag.getValue();
			sb.append(String.format("%03d", name.length())).append(name);
			sb.append(String.format("%03d", value.length())).append(value);
		}
		return sb.toString();
	}
	
	
	public static final List<AccountType.Detail> toTagList(final String tlv) {
		if(tlv == null) return List.of();
		final List<AccountType.Detail> tags = new ArrayList<>();
		int pointer = 0;
		while(pointer < tlv.length()) {
			final int nlen = Integer.parseInt(tlv.substring(pointer, pointer+3));
			pointer+=3;
			final AccountDetailType type = AccountDetailType.valueOf(tlv.substring(pointer, pointer+nlen));
			pointer+=nlen;
			final int vlen = Integer.parseInt(tlv.substring(pointer, pointer+3));
			pointer+=3;
			final String value = tlv.substring(pointer, pointer+vlen);
			pointer+=vlen;
			tags.add(new AccountType.Detail(type, value));
		}
		return tags;
	}
	
	public static final Map<String, String> toTagMap(final String tlv) {
		final Map<String, String> map = new HashMap<>();
		if(tlv == null) return Map.of();
		int pointer = 0;
		while(pointer < tlv.length()) {
			final int nlen = Integer.parseInt(tlv.substring(pointer, pointer+3));
			pointer+=3;
			final String name = tlv.substring(pointer, pointer+nlen);
			pointer+=nlen;
			final int vlen = Integer.parseInt(tlv.substring(pointer, pointer+3));
			pointer+=3;
			final String value = tlv.substring(pointer, pointer+vlen);
			pointer+=vlen;
			map.put(name, value);
		}
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(toTagMap("006ACTYPE007SAVINGS006MOBNUM012919876543210004MMID0071111111"));
	}
	
}
