package com.example.demo.impl;

import java.util.List;

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
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(ConfigureEntity.class));
	}

}
