package com.datamapper.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelValidatorService {
	public boolean isValidExcel(Sheet sheet) {
		int numberOfColumnsInSecondRow = sheet.getRow(0).getLastCellNum();
		int numberOfColumnsInFourthRow = sheet.getRow(3).getLastCellNum();
		if (numberOfColumnsInSecondRow >= 2 && numberOfColumnsInFourthRow >= 4
				&& secondRowHeaderValidator(getHeaderDetails(sheet, 0)) 
				&& fourthRowHeaderValidator(getHeaderDetails(sheet, 3))) {
			return true;
		} else {
			return false;
		}
	}
	
	private List<String> getHeaderDetails(Sheet sheet, int rowNumber){
		List<String> headers = new ArrayList<String>();
		Iterator<Cell> cells = sheet.getRow(rowNumber).cellIterator();
		while (cells.hasNext()) {
            Cell cell = (Cell) cells.next();
            RichTextString value = cell.getRichStringCellValue();
            headers.add(value.getString());
        }
		return headers;
	}
	
	private boolean secondRowHeaderValidator(List<String> headerDetails) {
		if(headerDetails.get(0).equalsIgnoreCase("Source Type") && headerDetails.get(1).equalsIgnoreCase("Target Type")) {
			return true;
		}
		return false;
		
	}
	
	private boolean fourthRowHeaderValidator(List<String> headerDetails) {
		if(headerDetails.get(0).equalsIgnoreCase("Source Field") && headerDetails.get(1).equalsIgnoreCase("Source Value (Sample)")
				&& headerDetails.get(2).equalsIgnoreCase("Target Field") && headerDetails.get(3).equalsIgnoreCase("Target Value Mapping")) {
			return true;
		}
		return false;
		
	}

}
