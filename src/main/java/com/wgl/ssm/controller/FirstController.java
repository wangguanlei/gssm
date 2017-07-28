package com.wgl.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wgl.ssm.annotation.Log;
import com.wgl.ssm.annotation.SystemControllerLog;
import com.wgl.ssm.service.IFirstService;

@Controller
@RequestMapping("/first")
public class FirstController{
	
	@Resource
	private IFirstService firstService;
	
	@Log(name="您访问了first方法")
	@ResponseBody
	@RequestMapping(value="/f",method=RequestMethod.GET)
	public String f(){
		return "123456";
	}
	
	@RequestMapping(value="/f0",method=RequestMethod.GET)
	public String f0(){
		return "123456";
	}
	
	@Log(name="您访问了first1方法")
	@ResponseBody
	@RequestMapping(value="/f1",method=RequestMethod.GET)
	public Map<String,Object> f1(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("f", 1);
		map.put("s", 2);
		return map;
	}
	
	@SystemControllerLog(description="f2") 
	@RequestMapping(value="/f2",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> f2(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("f", 1);
		map.put("s", 2);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.FORBIDDEN);
	}
	
	@SystemControllerLog(description="f3333333333333333333") 
	@RequestMapping(value="/f3",method=RequestMethod.GET)
	public ResponseEntity<Object> f3() throws Exception{
		System.out.println("firstController->f3");
		List<Map<String,Object>> dataList = this.firstService.list();
		return new ResponseEntity<Object>(dataList,HttpStatus.ACCEPTED);
	}

}
