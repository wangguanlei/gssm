package com.wgl.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.wgl.ssm.model.SystemLog;
import com.wgl.ssm.service.ISystemLogService;

@Service(value="systemLogService")
public class SystemLogServiceImpl implements ISystemLogService{

	@Override
	public void save(SystemLog log) {
		// TODO Auto-generated method stub
		System.out.println("SystemLogServiceImpl.save");
	}

}
