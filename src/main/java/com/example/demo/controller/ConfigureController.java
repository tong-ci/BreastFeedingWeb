package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String getThreeForMessage(@RequestParam(value="deviceId") String deviceId,HttpServletRequest request){
        try {
        	List<ConfigureEntity> list = ConfigureDao.getAllData();
        	HelpUtils.getNumberVisits("configureAll", request);
        	if ("865883040575139".equals(deviceId) || "864688033424919".equals(deviceId)) {
				
			}else {
				int pos = -1;
				for (int i = 0; i < list.size(); i++) {
					if ("surprised".equals(list.get(i).getKey())) {
						pos = i;
						break;
					}
				}
				list.remove(pos);
			}
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
        	if (is==0) {
				System.out.println("新设备访问 deviceId="+deviceId);
			}
        	HelpUtils.getNumberVisits("deviceIsExist", request);
        	 return new ReturnMsg(200, is).toString();
        }catch (Exception e) {
        	System.out.println("访问 deviceIsExist 失败 deviceId="+deviceId+" 错误信息： " + e.toString());
			return new ReturnMsg(500, "server error").toString();
		}
    }
}
