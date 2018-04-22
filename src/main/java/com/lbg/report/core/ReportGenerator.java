package com.lbg.report.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.lbg.report.service.ReportGenerationService;
import com.lbg.report.service.SimpleReportGenerationService;
import com.lbg.bean.CompareOutput;
import com.lbg.config.ConfigurationsStore;
import com.lbg.report.service.ReportGenerationService;
import com.lbg.report.service.SimpleReportGenerationService;

public class ReportGenerator {
	
	public void generateHtmlReport(CompareOutput output){
		ReportGenerationService reportService = new SimpleReportGenerationService();
		String report = null;
		try {
			report = reportService.generateReport(output);
			if(report!=null){
				printReport(report);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void printReport(String report) throws IOException {
		File reportFile = new File(getReportLocation());
		FileWriter fw = new FileWriter(reportFile);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(report);
		bw.close();		
		
	}

	public static String getReportLocation() {
		return ConfigurationsStore.reportLocation;
	}

	public void setReportLocation(String reportLocation) {
		ConfigurationsStore.reportLocation = reportLocation;
	}
	
}
