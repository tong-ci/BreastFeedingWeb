package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.AmountDao;
import com.example.demo.dao.ConfigureDao;
import com.example.demo.entity.AmountEntity;
import com.example.demo.entity.ConfigureEntity;

@Repository	
public class AmountDaoImpl implements AmountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<AmountEntity> getAllData() {
		String sql = "SELECT * FROM amount a WHERE a.show = 0";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(AmountEntity.class));
	}

}
