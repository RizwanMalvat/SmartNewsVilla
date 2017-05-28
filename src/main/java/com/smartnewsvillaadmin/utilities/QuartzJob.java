///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.smartnewsvillaadmin.utilities;
//
//import com.muddyapp.entities.MaFans;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import net.sf.json.JSONObject;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Component
//public class QuartzJob implements org.quartz.Job {
//
//    public QuartzJob() {
//    }
//
//    @Override
//    @Transactional
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobKey key = context.getJobDetail().getKey();
//
//        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//
//        Long artistid = dataMap.getLong("artistid");
//        Long festivalid = dataMap.getLong("festivalid");
//        String stagename = dataMap.getString("stagename");
//        String artistname = dataMap.getString("artistname");
//        List<MaFans> maFansAndroid = new ArrayList<>();
//        List<MaFans> maFansIos = new ArrayList<>();
//        try {
//            String url = "jdbc:postgresql://localhost:5432/Muddy";
////            String url = "jdbc:postgresql://192.168.1.13:5432/Muddy";
////            Connection conn = DriverManager.getConnection(url, "postgres", "mama");
//            Connection conn = DriverManager.getConnection(url, "postgres", "MA@786ma");
//            Statement stmt = conn.createStatement();
//            ResultSet rs;
//            rs = stmt.executeQuery("SELECT * FROM ma_my_schedule where artistid=" + artistid + " and festivalid=" + festivalid + " and status='Active'");
//            while (rs.next()) {
//                Long fanid = rs.getLong("fanid");
//                Statement stmt1 = conn.createStatement();
//                ResultSet rs1;
//                rs1 = stmt1.executeQuery("SELECT * FROM ma_fans where fanid=" + fanid + " and authstatus='Active'");
//                while (rs1.next()) {
////                    if (rs1.getString("notificationstatus") != null) {
////                        if (rs1.getString("notificationstatus").equals("On")) {
//                    if (rs1.getString("devicetype") != null) {
////                        System.err.println("rs1.getString(\"devicetype\") "+rs1.getString("devicetype"));
////                        System.err.println("rs1.getString(\"fan\") "+rs1.getString("fanid"));
//                        if (rs1.getString("devicetype").equals("Apple")) {
//                            MaFans maFans = new MaFans();
//                            maFans.setDevicetoken(rs1.getString("devicetoken"));
//                            maFansIos.add(maFans);
//                        } else if (rs1.getString("devicetype").equals("Android")) {
//                            MaFans maFans = new MaFans();
//                            maFans.setDevicetoken(rs1.getString("devicetoken"));
//                            maFansAndroid.add(maFans);
//                        }
//                    }
////                        }
////                    }
//                }
//            }
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        JSONObject jSONObjectAndroid = new JSONObject();
//
//        jSONObjectAndroid.put("festivalid", dataMap.getLong("festivalid"));
//        jSONObjectAndroid.put("festivalname", dataMap.getString("festivalname"));
//        jSONObjectAndroid.put("notification", artistname + " is going to be playing live in 15 Mins on the " + stagename + " stage");
//        new Pushnotification().sendToAndroid(maFansAndroid, jSONObjectAndroid.toString());
//
//        new Pushnotification().sendToIphone(maFansIos, artistname + " is going to be playing live in 15 Mins on the " + stagename + " stage");
//        System.out.println("Instance " + key + " of notification");
//
//    }
//}
