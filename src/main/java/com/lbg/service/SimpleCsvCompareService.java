package com.lbg.service;

import com.lbg.bean.CompareOutput;
import com.lbg.bean.CsvBean;
import com.lbg.compare.BeanComparer;
import com.lbg.exception.ConfigNotFoundException;

public class SimpleCsvCompareService implements CsvCompareService {

	public CompareOutput compareCsvBeans(CsvBean expectedBean,
			CsvBean actualBean) throws ConfigNotFoundException {
		BeanComparer beanComparer = new BeanComparer();
		return beanComparer.compareBeans(expectedBean, actualBean);
	}

}
