package cn.lucode.fastdev.job.utils;


import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.exception.CommonException;
import cn.lucode.fastdev.util.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 *
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
public class ScheduleRunnable implements Runnable {
	
	private Object target;
	private Method method;
	private String params;
	
	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params);
			}else{
				method.invoke(target);
			}
		}catch (Exception e) {
			throw new CommonException(ReturnCodeModel.SCHEDULE_JOB_ERROR);
		}
	}

}
