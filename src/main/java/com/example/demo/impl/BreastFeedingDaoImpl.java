package com.example.demo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.BreastFeedingDao;

@Repository
public class BreastFeedingDaoImpl implements BreastFeedingDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getDayData(String time,String deviceId) {
		String sql = "SELECT c.id, c.type, c.volume, c.time, ( timestampdiff(SECOND, ( SELECT e.time FROM breast_feeding e LEFT JOIN device e2 on e.baby_id = e2.baby_id WHERE e2.device_id = '"+deviceId+"' and e.time < c.time and (e.type = 1 OR e.type = 2) ORDER BY e.time DESC LIMIT 0, 1 ) ,c.time ) ) AS intervalTime FROM breast_feeding c LEFT JOIN device c2 on c.baby_id = c2.baby_id WHERE c2.device_id = '"+deviceId+"' and DATE_FORMAT(time,'%Y-%m-%d')='"+time+"' order by time";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public Boolean insertIntoData(String type, String volume, String time,String deviceId) {
		String sql = "insert into breast_feeding (type,volume,time,baby_id,creater_id) values ("+type+","+volume+",'"+time+"',(SELECT baby_id FROM device WHERE device_id = '"+deviceId+"'),(SELECT id FROM device WHERE device_id = '"+deviceId+"'))";
		return jdbcTemplate.update(sql) > 0 ? true : false;
	}

	@Override
	public Map<String, Object> getIntervalTime(String deviceId) {
		String sqlStr = "select timestampdiff(SECOND,( SELECT e.time FROM breast_feeding e LEFT JOIN device e2 on e.baby_id = e2.baby_id WHERE e2.device_id = '"+deviceId+"' and (e.type = 1 OR e.type = 2) ORDER BY e.time DESC LIMIT 0, 1 ),now()) as time";
		return jdbcTemplate.queryForMap(sqlStr);
	}

	@Override
	public List<Map<String, Object>> getTotalData(String deviceId) {
		String sqlStr = "select count(*) as  number, SUM(volume) as volumes,substr(t.time,1,10) as time  from breast_feeding t LEFT JOIN device t2 on t.baby_id = t2.baby_id where t2.device_id='"+deviceId+"' and t.time like '%' group by substr(t.time,1,10) desc";

		return jdbcTemplate.queryForList(sqlStr);
	}

	@Override
	public Boolean deleteDate(String id) {
		String sqlStr = "Delete from breast_feeding where id = "+id+"";
		return jdbcTemplate.update(sqlStr) > 0 ? true : false;
	}

}
