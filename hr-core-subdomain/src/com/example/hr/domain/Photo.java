package com.example.hr.domain;

import java.util.Arrays;
import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}
	
	public byte[] getValues() {
		return Arrays.copyOf(values, values.length);
	}

	public static Photo of(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}
	
}