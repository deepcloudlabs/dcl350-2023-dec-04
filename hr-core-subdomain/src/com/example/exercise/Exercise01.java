package com.example.exercise;
import com.example.hr.domain.JobStyle;

public class Exercise01 {

	public static void main(String[] args) {
		var jobStyle = JobStyle.valueOf("PART_TIME");
		System.out.println(jobStyle);
		for (var style : JobStyle.values()) {
			System.out.println(style.ordinal());
			System.out.println(style.name());
			System.out.println(style.getSgkJobId());
		}
	}

}

