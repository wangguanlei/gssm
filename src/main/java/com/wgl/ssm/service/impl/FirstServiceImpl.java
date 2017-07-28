package com.wgl.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wgl.ssm.annotation.Log;
import com.wgl.ssm.annotation.SystemServiceLog;
import com.wgl.ssm.dao.FirstMapper;
import com.wgl.ssm.model.User;
import com.wgl.ssm.service.IFirstService;

@Service("firstService")
public class FirstServiceImpl implements IFirstService {
	
	@Resource
	private FirstMapper firstMapper;

//	@Log(name = "您访问了查询list方法")
	@SystemServiceLog(description="lllllllllllllllllllllllllll")
	@Override
	public List<Map<String, Object>> list() throws Exception {
//		User user = null;
//		user.getAge();
		return this.firstMapper.list();
	}

}
