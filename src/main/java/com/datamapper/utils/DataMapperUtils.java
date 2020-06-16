package com.datamapper.utils;

import static com.datamapper.constants.AppConstants.EXCEL_PATH;
import static com.datamapper.constants.AppConstants.SHEET_NAME;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datamapper.model.SourceField;
import com.datamapper.model.SourceMapper;
import com.datamapper.model.TargetField;
import com.datamapper.model.TargetMapper;

public class DataMapperUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapperUtils.class);

	public static Sheet getExcelSheet() {
		LOGGER.info("DataMapperUtils.getExcelSheet() :: method  started ");

		FileInputStream file;
		Workbook workBook;
		Sheet sheet = null;
		try {
			file = new FileInputStream(EXCEL_PATH);
			workBook = WorkbookFactory.create(file);
			sheet = workBook.getSheet(SHEET_NAME);
		} catch (FileNotFoundException e) {
			LOGGER.error("ErrorMsg = {}, Exception ={}", e.getMessage(), e);
		} catch (EncryptedDocumentException e) {
			LOGGER.error("ErrorMsg = {}, Exception ={}", e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error("ErrorMsg = {}, Exception ={}", e.getMessage(), e);
		}
		LOGGER.info("DataMapperUtils.getExcelSheet() :: method  ended ");
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

	private static List<SourceField> getSourceFieldsList(Sheet sheet) {
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

	public static TargetMapper createTargetMapper(Sheet sheet) {
		TargetMapper targetMapper = new TargetMapper();
		targetMapper.setTargetType(sheet.getRow(1).getCell(1).getStringCellValue());
		targetMapper.setTargetFields(getTargetFieldsList(sheet));
		return targetMapper;
	}

	private static List<TargetField> getTargetFieldsList(Sheet sheet) {
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