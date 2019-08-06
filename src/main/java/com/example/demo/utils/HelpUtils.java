package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HelpUtils {
	private static Map<String, Long> numberVisits = new HashMap<String, Long>();
	private static Map<String, Date> numberDates = new HashMap<String, Date>();
	private static Map<String, String> numberIps = new HashMap<String, String>();
	
	public static Map<Integer, String> typeMaps = new HashMap<Integer,String>();
	

	public static String getTypeName(int type, int volume) {
		if (typeMaps.containsKey(type)) {
			if (volume == 3 && type == 3) {
				return "鱼肝油/" +volume + "ml";
			}else {
				return typeMaps.get(type)+"/" + (volume==0?"未知":(volume + "ml"));
			}
		}else {
			if (type == 1) {
				return "奶粉/" + (volume==0?"未知":(volume + "ml"));
			} else if (type == 2) {
				return "母乳/" + (volume==0?"未知":(volume + "ml"));
			} else if (type == 3) {
				if (volume == 3) {
					return "鱼肝油/" +volume + "ml";
				} else {
					return "补钙/" + (volume==0?"未知":(volume + "ml"));
				}
			} else if (type == 4) {
				return "喝水/" + (volume==0?"未知":(volume + "ml"));
			}else if(type == 5) {
				return "益生菌/" + (volume==0?"未知":(volume + "ml"));
			}
			return "";
		}
	}
	
	public static String getTypeName(int type, int volume,int position) {
		switch (position) {
		case 0:
			return "快去";
		case 1:
			return "找找";
		case 2:
			return "看";
		case 3:
			return "说不定";
		case 4:
			return "家里有";
		case 5:
			return "惊喜哦";

		}
		if (typeMaps.containsKey(type)) {
			if (volume == 3 && type == 3) {
				return "鱼肝油/" +volume + "ml";
			}else {
				return typeMaps.get(type)+"/" + (volume==0?"未知":(volume + "ml"));
			}
		}else {
			if (type == 1) {
				return "奶粉/" + (volume==0?"未知":(volume + "ml"));
			} else if (type == 2) {
				return "母乳/" + (volume==0?"未知":(volume + "ml"));
			} else if (type == 3) {
				if (volume == 3) {
					return "鱼肝油/" +volume + "ml";
				} else {
					return "补钙/" + (volume==0?"未知":(volume + "ml"));
				}
			} else if (type == 4) {
				return "喝水/" + (volume==0?"未知":(volume + "ml"));
			}else if(type == 5) {
				return "益生菌/" + (volume==0?"未知":(volume + "ml"));
			}
			return "";
		}
	}


	public static long getNumberVisits(String name, HttpServletRequest request) {
//		long i = 1;
//		if (numberVisits.containsKey(name)) {
//			i = numberVisits.get(name) + 1;
//		}
//		numberVisits.put(name, i);
//		numberDates.put(name, new Date());
//		numberIps.put(name, IpUtil.getIpAddr(request));
//
//		System.out.print(getOutMsg());
		return 0;
	}

	private static String getOutMsg() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" ------------------------------------------------------------------------------ ");
		buffer.append("|       接口名称       |       时间       |        最后ip        |   访问次数  |");
		for (String key : numberVisits.keySet()) {
			buffer.append(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- ");
			buffer.append(getNameString(key));
			buffer.append(getTimeString(key));
			buffer.append(getIpString(key));
			buffer.append(getNumberString(key));
		}
		buffer.append(" ------------------------------------------------------------------------------ ");
		clearConsole();
		return buffer.toString();
	}

	private static String getNameString(String name) { // 25
		StringBuffer buffer = new StringBuffer();
		buffer.append("|    " + name);
		for (int i = 0; i < 24 - (name.length() + 6); i++) {
			buffer.append(" ");
		}
		return buffer.toString();
	}

	private static String getTimeString(String name) { // 13
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		if (numberDates.containsKey(name)) {
			Date date = numberDates.get(name);
			String dateString = formatter.format(date);
			return "|     " + dateString + "     ";
		} else {
			return "|     00:00:00     ";
		} 
	}

	private static String getIpString(String name) {// 225.225.225.225 25
		StringBuffer buffer = new StringBuffer();
		String ip = numberIps.get(name);
		if (ip == null || "".equals(ip)) {
			ip = "0.0.0.0";
		}
		buffer.append("|    " + ip);
		for (int i = 0; i < 24 - (ip.length() + 6); i++) {
			buffer.append(" ");
		}
		return buffer.toString();
	}

	private static String getNumberString(String name) { // 19
		StringBuffer buffer = new StringBuffer();
		String num = "0";
		if (numberVisits.containsKey(name)) {
			num = numberVisits.get(name) + "";
		}
		buffer.append("|    " + num);
		for (int i = 0; i < 19 - (num.length() + 10); i++) {
			buffer.append(" ");
		}
		buffer.append("|");
		return buffer.toString();
	}

	private static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls")
		    //将 ProcessBuilder 对象的输出管道和 Java 的进程进行关联，这个函数的返回值也是一个 ProcessBuilder
		    .inheritIO()
		    //开始执行 ProcessBuilder 中的命令
		    .start()
		    //等待 ProcessBuilder 中的清屏命令执行完毕
		    //如果不等待则会出现清屏代码后面的输出被清掉的情况
		    .waitFor(); // 清屏命
		} catch (Exception exception) {
			// Handle exception.
		}
	}
	
	public static void setTypelList(List<Map<String, Object>> typelList) {
		if (typelList == null) return;
		for (Map<String, Object> map:typelList) {
			typeMaps.put((Integer)map.get("id"), (String)map.get("name"));
		}
	}

}
