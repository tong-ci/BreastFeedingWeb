package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AmountDao;
import com.example.demo.entity.AmountEntity;
import com.example.demo.entity.ReturnMsg;
import com.example.demo.utils.HelpUtils;

@RestController
@RequestMapping("/amount")
public class AmountController {

	@Autowired
	private AmountDao amountDao;

	@RequestMapping("/getAll")
	public String getThreeForMessage(HttpServletRequest request) {
		List<AmountEntity> list = amountDao.getAllData();
		HelpUtils.getNumberVisits("amountAll", request);
		return new ReturnMsg(200, list).toString();
	}

	@RequestMapping("/getTypeAllData")
	public String getTypeAllData(HttpServletRequest request) {
		List<Map<String, Object>> list = amountDao.getTypeAllData();
		HelpUtils.setTypelList(list);
		HelpUtils.getNumberVisits("getTypeAllData", request);
		return new ReturnMsg(200, list).toString();
	}

}
