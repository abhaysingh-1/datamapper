package com.social91.model;

import java.util.List;

public class TargetMapper {
	private String targetType;
	private List<TargetFields> targetFields;
	
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public List<TargetFields> getTargetFields() {
		return targetFields;
	}
	public void setTargetFields(List<TargetFields> targetFields) {
		this.targetFields = targetFields;
	}
	@Override
	public String toString() {
		return "TargetMapper [targetType=" + targetType + ", targetFields=" + targetFields + "]";
	}
}
