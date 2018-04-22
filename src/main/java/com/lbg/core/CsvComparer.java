package com.lbg.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.lbg.bean.CompareOutput;
import com.lbg.bean.CsvBean;
import com.lbg.exception.ConfigNotFoundException;
import com.lbg.service.BeanCreatorService;
import com.lbg.service.CsvCompareService;
import com.lbg.service.SimpleBeanCreatorService;
import com.lbg.service.SimpleCsvCompareService;

public class CsvComparer {

	public CsvBean convertCsvToBean(File csvFile){
		CsvBean csvBean = null;
		BeanCreatorService beanCreatorService = new SimpleBeanCreatorService();
		try {
			csvBean = beanCreatorService.getBeanFromCsv(csvFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return csvBean;
	}
	
	public CompareOutput compareCsvBeans(CsvBean expected, CsvBean actual){
		CsvCompareService csvCompareService = new SimpleCsvCompareService();
		CompareOutput compareOutput = null;
		try {
			compareOutput = csvCompareService.compareCsvBeans(expected, actual);
		} catch (ConfigNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compareOutput;
	}
	
	public CompareOutput compareCsvFiles(File expectedFile, File actualFile){
		CsvBean expectedBean = convertCsvToBean(expectedFile);
		CsvBean actualBean = convertCsvToBean(actualFile);
		
		return compareCsvBeans(expectedBean, actualBean);
	}
	
}
