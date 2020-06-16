package com.datamapper.model;

import java.util.List;

public class TargetMapper {
	private String targetType;
	private List<TargetField> targetFields;
	
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public List<TargetField> getTargetFields() {
		return targetFields;
	}
	public void setTargetFields(List<TargetField> targetFields) {
		this.targetFields = targetFields;
	}
	@Override
	public String toString() {
		return "TargetMapper [targetType=" + targetType + ", targetFields=" + targetFields + "]";
	}
}
