package com.example.demo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ConfigureDao;
import com.example.demo.entity.ConfigureEntity;

@Repository	
public class ConfigureDaoImpl implements ConfigureDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<ConfigureEntity> getAllData() {
		String sql = "select * from configure";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ConfigureEntity>(ConfigureEntity.class));
	}

	@Override
	public int deviceIsExist(String deviceId) {
		String sqlStr = "select * from device WHERE device_id ="+deviceId;
		List<Map<String, Object>> list= jdbcTemplate.queryForList(sqlStr);
		if (list.size()<0) {
			return 0;
		}else {
			return (int)list.get(0).get("baby_id");
		}
	}

}
