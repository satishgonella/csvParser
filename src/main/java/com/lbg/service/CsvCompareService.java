package com.lbg.service;

import com.lbg.bean.CompareOutput;
import com.lbg.bean.CsvBean;
import com.lbg.exception.ConfigNotFoundException;

public interface CsvCompareService {

	public CompareOutput compareCsvBeans(CsvBean expectedBean, CsvBean actualBean) throws ConfigNotFoundException;
	
}
