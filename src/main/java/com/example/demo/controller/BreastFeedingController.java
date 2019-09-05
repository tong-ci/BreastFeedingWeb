package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

<<<<<<< HEAD
	@RequestMapping("getDayData")
	public ReturnMsg getDayData(String time,String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		List<Map<String, Object>> list = breastFeedingDao.getDayData(time, deviceId);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			map.put("typeName", HelpUtils.getTypeName((int) map.get("type"), (int) map.get("volume")));
=======
	@RequestMapping("/getDayData")
	public ReturnMsg getDayData(@RequestParam(value = "time") String time,
			@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request) {
    	if(StringUtils.isNullOrEmpty(deviceId)) {
    		System.out.println("访问 insertIntoData 失败 错误信息： deviceId为空");
    		return new ReturnMsg(401, "deviceId is null");
    	}
		try {
			List<Map<String, Object>> list = breastFeedingDao.getDayData(time, deviceId);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				map.put("typeName", HelpUtils.getTypeName((int) map.get("type"), (int) map.get("volume")));
>>>>>>> 4702e3a67a448770e208813f02c60883e74e84f1

		}
		HelpUtils.getNumberVisits("getDayData", request);
		return new ReturnMsg(200, list);
	}

<<<<<<< HEAD
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
=======
	@RequestMapping(value = "/insertIntoData", method = RequestMethod.POST)
	public ReturnMsg insertIntoData(@RequestParam(value = "type") String type,
			@RequestParam(value = "volume") String volume, @RequestParam(value = "time") String time,
			@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request) {
    	if(StringUtils.isNullOrEmpty(deviceId)) {
    		System.out.println("访问 insertIntoData 失败 错误信息： deviceId为空");
    		return new ReturnMsg(401, "deviceId is null");
    	}
		try {
			Boolean isOk = breastFeedingDao.insertIntoData(type, volume, time, deviceId);
			if (isOk) {
				HelpUtils.getNumberVisits("insertIntoData", request);
				return new ReturnMsg(200, "Success");
			} else {
				System.out.println("访问insertIntoData 插入数据失败");
				return new ReturnMsg(500, "fail");
			}
		} catch (Exception e) {
			System.out.println("访问insertIntoData 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
>>>>>>> 4702e3a67a448770e208813f02c60883e74e84f1
		}
	}

<<<<<<< HEAD
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
=======
	@RequestMapping("/getIntervalTime")
	public ReturnMsg getIntervalTime(@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request) {
    	if(StringUtils.isNullOrEmpty(deviceId)) {
    		System.out.println("访问 getIntervalTime 失败 错误信息： deviceId为空");
    		return new ReturnMsg(401, "deviceId is null");
    	}
		try {
			Map<String, Object> list = breastFeedingDao.getIntervalTime(deviceId);
			List<Map<String, Object>> totaList = breastFeedingDao.getTotalData(deviceId);
			if (totaList.size() > 0) {
				list.put("totalvolume", totaList.get(0).get("volumes"));
			}
			HelpUtils.getNumberVisits("getIntervalTime", request);
			return new ReturnMsg(200, list);
		} catch (Exception e) {
			System.out.println("访问getIntervalTime 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
>>>>>>> 4702e3a67a448770e208813f02c60883e74e84f1
		}
		HelpUtils.getNumberVisits("getIntervalTime", request);
		return new ReturnMsg(200, list);
	}

<<<<<<< HEAD
	@RequestMapping("getTotalData")
	public ReturnMsg getTotalData(String deviceId, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(deviceId)) {
			return new ReturnMsg(401, "deviceId is no");
		}
		List<Map<String, Object>> list = breastFeedingDao.getTotalData(deviceId);
		HelpUtils.getNumberVisits("getTotalData", request);
		return new ReturnMsg(200, list);
=======
	@RequestMapping("/getTotalData")
	public ReturnMsg getTotalData(@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request) {
    	if(StringUtils.isNullOrEmpty(deviceId)) {
    		System.out.println("访问 getTotalData 失败 错误信息： deviceId为空");
    		return new ReturnMsg(401, "deviceId is null");
    	}
		try {
			List<Map<String, Object>> list = breastFeedingDao.getTotalData(deviceId);
			HelpUtils.getNumberVisits("getTotalData", request);
			return new ReturnMsg(200, list);
		} catch (Exception e) {
			System.out.println("访问 getTotalData 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
>>>>>>> 4702e3a67a448770e208813f02c60883e74e84f1

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
