package com.social91.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.social91.model.SourceFields;
import com.social91.model.SourceMapper;
import com.social91.model.TargetFields;
import com.social91.model.TargetMapper;

public class DataMapperUtils {

	public static Sheet getExcelSheet() {
		FileInputStream file;
		Workbook workBook;
		Sheet sheet = null;
		try {
			file = new FileInputStream("./src/main/resources/Attribute-Mapping.xlsx");
			workBook = WorkbookFactory.create(file);
			sheet = workBook.getSheet("FxRateProcessor");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sheet;
	}

	public static boolean isValidExcel(Sheet sheet) {
		int numberOfColumnsInSecondRow = sheet.getRow(0).getLastCellNum();
		int numberOfColumnsInFourthRow = sheet.getRow(3).getLastCellNum();
		if (numberOfColumnsInSecondRow >= 2 && numberOfColumnsInFourthRow >= 4) {
			return true;
		} else {
			return false;
		}
	}

	public static SourceMapper createSourceMapper(Sheet sheet) {
		SourceMapper sourceMapper = new SourceMapper();
		sourceMapper.setSourceType(sheet.getRow(1).getCell(0).getStringCellValue());
		sourceMapper.setSourceFields(getSourceFieldsList(sheet));
		return sourceMapper;
	}

	private static List<SourceFields> getSourceFieldsList(Sheet sheet) {
		List<SourceFields> sourceFieldsList = new ArrayList<SourceFields>();
		for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell sourceFieldColumn = row.getCell(0);
				if (sourceFieldColumn != null) {
					String cellValue = sourceFieldColumn.getStringCellValue();
					SourceFields sourceFields = new SourceFields();
					sourceFields.setFieldName(cellValue);
					sourceFields.setRowNumber(rowIndex + 1);
					sourceFieldsList.add(sourceFields);
				}
			}
		}
		return sourceFieldsList;
	}

	public static TargetMapper createTargetMapper(Sheet sheet) {
		TargetMapper targetMapper = new TargetMapper();
		targetMapper.setTargetType(sheet.getRow(1).getCell(1).getStringCellValue());
		targetMapper.setTargetFields(getTargetFieldsList(sheet));
		return targetMapper;
	}

	private static List<TargetFields> getTargetFieldsList(Sheet sheet) {
		List<TargetFields> targetFieldsList = new ArrayList<TargetFields>();
		for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell targetFieldColumn = row.getCell(2);
				Cell targetValueColumn = row.getCell(3);
				if (targetFieldColumn != null) {
					String cellValue = targetFieldColumn.getStringCellValue();
					TargetFields targetFields = new TargetFields();
					targetFields.setFieldName(cellValue);
					targetFields.setRowNumber(rowIndex + 1);
					if (targetValueColumn != null) {
						switch (targetValueColumn.getCellType()) {
						case STRING:
							targetFields.setValue(targetValueColumn.getStringCellValue());
							break;
						case FORMULA:
							targetFields.setFormula(true);
							targetFields.setValue(targetValueColumn.getCellFormula());
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