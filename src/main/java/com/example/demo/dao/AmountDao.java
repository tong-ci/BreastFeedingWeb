package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.AmountEntity;

public interface AmountDao {
	
	List<AmountEntity> getAllData();
	
	List<Map<String, Object>> getTypeAllData();

}
