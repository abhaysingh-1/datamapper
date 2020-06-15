package com.social91.model;

public class TargetFields {
	private String fieldName;
	private int rowNumber;
	private String value;
	private boolean formula;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isFormula() {
		return formula;
	}
	public void setFormula(boolean formula) {
		this.formula = formula;
	}
	@Override
	public String toString() {
		return "TargetFields [fieldName=" + fieldName + ", rowNumber=" + rowNumber + ", value=" + value + ", formula="
				+ formula + "]";
	}
}
