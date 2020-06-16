package com.datamapper.datamapper;

import static com.datamapper.constants.AppConstants.EXCEL_PATH;
import static com.datamapper.constants.AppConstants.ONE_TO_ONE_VALUE;
import static com.datamapper.constants.AppConstants.SHEET_NAME;
import static com.datamapper.constants.AppConstants.USES_LOGIC_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.datamapper.constants.AppConstants;
import com.datamapper.service.DataMapperService;
import com.datamapper.utils.DataMapperUtils;


class DataMapperServiceTests {
	private static FileInputStream file;
	private static Workbook workBook = null;
	private static Sheet sheet = null;
	private DataMapperService dataMapperService = new DataMapperService();
	
	@BeforeClass
	public void setUp() throws EncryptedDocumentException, IOException {

		file = new FileInputStream(EXCEL_PATH);
		workBook = WorkbookFactory.create(file);
		sheet = workBook.getSheet(SHEET_NAME);
	}

	@Test
	public void testIsValidExcel() {
		assertTrue(DataMapperUtils.isValidExcel(sheet));
	}

	@Test
	public void testuseCases_hardCodedValue() {
		assertEquals(AppConstants.HARD_CODED_VALUE, dataMapperService.useCases("priceProvider"));
	}
	
	@Test
	public void testuseCases_oneToOneValue() {
		assertEquals(ONE_TO_ONE_VALUE, dataMapperService.useCases("investment"));
	}
	
	@Test
	public void testuseCases_UsesLogicValue() {
		assertEquals(USES_LOGIC_VALUE, dataMapperService.useCases("priceDenomination"));
	}
	
	@Test
	public void testuseCases_NullValue() {
		assertEquals(null, dataMapperService.useCases("externalReference"));
	}

}
