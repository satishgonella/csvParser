package com.lbg.report.service;

import java.io.IOException;

import com.lbg.bean.CompareOutput;

public interface ReportGenerationService {

	public String generateReport(CompareOutput output) throws IOException;
}
