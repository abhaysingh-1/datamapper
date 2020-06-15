package com.social91.main;

import org.apache.poi.ss.usermodel.Sheet;

import com.social91.model.SourceMapper;
import com.social91.model.TargetMapper;
import com.social91.utils.DataMapperUtils;

public class Test {
	public static void main(String[] args) {
		Sheet sheet = DataMapperUtils.getExcelSheet();
		
		if(DataMapperUtils.isValidExcel(sheet)) {
			SourceMapper sourceMapper = DataMapperUtils.createSourceMapper(sheet);
			TargetMapper targetMapper = DataMapperUtils.createTargetMapper(sheet);
			System.out.println(sourceMapper);
			System.out.println(targetMapper);
		}else {
			System.out.println("Not a Valid Excel");
		}
	}

}
