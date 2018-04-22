package com.lbg.report.service;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

import com.lbg.bean.CompareOutput;
import com.lbg.config.ConfigurationsStore;
import com.lbg.report.helper.CommonReportHelper;
import com.lbg.report.helper.ReportHeaderCompareHelper;
import com.lbg.report.helper.ReportRowsCompareHelper;
 
public class SimpleReportGenerationService implements ReportGenerationService {

	public String generateReport(CompareOutput output) throws IOException {
		ReportHeaderCompareHelper reportHeaderHelper = new ReportHeaderCompareHelper();
		ReportRowsCompareHelper reportRowsHelper = new ReportRowsCompareHelper();
		CommonReportHelper commonReportHelper = new CommonReportHelper();
		HtmlCanvas canvas = new HtmlCanvas();
		
		commonReportHelper.initCanvas(canvas);
		
		commonReportHelper.createReportHeader(canvas,output);
		
		//Create Header Compare header
		if(ConfigurationsStore.compareHeaders){
		reportHeaderHelper.createHeaderComparision(canvas,output);
		}
		
		reportRowsHelper.createRowsComparision(canvas,output);
		
		
		commonReportHelper.closeCanvas(canvas);
		
		return canvas.toHtml();
	}

}
