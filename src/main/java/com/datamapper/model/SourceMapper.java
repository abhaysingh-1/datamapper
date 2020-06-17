package com.datamapper.model;

import java.util.List;

public class SourceMapper {
	private String sourceType;
	private List<SourceField> sourceFields;
	
	public SourceMapper(String sourceType, List<SourceField> sourceFields) {
		super();
		this.sourceType = sourceType;
		this.sourceFields = sourceFields;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public List<SourceField> getSourceFields() {
		return sourceFields;
	}
	public void setSourceFields(List<SourceField> sourceFields) {
		this.sourceFields = sourceFields;
	}
	
	@Override
	public String toString() {
		return "SourceMapper [sourceType=" + sourceType + ", sourceFields=" + sourceFields + "]";
	}
}
