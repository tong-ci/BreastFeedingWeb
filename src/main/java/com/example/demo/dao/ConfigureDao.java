package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.ConfigureEntity;

public interface ConfigureDao {
	
	List<ConfigureEntity> getAllData();
	
	int deviceIsExist(String deviceId);
}
