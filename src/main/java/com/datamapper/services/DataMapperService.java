package com.datamapper.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.datamapper.model.SourceField;
import com.datamapper.model.SourceMapper;
import com.datamapper.model.SourceMapperBuilder;
import com.datamapper.model.TargetField;
import com.datamapper.model.TargetMapper;
import com.datamapper.model.TargetMapperBuilder;

public class DataMapperService {

	
	public SourceMapper getSourceMapper(Sheet sheet) {
		SourceMapper sourceMapper = new SourceMapperBuilder().setSourceType(sheet.getRow(1).getCell(0).getStringCellValue()).setSourceFields(getSourceFieldsList(sheet)).getSourceMapper();
		return sourceMapper;
	}

	private List<SourceField> getSourceFieldsList(Sheet sheet) {
		List<SourceField> sourceFieldsList = new ArrayList<SourceField>();
		for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell sourceFieldColumn = row.getCell(0);
				if (sourceFieldColumn != null) {
					String cellValue = sourceFieldColumn.getStringCellValue();
					SourceField sourceFields = new SourceField();
					sourceFields.setFieldName(cellValue);
					sourceFields.setRowNumber(rowIndex + 1);
					sourceFieldsList.add(sourceFields);
				}
			}
		}
		return sourceFieldsList;
	}
	
	public TargetMapper getTargetMapper(Sheet sheet) {
		TargetMapper targetMapper = new TargetMapperBuilder().setTargetType(sheet.getRow(1).getCell(1).getStringCellValue()).setTargetFields(getTargetFieldsList(sheet)).getTargetMapper();
		return targetMapper;
	}

	private List<TargetField> getTargetFieldsList(Sheet sheet) {
		List<TargetField> targetFieldsList = new ArrayList<TargetField>();
		for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell targetFieldColumn = row.getCell(2);
				Cell targetValueColumn = row.getCell(3);
				if (targetFieldColumn != null) {
					String cellValue = targetFieldColumn.getStringCellValue();
					TargetField targetFields = new TargetField();
					targetFields.setFieldName(cellValue);
					targetFields.setRowNumber(rowIndex + 1);
					if (targetValueColumn != null) {
						switch (targetValueColumn.getCellType()) {
						case STRING:
							targetFields.setValue(targetValueColumn.getStringCellValue());
							break;
						case NUMERIC:
							targetFields.setValue(String.valueOf(targetValueColumn.getNumericCellValue()));
							break;
						case BOOLEAN:
							targetFields.setValue(String.valueOf(targetValueColumn.getBooleanCellValue()));
							break;
						case FORMULA:
							targetFields.setFormula(true);
							targetFields.setValue(targetValueColumn.getCellFormula());
							break;
						default:
							break;
						}
					}
					targetFieldsList.add(targetFields);
				}
			}
		}
		return targetFieldsList;
	}
}
