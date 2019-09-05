package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BreastFeedingDao;
import com.example.demo.entity.ReturnMsg;
import com.example.demo.utils.HelpUtils;
import com.mysql.cj.util.StringUtils;

@RestController
@RequestMapping("breastFeeding")
public class BreastFeedingController {

	@Autowired
	private BreastFeedingDao breastFeedingDao;

	@RequestMapping("getDayData")
	public ReturnMsg getDayData(String time,String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		List<Map<String, Object>> list = breastFeedingDao.getDayData(time, deviceId);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			map.put("typeName", HelpUtils.getTypeName((int) map.get("type"), (int) map.get("volume")));

		}
		HelpUtils.getNumberVisits("getDayData", request);
		return new ReturnMsg(200, list);
	}

	@RequestMapping(value = "insertIntoData", method = RequestMethod.POST)
	public ReturnMsg insertIntoData(String type, String volume,String time,String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		Boolean isOk = breastFeedingDao.insertIntoData(type, volume, time, deviceId);
		if (isOk) {
			HelpUtils.getNumberVisits("insertIntoData", request);
			return new ReturnMsg(200, "Success");
		} else {
			System.out.println("访问insertIntoData 插入数据失败");
			return new ReturnMsg(500, "fail");
		}
	}

	@RequestMapping("getIntervalTime")
	public ReturnMsg getIntervalTime(String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		Map<String, Object> list = breastFeedingDao.getIntervalTime(deviceId);
		List<Map<String, Object>> totaList = breastFeedingDao.getTotalData(deviceId);
		if (totaList.size() > 0) {
			String listTime = (String) totaList.get(0).get("time");
			String timeString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			list.put("totalvolume", timeString.equals(listTime) ? totaList.get(0).get("volumes") : "0");
		}
		HelpUtils.getNumberVisits("getIntervalTime", request);
		return new ReturnMsg(200, list);
	}

	@RequestMapping("getTotalData")
	public ReturnMsg getTotalData(String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		List<Map<String, Object>> list = breastFeedingDao.getTotalData(deviceId);
		HelpUtils.getNumberVisits("getTotalData", request);
		return new ReturnMsg(200, list);

	}

	@RequestMapping(value = "deleteDate", method = RequestMethod.GET)
	public ReturnMsg deleteDate(String id, HttpServletRequest request) {
		Boolean isOk = breastFeedingDao.deleteDate(id);
		if (isOk) {
			HelpUtils.getNumberVisits("deleteDate", request);
			return new ReturnMsg(200, "Success");
		} else {
			System.out.println("访问 deleteDate 删除数据失败");
			return new ReturnMsg(500, "fail");
		}

	}

}
