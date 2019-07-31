package com.example.demo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.AmountDao;
import com.example.demo.entity.AmountEntity;

@Repository	
public class AmountDaoImpl implements AmountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<AmountEntity> getAllData() {
		String sql = "SELECT * FROM amount a WHERE a.show = 1";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<AmountEntity>(AmountEntity.class));
	}

	@Override
	public List<Map<String, Object>> getTypeAllData() {
		String sql = "select id,name FROM nutrition_type WHERE is_show = 1";
		return jdbcTemplate.queryForList(sql);
	}

}
