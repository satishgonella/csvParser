package com.lbg.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lbg.bean.HeaderElement;
import com.lbg.bean.RowElement;
import com.lbg.config.ConfigurationsStore;
import com.lbg.exception.ConfigNotFoundException;
import com.lbg.util.CsvCompareHelper;

public class CsvParser {

	BufferedReader csvBufferedReader;
	File csvFile;
	public CsvParser(File csvFile) throws FileNotFoundException,
			ConfigNotFoundException {
		CsvCompareHelper.checkForConfigFile();
		this.csvFile = csvFile;
	}

	private void initBufferedReader(File csvFile) throws FileNotFoundException {
		csvBufferedReader = new BufferedReader(new FileReader(csvFile));
	}

	public HeaderElement getHeaderRow() throws IOException {
		HeaderElement headerElement = new HeaderElement();
		List<String> headerList = null;
		String header;
		initBufferedReader(this.csvFile);
		if (ConfigurationsStore.compareHeaders) {
			header = csvBufferedReader.readLine();
				headerList = Arrays.asList(header
						.split(ConfigurationsStore.csvDelimiter));
		}

		headerElement.setHeaders(headerList);
		headerElement.setKeys(ConfigurationsStore.keys);
		return headerElement;
	}
	
	public List<RowElement> getRows() throws IOException {
		List<RowElement> rows = new ArrayList<RowElement>();
		String row;
		int lineNumber = 0;
		HeaderElement headerElement = null;
		initBufferedReader(this.csvFile);
		if(ConfigurationsStore.compareHeaders){
			lineNumber++;
			headerElement = getHeaderRow();
		}
		while ((row = csvBufferedReader.readLine()) != null) {
			lineNumber++;
			RowElement rowElement = getRowElement(row, headerElement);
			rowElement.setLineNumber(lineNumber);
			rows.add(rowElement);
		}
		return rows;
	}

	private RowElement getRowElement(String row, HeaderElement headerElement) {
		List<String> rowValue = Arrays.asList(row
				.split(ConfigurationsStore.csvDelimiter));
		RowElement rowElement = new RowElement();
		rowElement.setRowHeaders(headerElement);
		rowElement.setRowEntries(rowValue);
		return rowElement;
	}

}
