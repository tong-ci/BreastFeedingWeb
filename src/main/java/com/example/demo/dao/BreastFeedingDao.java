package com.example.demo.dao;

import java.util.List;
import java.util.Map;


public interface BreastFeedingDao {
	
	List<Map<String, Object>> getDayData(String time);

	Boolean insertIntoData(String type,String volume,String time);
	
	Map<String,Object> getIntervalTime();
	
	List<Map<String, Object>> getTotalData();
	
	Boolean deleteDate(String id);
	
}
