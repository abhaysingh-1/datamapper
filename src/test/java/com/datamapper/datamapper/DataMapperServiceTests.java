package com.datamapper.datamapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.datamapper.services.DataMapperService;
import com.datamapper.services.ExcelReaderService;
import com.datamapper.services.ExcelValidatorService;

class DataMapperServiceTests {

	private DataMapperService dataMapperService = new DataMapperService();
	private ExcelValidatorService excelValidatorService = new ExcelValidatorService();
	ExcelReaderService excelSheetReaderService = new ExcelReaderService(); 

	@Test
	public void testIsValidExcel() {
		assertTrue(excelValidatorService.isValidExcel(excelSheetReaderService.getExcelSheet()));
	}

	@Test
	public void testuseCases_hardCodedValue() {
		assertEquals("hardCoded value", dataMapperService.useCases("priceProvider"));
	}

	@Test
	public void testuseCases_oneToOneValue() {
		assertEquals("one to one mapping", dataMapperService.useCases("investment"));
	}

	@Test
	public void testuseCases_UsesLogicValue() {
		assertEquals("uses logic", dataMapperService.useCases("priceDenomination"));
	}

	@Test
	public void testuseCases_NullValue() {
		assertEquals(null, dataMapperService.useCases("externalReference"));
	}

}
