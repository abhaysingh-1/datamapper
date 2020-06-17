package com.datamapper.model;

import java.util.List;

public class SourceMapperBuilder {
	private String sourceType;
	private List<SourceField> sourceFields;
	
	public SourceMapperBuilder setSourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}
	public SourceMapperBuilder setSourceFields(List<SourceField> sourceFields) {
		this.sourceFields = sourceFields;
		return this;
	}
	
	public SourceMapper getSourceMapper() {
		return new SourceMapper(sourceType, sourceFields);
	}

}
