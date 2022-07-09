package com.fss.aeps.util;

import java.util.Map;

public final class Mapper {

	private final Map<String, String> map;

	public Mapper(Map<String, String> map) {
		this.map = map;
	}

	public final String map(final String key) {
		final String val = map.get(key);
		return val == null ? key : val;
	}
	
	public final String mapOrDefault(final String key, String defVal) {
		final String val = map.get(key);
		return val == null ? defVal : val;
	}
}
