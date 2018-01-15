package com.erchuinet.task;
/*package com.erchuinet.app.common.task;

import org.springframework.scheduling.annotation.Scheduled; 
import org.springframework.stereotype.Component; 
 
*//**
 * 基于注解的定时器 
 *//*
@Component
public class TestTask { 
 
  *//**
   * 定时计算。每天凌晨 01:00 执行一次
   *//*
  @Scheduled(cron = "0 0 1 * * *")
  public void show() {
    System.out.println("show method 2"); 
  } 
 
  *//**
   * 启动时执行一次，之后每隔2秒执行一次 
   *//*
  @Scheduled(fixedRate = 1000*2)  
  public void print() { 
    System.out.println("print method 2");
  }*/

 /*    @Scheduled(cron = "5 * * * * ?//")
//    public void test() throws Exception {
  //      System.out.println("Test is working......");
    //}


    //@Scheduled(cron = "0 0 1 * * ?")//每天凌晨1点整
    //@Scheduled(cron = "0 30 0 * * ?")//每天凌晨0点30分
//    //@Scheduled(cron = "0 60 * * * ?")//1小时处理一次
//
//   }
*/
