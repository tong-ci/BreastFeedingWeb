package com.example.demo.entity;

import com.alibaba.fastjson.JSON;

public class ReturnMsg {
	
	private int code;
	private Object data;
	private String msg;
	
	public ReturnMsg(int code,Object data) {
		this.code = code;
		this.data = data;
	}
	
	public ReturnMsg(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(ReturnMsg.this);
	}

}
