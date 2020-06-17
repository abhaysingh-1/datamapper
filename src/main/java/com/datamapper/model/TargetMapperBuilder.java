package com.datamapper.model;

import java.util.List;

public class TargetMapperBuilder {
	private String targetType;
	private List<TargetField> targetFields;
	
	public TargetMapperBuilder setTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}
	public TargetMapperBuilder setTargetFields(List<TargetField> targetFields) {
		this.targetFields = targetFields;
		return this;
	}
	
	public TargetMapper getTargetMapper() {
		return new TargetMapper(targetType, targetFields);
	}

}
