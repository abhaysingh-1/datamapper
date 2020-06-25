package com.datamapper.datamapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.datamapper.model.SourceField;
import com.datamapper.model.TargetField;
import com.datamapper.services.DataMapperService;
import com.datamapper.services.ExcelReaderService;
import com.datamapper.services.ExcelValidatorService;

class DataMapperServiceTests {

	private static String filePath = "./src/main/resources/Attribute-Mapping.xlsx";
	private static String sheetName = "FxRateProcessor";
	
	private static ExcelValidatorService excelValidatorService;
	private static ExcelReaderService excelSheetReaderService;
	private static Sheet sheet;
	static boolean validExcel;
	
	@BeforeAll
	public static void init() {
		
		excelValidatorService = new ExcelValidatorService();
		excelSheetReaderService = new ExcelReaderService(); 
		sheet = excelSheetReaderService.getExcelSheet(filePath, sheetName);
		validExcel = excelValidatorService.isValidExcel(sheet);
		
	}
	
	@Test
	public void testIsValidExcel() {
		assertTrue(validExcel);
	}

	@Test
	public void testSourceMapperType() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();;
			assertEquals("FxRate", dataMapperService.getSourceMapper(sheet).getSourceType());
		}
	}

	@Test
	public void testSourceMapperSourceField() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  SourceField sourceField = new SourceField("isoCode",6);
			assertEquals(sourceField.toString(), dataMapperService.getSourceMapper(sheet).getSourceFields().get(0).toString());
		}
	}
	
	@Test
	public void testTargetMapperType() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();;
			assertEquals("GenevaPrice", dataMapperService.getTargetMapper(sheet).getTargetType());
		}
	}
	
	@Test
	public void testTargetMapperSourceField() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = new TargetField("investment", 6, "$B6", true);
			assertEquals(targetField.toString(), dataMapperService.getTargetMapper(sheet).getTargetFields().get(1).toString());
		}
	}

	@Test
	public void testIsOneToOneMapping() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(1);
			  boolean oneToOneMapping = false;
			  if(null != targetField.getValue() && targetField.isFormula() && targetField.getValue().startsWith("$")) {
				  oneToOneMapping = true;
			  }
			  assertTrue(oneToOneMapping);
		}
	}
	
	@Test
	public void testIsOneToOneMapping_NegativeScenario1() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(3);
			  boolean oneToOneMapping = false;
			  if(null != targetField.getValue() && targetField.isFormula() && targetField.getValue().startsWith("$")) {
				  oneToOneMapping = true;
			  }
			  assertFalse(oneToOneMapping);
		}
	}

	@Test
	public void testIsOneToOneMapping_NegativeScenario2() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(8);
			  boolean oneToOneMapping = false;
			  if(null != targetField.getValue() && targetField.isFormula() && targetField.getValue().startsWith("$")) {
				  oneToOneMapping = true;
			  }
			  assertFalse(oneToOneMapping);
		}
	}
	
	@Test
	public void testIsHardCoded() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(3);
			  boolean hardCoded = false;
			  if(null != targetField.getValue() && !targetField.isFormula()) {
				  hardCoded = true;
			  }
			  assertTrue(hardCoded);
		}
	}
	
	@Test
	public void testIsHardCoded_NegativeScenario() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(2);
			  boolean hardCoded = false;
			  if(null != targetField.getValue() && !targetField.isFormula()) {
				  hardCoded = true;
			  }
			  assertFalse(hardCoded);
		}
	}
	
	@Test
	public void testIsLogicalFormula() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(8);
			  boolean logicalFormula = false;
			  if(null != targetField.getValue() && targetField.isFormula() && !targetField.getValue().startsWith("$")) {
				  logicalFormula = true;
			  }
			  assertTrue(logicalFormula);
		}
	}
	
	@Test
	public void testIsLogicalFormula_NegativeScenario() {
		if(validExcel) {
			  DataMapperService dataMapperService = new DataMapperService();
			  TargetField targetField = dataMapperService.getTargetMapper(sheet).getTargetFields().get(8);
			  boolean logicalFormula = false;
			  if(null != targetField.getValue() && targetField.isFormula() && !targetField.getValue().startsWith("$")) {
				  logicalFormula = true;
			  }
			  assertTrue(logicalFormula);
		}
	}
	
}
