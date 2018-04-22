package com.lbg.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.lbg.bean.CsvBean;
import com.lbg.exception.ConfigNotFoundException;

public interface BeanCreatorService {

	public CsvBean getBeanFromCsv(File csvFile) throws FileNotFoundException, ConfigNotFoundException, IOException;
	
}
