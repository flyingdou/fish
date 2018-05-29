package com.fish.test;

import com.fish.pojo.City;
import com.fish.util.JSON2Bean;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.fluentPut("id", 1)
//		          .fluentPut("city", "武汉")
//		          .fluentPut("longitude", 114.36)
//		          .fluentPut("latitude", 30.15)
//		          .fluentPut("create", new Date())
//		          .fluentPut("updateDate", new Date())
//		          ;
		City city = new City();
		JSONObject jsonObject = new JSONObject();
		  jsonObject.accumulate("id", 1)
			        .accumulate("city", "武汉")
			        .accumulate("longitude", 114.36)
			        .accumulate("latitude", 30.15)
			        ;
		  String jsonStr = jsonObject.toString();
		JSON2Bean json2 = new JSON2Bean();
		city = (City) json2.json2City(jsonStr, City.class);
//		city = (City) JsonUtils.JsonObjctToBean(jsonObject, City.class);
		
		
		System.err.println(city.getCity());
		
	}
	
	
	
	
	
	
	
}
