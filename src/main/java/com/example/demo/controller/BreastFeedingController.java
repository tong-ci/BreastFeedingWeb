package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BreastFeedingDao;
import com.example.demo.entity.ReturnMsg;
import com.example.demo.utils.HelpUtils;

@RestController
@RequestMapping("/breastFeeding")
public class BreastFeedingController {

    @Autowired
    private BreastFeedingDao breastFeedingDao;
    
    @RequestMapping("/getDayData")
    public ReturnMsg getDayData(@RequestParam(value="time") String time,HttpServletRequest request) {
    	
    	try {
			List<Map<String, Object>> list = breastFeedingDao.getDayData(time);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				map.put("typeName", HelpUtils.getTypeName((int)map.get("type"),(int)map.get("volume")));
				
			}
			HelpUtils.getNumberVisits("getDayData", request);
			return new ReturnMsg(200, list);
		} catch (Exception e) {
			System.out.println("访问 getDayData 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
    	
    }
    
    @RequestMapping(value= "/insertIntoData",method = RequestMethod.POST)
    public ReturnMsg insertIntoData(@RequestParam(value="type") String type,@RequestParam(value="volume")  String volume,@RequestParam(value="time")  String time,HttpServletRequest request) {
    	
    	try {
			Boolean isOk = breastFeedingDao.insertIntoData(type,volume,time);
			if (isOk) {
				HelpUtils.getNumberVisits("insertIntoData", request);
				return new ReturnMsg(200, "Success");
			}else {
				System.out.println("访问insertIntoData 插入数据失败");
				return new ReturnMsg(500, "fail");
			}
		} catch (Exception e) {
			System.out.println("访问insertIntoData 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
    	
    }
    
    @RequestMapping("/getIntervalTime")
    public ReturnMsg getIntervalTime(HttpServletRequest request) {
    	
    	try {
			Map<String, Object> list = breastFeedingDao.getIntervalTime();
			List<Map<String, Object>>  totaList = breastFeedingDao.getTotalData();
			if (totaList.size()>0) {
				list.put("totalvolume", totaList.get(0).get("volumes"));
			}
			HelpUtils.getNumberVisits("getIntervalTime", request);
			return new ReturnMsg(200, list);
		} catch (Exception e) {
			System.out.println("访问getIntervalTime 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
    	
    }
    
    @RequestMapping("/getTotalData")
    public ReturnMsg getTotalData(HttpServletRequest request) {
    	
    	try {
			List<Map<String, Object>> list = breastFeedingDao.getTotalData();
			HelpUtils.getNumberVisits("getTotalData", request);
			return new ReturnMsg(200, list);
		} catch (Exception e) {
			System.out.println("访问 getTotalData 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
    	
    }
    
    @RequestMapping(value= "/deleteDate",method = RequestMethod.GET)
    public ReturnMsg deleteDate(@RequestParam(value="id") String id,HttpServletRequest request) {
    	
    	try {
			Boolean isOk = breastFeedingDao.deleteDate(id);
			if (isOk) {
				HelpUtils.getNumberVisits("deleteDate", request);
				return new ReturnMsg(200, "Success");
			}else {
				System.out.println("访问 deleteDate 删除数据失败");
				return new ReturnMsg(500, "fail");
			}
		} catch (Exception e) {
			System.out.println("访问 deleteDate 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "Server error");
		}
    	
    }

}
