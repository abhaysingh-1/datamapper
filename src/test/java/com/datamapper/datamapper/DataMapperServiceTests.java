package com.datamapper.datamapper;

import static com.datamapper.constants.AppConstants.ONE_TO_ONE_VALUE;
import static com.datamapper.constants.AppConstants.USES_LOGIC_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.datamapper.constants.AppConstants;
import com.datamapper.service.DataMapperService;
import com.datamapper.utils.DataMapperUtils;

class DataMapperServiceTests {

	private DataMapperService dataMapperService = new DataMapperService();

	@Test
	public void testIsValidExcel() {
		assertTrue(DataMapperUtils.isValidExcel(DataMapperUtils.getExcelSheet()));
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
