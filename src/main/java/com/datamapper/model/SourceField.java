package com.datamapper.model;

public class SourceField {
	private String fieldName;
	private int rowNumber;
	
	public SourceField(String fieldName, int rowNumber) {
		super();
		this.fieldName = fieldName;
		this.rowNumber = rowNumber;
	}
	
	public SourceField() {
		super();
	}


	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	@Override
	public String toString() {
		return "SourceFields [fieldName=" + fieldName + ", rowNumber=" + rowNumber + "]";
	}
}
