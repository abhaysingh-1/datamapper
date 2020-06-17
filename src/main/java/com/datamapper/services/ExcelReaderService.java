package com.datamapper.services;

import static com.datamapper.constants.AppConstants.EXCEL_PATH;
import static com.datamapper.constants.AppConstants.SHEET_NAME;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelReaderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReaderService.class);

	public Sheet getExcelSheet() {
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
		return sheet;
	}
}
