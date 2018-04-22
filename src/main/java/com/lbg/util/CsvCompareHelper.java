package com.lbg.util;

import java.util.Collection;

import com.lbg.config.ConfigurationsStore;
import com.lbg.exception.ConfigNotFoundException;

public class CsvCompareHelper {

	public static void checkForConfigFile() throws ConfigNotFoundException{
		if(!ConfigurationsStore.configFileFound){
			throw new ConfigNotFoundException();
		}
	}
	
	public static boolean isNullorEmpty(Collection collection){
		return (collection==null) || collection.isEmpty();
	}
	
}
