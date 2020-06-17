package com.datamapper.services;

import static com.datamapper.constants.AppConstants.HARD_CODED_VALUE;
import static com.datamapper.constants.AppConstants.ONE_TO_ONE_FORMULA_TYPE;
import static com.datamapper.constants.AppConstants.ONE_TO_ONE_VALUE;
import static com.datamapper.constants.AppConstants.USES_LOGIC_VALUE;

import org.apache.poi.ss.usermodel.Sheet;

import com.datamapper.model.TargetField;
import com.datamapper.model.TargetMapper;

public class DataMapperService {

	ExcelValidatorService excelValidatorService = new ExcelValidatorService();
	ExcelReaderService excelSheetReaderService = new ExcelReaderService();
	TargetMapperBuilderService targetMapperSettingService = new TargetMapperBuilderService();

	public String useCases(String targetFieldName) {
		Sheet sheet = excelSheetReaderService.getExcelSheet();
		if (excelValidatorService.isValidExcel(sheet)) {
			TargetMapper targetMapper = targetMapperSettingService.createTargetMapper(sheet);
			for (int i = 0; i < targetMapper.getTargetFields().size(); i++) {
				TargetField fieldNameValue = targetMapper.getTargetFields().get(i);
				if (fieldNameValue.getFieldName().equals(targetFieldName) && null != fieldNameValue.getValue()) {
					if (!fieldNameValue.isFormula()) {
						return HARD_CODED_VALUE;
					} else if (fieldNameValue.isFormula()
							&& fieldNameValue.getValue().startsWith(ONE_TO_ONE_FORMULA_TYPE)) {
						return ONE_TO_ONE_VALUE;
					} else {
						return USES_LOGIC_VALUE;
					}
				}
			}
		}
		return null;
	}
}
