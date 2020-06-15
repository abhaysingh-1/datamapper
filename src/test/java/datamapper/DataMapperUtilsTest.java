package datamapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.social91.utils.DataMapperUtils;

public class DataMapperUtilsTest {
	private static FileInputStream file;
	private static Workbook workBook;
	private static Sheet sheet = null;

	@BeforeClass
	public static void setUp() throws EncryptedDocumentException, IOException {

		file = new FileInputStream("./src/main/resources/Attribute-Mapping.xlsx");
		workBook = WorkbookFactory.create(file);
		sheet = workBook.getSheet("FxRateProcessor");
	}

	@Test
	public void testIsValidExcel() {
		assertTrue(DataMapperUtils.isValidExcel(sheet));
	}

	@Test
	public void testCreateSourceMapper() {
		String sourceMapper = "SourceMapper [sourceType=FxRate, sourceFields=[SourceFields [fieldName=isoCode, rowNumber=6], SourceFields [fieldName=date, rowNumber=7], SourceFields [fieldName=fxListName, rowNumber=12], SourceFields [fieldName=price, rowNumber=14], SourceFields [fieldName=crossFxRate, rowNumber=21]]]";
		assertEquals(sourceMapper, DataMapperUtils.createSourceMapper(sheet).toString());
	}

	@Test
	public void testCreateTargetMapper() {
		String targetMapper = "TargetMapper [targetType=GenevaPrice, targetFields=[TargetFields [fieldName=externalReference, rowNumber=5, value=null, formula=false], TargetFields [fieldName=investment, rowNumber=6, value=$B6, formula=true], TargetFields [fieldName=priceDate, rowNumber=7, value=$B7, formula=true], TargetFields [fieldName=priceProvider, rowNumber=8, value=PRJ, formula=false], TargetFields [fieldName=priceMarket, rowNumber=9, value=null, formula=false], TargetFields [fieldName=priceSource, rowNumber=10, value=null, formula=false], TargetFields [fieldName=priceType, rowNumber=11, value=null, formula=false], TargetFields [fieldName=priceList, rowNumber=12, value=$B12, formula=true], TargetFields [fieldName=priceDenomination, rowNumber=13, value=IF($B21,$B6,\"USD\"), formula=true], TargetFields [fieldName=price, rowNumber=14, value=$B14, formula=true], TargetFields [fieldName=annotation, rowNumber=15, value=null, formula=false], TargetFields [fieldName=typeOfPrice, rowNumber=16, value=null, formula=false], TargetFields [fieldName=leReceivedDate, rowNumber=17, value=null, formula=false], TargetFields [fieldName=taxLotId, rowNumber=18, value=null, formula=false], TargetFields [fieldName=accountingCalendarBackDated, rowNumber=19, value=null, formula=false], TargetFields [fieldName=accountingPeriodBackDated, rowNumber=20, value=null, formula=false]]]";
		assertEquals(targetMapper, DataMapperUtils.createTargetMapper(sheet).toString());
	}
}
