package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public enum JobStyle {
	PART_TIME(100), FULL_TIME(110), INTERN(210), FREELANCE(300);
	
	private final int sgkJobId;

	private JobStyle(int sgkJobId) {
		this.sgkJobId = sgkJobId;
	}

	public int getSgkJobId() {
		return sgkJobId;
	}
	
}
