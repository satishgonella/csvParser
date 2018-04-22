package com.lbg.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.lbg.bean.CsvBean;
import com.lbg.bean.HeaderElement;
import com.lbg.bean.RowElement;
import com.lbg.exception.ConfigNotFoundException;
import com.lbg.parser.CsvParser;

public class SimpleBeanCreatorService implements BeanCreatorService {

	public CsvBean getBeanFromCsv(File csvFile) throws FileNotFoundException, ConfigNotFoundException, IOException {
		CsvBean csvBean = new CsvBean();
		CsvParser csvParser = new CsvParser(csvFile);
		HeaderElement headerElement = csvParser.getHeaderRow();
		List<RowElement> rows = csvParser.getRows();
		csvBean.setHeader(headerElement);
		csvBean.setRows(rows);
		csvBean.setFileName(csvFile.getName());
		return csvBean;
	}

}
