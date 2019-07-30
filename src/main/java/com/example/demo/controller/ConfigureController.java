package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.ConfigureDao;
import com.example.demo.entity.ConfigureEntity;
import com.example.demo.entity.ReturnMsg;
import com.example.demo.utils.HelpUtils;

@RestController
@RequestMapping("/configure")
public class ConfigureController {

    @Autowired
    private ConfigureDao ConfigureDao;
    
    @RequestMapping("/getAll")
    public String getThreeForMessage(HttpServletRequest request){
        try {
        	List<ConfigureEntity> list = ConfigureDao.getAllData();
        	HelpUtils.getNumberVisits("configureAll", request);
        	 return new ReturnMsg(200, list).toString();
        }catch (Exception e) {
        	System.out.println("访问 configureAll 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "server error").toString();
		}
    }
    
    @RequestMapping("/deviceIsExist")
    public String deviceIsExist(@RequestParam(value="deviceId") String deviceId,HttpServletRequest request){
        try {
        	int is = ConfigureDao.deviceIsExist(deviceId);
        	HelpUtils.getNumberVisits("deviceIsExist", request);
        	 return new ReturnMsg(200, is).toString();
        }catch (Exception e) {
        	System.out.println("访问 deviceIsExist 失败 错误信息： " + e.toString());
			return new ReturnMsg(500, "server error").toString();
		}
    }
}
