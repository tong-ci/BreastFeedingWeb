package com.example.demo.dao;

import java.util.List;
import java.util.Map;


public interface BreastFeedingDao {
	
	List<Map<String, Object>> getDayData(String time,String deviceId);

	Boolean insertIntoData(String type,String volume,String time,String deviceId);
	
	Map<String,Object> getIntervalTime(String deviceId);
	
	List<Map<String, Object>> getTotalData(String deviceId);
	
	Boolean deleteDate(String id);
	
}
