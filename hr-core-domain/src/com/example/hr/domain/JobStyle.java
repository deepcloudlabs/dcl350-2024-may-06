package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject // 3. Enum
public enum JobStyle {
	FULL_TIME, PART_TIME, INTERN, FREELANCE
}
