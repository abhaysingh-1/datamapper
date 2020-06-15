package com.social91.model;

import java.util.List;

public class SourceMapper {
	private String sourceType;
	private List<SourceFields> sourceFields;
	
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public List<SourceFields> getSourceFields() {
		return sourceFields;
	}
	public void setSourceFields(List<SourceFields> sourceFields) {
		this.sourceFields = sourceFields;
	}
	
	@Override
	public String toString() {
		return "SourceMapper [sourceType=" + sourceType + ", sourceFields=" + sourceFields + "]";
	}
}
