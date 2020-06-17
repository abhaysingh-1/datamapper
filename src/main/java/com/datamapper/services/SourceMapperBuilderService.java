package com.datamapper.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.datamapper.model.SourceField;
import com.datamapper.model.SourceMapper;
import com.datamapper.model.SourceMapperBuilder;

public class SourceMapperBuilderService {
	
	public SourceMapper createSourceMapper(Sheet sheet) {
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

}
