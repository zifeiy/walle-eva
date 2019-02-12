package com.anbank.eva.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.anbank.eva.service.TaskService;

@Component
public class TaskComponent {
	
	@Autowired
	private TaskService taskService;
	
	@Scheduled(cron="0 10 * * * *")	 // you can run cron job here
    public void call_P_RPT_TYSH_JL_ZD_DETAIL() {
        System.out.println("cron job 1: " + new Date());
        this.taskService.sayHello();
    }
	
	@Scheduled(cron="0 10-22/6 * * * *") // you can run many cron jobs here
	public void run_DK004() {
		System.out.println("cron job 2: " + new Date());
        this.taskService.sayByebye();
	}
	
}
