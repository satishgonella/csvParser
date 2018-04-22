package com.lbg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.lbg.bean.CompareOutput;
import com.lbg.bean.HeadersCompliment;


import com.lbg.bean.RowsCompliment;
import com.lbg.config.ConfigurationsManager;
import com.lbg.core.CsvComparer;
import com.lbg.report.core.ReportGenerator;

public class CsvCompareTest {

	File expectedFile;
	File actualFile;
	File configFile;
	
	@Before
	public void initTest() throws FileNotFoundException, IOException{
		configFile = new File("C:\\IdeaProjects\\csvParser\\src\\main\\resources\\csv-config.properties");
		ConfigurationsManager.initializeManager(configFile);
	}
	
	//@Test
	public void compareHeadersTest() throws FileNotFoundException, IOException{
		expectedFile = new File("test-resources/expected-heading-1.txt");
		CsvComparer comparer = new CsvComparer();
		List<String> commonHeaders = new ArrayList<String>();
		List<String> missingHeaders = new ArrayList<String>();
		List<String> extraHeaders = new ArrayList<String>();
		CompareOutput output = null;
		
		actualFile = new File("test-resources/actual-heading-1.txt");
		output = comparer.compareCsvFiles(expectedFile, actualFile);
		
		commonHeaders.add("heading1");
		commonHeaders.add("heading2");
		
		missingHeaders.add("heading3");
		missingHeaders.add("heading4");
		
		extraHeaders.add("headng3");
		extraHeaders.add("headig4");
		
		HeadersCompliment headersCompliment = output.getHeadersCompliment();
		Assert.assertEquals(headersCompliment.getCommonHeaders(), commonHeaders);
		Assert.assertEquals(headersCompliment.getExtraHeaders(), extraHeaders);
		Assert.assertEquals(headersCompliment.getMissingHeaders(), missingHeaders);
		
		actualFile = new File("test-resources/actual-heading-2.txt");
		output = comparer.compareCsvFiles(expectedFile, actualFile);

		commonHeaders.clear();
		missingHeaders.clear();
		extraHeaders.clear();
		
		commonHeaders.add("heading1");
		commonHeaders.add("heading2");
		commonHeaders.add("heading3");
		commonHeaders.add("heading4");
		
		extraHeaders.add("heading5");
		headersCompliment = output.getHeadersCompliment();
		Assert.assertEquals(headersCompliment.getCommonHeaders(), commonHeaders);
		Assert.assertEquals(headersCompliment.getExtraHeaders(), extraHeaders);
		Assert.assertEquals(headersCompliment.getMissingHeaders(), missingHeaders);
	}
	
	//@Test
	public void CompareRowsTest(){
		expectedFile = new File("test-resources/expected-heading-1.txt");
		actualFile = new File("test-resources/actual-content-1.txt");
		CsvComparer comparer = new CsvComparer();
		CompareOutput output = null;
		
		output = comparer.compareCsvFiles(expectedFile, actualFile);
		
		RowsCompliment rowsCompliment = output.getRowsCompliment();
		
		List<String> extraRowOne = new ArrayList<String>();
		List<String> missingRowOne = new ArrayList<String>();
		
		List<String> extraRowTwo = new ArrayList<String>();
		List<String> missingRowTwo = new ArrayList<String>();
		
		//one,two,thre,four
		//ek,do,teen,char
		extraRowOne.add("one");
		extraRowOne.add("two");
		extraRowOne.add("thre");
		extraRowOne.add("four");
		
		extraRowTwo.add("ek");
		extraRowTwo.add("do");
		extraRowTwo.add("teen");
		extraRowTwo.add("char");
		
		//one,two,thre,four
		//ek,do,teen,char
		missingRowOne.add("one");
		missingRowOne.add("two");
		missingRowOne.add("three");
		missingRowOne.add("four");
		
		missingRowTwo.add("ek");
		missingRowTwo.add("do");
		missingRowTwo.add("theen");
		missingRowTwo.add("char");
		
		Assert.assertEquals(rowsCompliment.getExtraRows().get(0).getRowEntries(), extraRowOne);
		Assert.assertEquals(rowsCompliment.getMissingRows().get(0).getRowEntries(), missingRowOne);
		
		Assert.assertEquals(rowsCompliment.getExtraRows().get(1).getRowEntries(), extraRowTwo);
		Assert.assertEquals(rowsCompliment.getMissingRows().get(1).getRowEntries(), missingRowTwo);
		
		actualFile = new File("test-resources/actual-content-2.txt");
		output = comparer.compareCsvFiles(expectedFile, actualFile);
		
		rowsCompliment = output.getRowsCompliment();
		
		extraRowOne.clear();
		extraRowTwo.clear();
		
		/*one,two,three,four,five
		ek,do,theen,char,paanch
		onnu,randu,moonu,nalu,anju*/
		
		extraRowOne.add("one");
		extraRowOne.add("two");
		extraRowOne.add("three");
		extraRowOne.add("four");
		extraRowOne.add("five");
		
		extraRowTwo.add("ek");
		extraRowTwo.add("do");
		extraRowTwo.add("theen");
		extraRowTwo.add("char");
		extraRowTwo.add("paanch");
		
		Assert.assertEquals(rowsCompliment.getExtraRows().get(0).getRowEntries(), extraRowOne);
		Assert.assertEquals(rowsCompliment.getMissingRows().get(0).getRowEntries(), missingRowOne);
		
		Assert.assertEquals(rowsCompliment.getExtraRows().get(1).getRowEntries(), extraRowTwo);
		Assert.assertEquals(rowsCompliment.getMissingRows().get(1).getRowEntries(), missingRowTwo);
	}
	
	@Test
	public void generateReport(){		
		CsvComparer comparer = new CsvComparer();
		ReportGenerator reportGenerator = new ReportGenerator();

		CompareOutput output = null;
/*		expectedFile = new File("test-resources/expected-heading-1.txt");
		actualFile = new File("test-resources/actual-heading-1.txt");
		expectedFile = new File("test-resources/expected-heading-1.txt");
		actualFile = new File("test-resources/actual-content-1.txt");
		expectedFile = new File("test-resources/expected-heading-1.txt");
		actualFile = new File("test-resources/actual-content-2.txt");*/
		actualFile = new File("C:\\Test\\SampleCSVFile_53000kb_sec.csv");
		expectedFile = new File("C:\\Test\\1000000 Sales Records.csv");
		output = comparer.compareCsvFiles(expectedFile, actualFile);
		reportGenerator.generateHtmlReport(output);
	}
	
}
