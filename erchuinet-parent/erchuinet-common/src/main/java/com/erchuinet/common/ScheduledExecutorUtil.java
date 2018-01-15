package com.erchuinet.common;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

public class ScheduledExecutorUtil {
 
	  private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	  
	    public static void scheduledExecutorService( long initialDelay, long period,final HttpSession session ,final String key){
	        Runnable runnable = new Runnable() {  
	            public void run() {  
	                Integer count=(Integer) session.getAttribute("sendNumber");
	                if(count>0){
	                	session.removeAttribute(key);
	                	if(!scheduler.isShutdown()){
	                		scheduler.shutdown();
	                	}
	                }
	            }  
	        };  
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        scheduler.scheduleAtFixedRate(runnable, 60, 1, TimeUnit.SECONDS);
	    }
	    public static void task(long delay,long intevalPeriod,final HttpSession session ,final String key){
	    	final Timer timer = new Timer();  
	    	  TimerTask task = new TimerTask() {  
	             @Override  
	             public void run() {  
	            	        try {
	            	        	session.removeAttribute(key);
	            	        	timer.cancel();
							} catch (Exception e) {
							}
	             }  
	         };  
 
	         timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	    }
	
}
