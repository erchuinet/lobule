package com.erchuinet.task;
/*package com.erchuinet.app.common.task;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
@Configuration
@EnableAsync
@EnableScheduling
public class ToTimer{
	
//	@Resource
	
	
	public void work() {
        System.out.println("Quartz的任务调度！"+new Date().getTime());
    }
	
	
	@Scheduled(initialDelay=5000,fixedDelay=2000)
    public void doSomething() {
        System.out.println("do some thing...");
    }
    
    *//*****
     * 格式: [秒] [分] [小时] [日] [月] [周] [年]
        0 0 12 * * ?           每天12点触发
        0 15 10 ? * *          每天10点15分触发
        0 15 10 * * ?          每天10点15分触发 
        0 15 10 * * ? *        每天10点15分触发 
        0 15 10 * * ? 2005     2005年每天10点15分触发
        0 * 14 * * ?           每天下午的 2点到2点59分每分触发
        0 0/5 14 * * ?         每天下午的 2点到2点59分(整点开始，每隔5分触发) 
        0 0/5 14,18 * * ?        每天下午的 18点到18点59分(整点开始，每隔5分触发)
        
        0 0-5 14 * * ?            每天下午的 2点到2点05分每分触发
        0 10,44 14 ? 3 WED        3月分每周三下午的 2点10分和2点44分触发
        0 15 10 ? * MON-FRI       从周一到周五每天上午的10点15分触发
        0 15 10 15 * ?            每月15号上午10点15分触发
        0 15 10 L * ?             每月最后一天的10点15分触发
        0 15 10 ? * 6L            每月最后一周的星期五的10点15分触发
        0 15 10 ? * 6L 2002-2005  从2002年到2005年每月最后一周的星期五的10点15分触发
        
        0 15 10 ? * 6#3           每月的第三周的星期五开始触发
        0 0 12 1/5 * ?            每月的第一个中午开始每隔5天触发一次
        0 11 11 11 11 ?           每年的11月11号 11点11分触发(光棍节)
     *//*
    @Scheduled(cron="0 0-15 20 * * ?")
    public void doSomeWork(){
        System.out.println("do some work om time......");
    }
	*//**
	 * 
	 *//*
    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次    
    public void myTest(){    
          System.out.println("进入测试");    
    }    
	
}
*/