package com.lbg.report.helper;

import static org.rendersnake.HtmlAttributesFactory.style;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.rendersnake.HtmlCanvas;

import com.lbg.bean.CompareOutput;
import com.lbg.bean.RowElement;
import com.lbg.config.ConfigurationsStore;
import com.lbg.report.style.GeneralInfoMessage;
import com.lbg.report.style.SectionInternalHederLevelOne;
import com.lbg.report.style.SectionStyle;
import com.lbg.util.CsvCompareConstants;

public class CommonReportHelper {

	
	public void initCanvas(HtmlCanvas canvas) throws IOException {
		canvas
			.html()
				.body()
					.table(style("width:100%"));
		
	}
	

	public void createHeader(HtmlCanvas canvas, String header, SectionStyle headerStyle) throws IOException {
		String style = ReportCssHelper.getStyleString(headerStyle);
		canvas
			.tr()
				.td()
					.table()
						.tr()
							.td(style(style)).content(header)
						._tr()
					._table()
				._td()
			._tr();
	}
	
	public void createTableFromList(HtmlCanvas canvas,List<String> entries, String header, SectionStyle sectionStyle,boolean showEmptyMessage) throws IOException{
		canvas
			.table();
		canvas
				.tr()
					.td(style(ReportCssHelper.getStyleString(sectionStyle))).content(header)
				._tr();
		for (String entry : entries) {
			canvas
				.tr()
					.td().content(entry)
				._tr();
		}
		if(entries.isEmpty() && showEmptyMessage) {
			canvas
				.tr()
					.td().content("none..")
				._tr();
		}
		canvas
			._table();
	}
	

	public void closeCanvas(HtmlCanvas canvas) throws IOException {
		canvas
			._table()
				._body()
					._html();
		
	}


	public void createReportHeader(HtmlCanvas canvas, CompareOutput output) throws IOException {		
		canvas
			.tr()
				.td()
					.table(style("width:100%"))
						.tr()
							.td(style(ReportCssHelper.reportHeaderStyle)).content(ReportVariables.REPORT_MAIN_HEADER)
							.td().content(CsvCompareConstants.VERSION)
						._tr()
						.tr()
							.td(style(ReportCssHelper.fileNameStyle)).content("Expected File Name : "+output.getExpectedFileName())
						._tr()
						.tr()
							.td(style(ReportCssHelper.fileNameStyle)).content("Actual File Name : "+output.getActualFileName())
						._tr()
						.tr()
							.td(style(ReportCssHelper.getStyleString(new GeneralInfoMessage()))).content(getCompareStrategy())
						._tr()
					._table()
					.hr()
				._td()
			._tr();
		
	}
	
	private String getCompareStrategy() {
		if(ConfigurationsStore.considerOrder){
			return "Comparing the files based on row order..";
		}
		if(!ConfigurationsStore.keys.isEmpty()){
			String compareString;
			compareString = "Comparing the files using the primary key : entries in position "+StringUtils.join(ConfigurationsStore.keys, ConfigurationsStore.csvDelimiter);
			return compareString;
		}
		return "Doing a plain compare";
	}


	public void createRowFromList(HtmlCanvas canvas, List<String> masterList,
			List<String> highLightList, String markColor) throws IOException {
		if(highLightList==null){
			highLightList = new ArrayList<String>();
		}
		canvas.tr();
		
		for (String header : masterList) {
			String color = ReportVariables.WHITE_COLOR;
			if (highLightList.contains(header)) {
				color = markColor;
			}
			Map<String,String> styleMap = new HashMap<String,String>();
			styleMap.put("BACKGROUND-COLOR", color);
			String style = ReportCssHelper.getStyleStringFromMap(styleMap);
			canvas
				.td()
					.span(style(style)).content(header)
				._td();
		}

		canvas._tr();

	}
	
	public void createRowFromList(HtmlCanvas canvas, List<String> masterList,
			List<String> highLightList, String markColor, int lineNumber) throws IOException {
		if(highLightList==null){
			highLightList = new ArrayList<String>();
		}
		canvas.tr();
		
		for (String header : masterList) {
			String color = ReportVariables.WHITE_COLOR;
			if (highLightList.contains(header)) {
				color = markColor;
			}
			Map<String,String> styleMap = new HashMap<String,String>();
			styleMap.put("BACKGROUND-COLOR", color);
			String style = ReportCssHelper.getStyleStringFromMap(styleMap);
			canvas
				.td()
					.span(style(style)).content(header)
				._td();
		}
			if (ConfigurationsStore.showLineNumbers) {
				canvas.td()
						.span(style(ReportCssHelper
								.getStyleString(new GeneralInfoMessage())))
						.content("---line no " + lineNumber)._td();
			}
		canvas._tr();

	}


	public void createTableFromRowList(HtmlCanvas canvas,
			List<RowElement> entries, String header,
			SectionInternalHederLevelOne sectionStyle, boolean showEmptyMessage) throws IOException {
		canvas
		.table();
		canvas
				.tr()
					.td(style(ReportCssHelper.getStyleString(sectionStyle)).colspan("10000")).content(header)
				._tr();
		for (RowElement rowElement : entries) {
			createRowFromList(canvas, rowElement.getRowEntries(), null, ReportVariables.WHITE_COLOR,rowElement.getLineNumber());
		}
		if(entries.isEmpty() && showEmptyMessage) {
			canvas
				.tr()
					.td(style(ReportCssHelper.getStyleString(new GeneralInfoMessage()))).content("none..")
				._tr();
		}
		canvas
			._table();
		
	}
	
}
