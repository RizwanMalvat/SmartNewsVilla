///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.smartnewsvillaadmin.utilities;
//
//import com.google.android.gcm.server.Message;
//import com.google.android.gcm.server.MulticastResult;
//import com.google.android.gcm.server.Sender;
//import com.google.common.collect.Lists;
//import com.smartnewsvillaadmin.entities.MaFans;
//import com.smartnewsvillaadmin.services.FansService;
//import com.notnoop.apns.APNS;
//import com.notnoop.apns.ApnsNotification;
//import com.notnoop.apns.ApnsService;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//public class Pushnotification {
//
////
////    public void sendToApple(List<MaFans> maFans, FansService fansService, String message) {
////
////        try {
////            // get the certificate
////            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("WenderCastPush-1.p12");
////            // service = APNS.newService().withCert(certStream, "12345").withSandboxDestination().build();
////            ApnsService pushService = APNS.newService()
////                    .withCert(certStream, "12345")
////                    .withSandboxDestination()
////                    .build();
////
////            for (MaFans maFan : maFans) {
////                if (!(maFan.getDevicetoken() == null || maFan.getDevicetoken().trim().equals(""))) {
////                    if (maFan.getNotificationstatus().equalsIgnoreCase("On")) {
////                        try {
////                            String payload = APNS.newPayload().alertBody(message).build();
////                            String token = maFan.getDevicetoken();
////                            pushService.push(token, payload);
////
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
////                    }
////                }
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        } finally {
////
////        }
////
////    }
//    public void sendToIphone(List<MaFans> maFans, String message) {
//        try {
//
//            if (maFans != null) {
//                for (MaFans maFan : maFans) {
//                    if (!(maFan.getDevicetoken() == null || maFan.getDevicetoken().trim().equals(""))) {
//                        // if (maFan.getNotificationstatus().equalsIgnoreCase("On")) {
//                        InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("WenderCastPush.p12");
//                        ApnsService service
//                                = APNS.newService()
//                                .withCert(certStream, "12345")
//                                .withProductionDestination()
//                                .build();
//
//                        String payload = APNS.newPayload().alertBody(message).badge(Integer.parseInt(maFan.getBadge().toString())).build();
//
//                        String token = maFan.getDevicetoken();
//                        ApnsNotification apnsNotification = service.push(token, payload);
//
//                        System.out.println("dt===> " + maFan.getDevicetoken());
//                        System.out.println("getIdentifier===> " + apnsNotification.getIdentifier());
////                        Map<String, Date> inactiveDevices = service.getInactiveDevices();
////                        for (String deviceToken : inactiveDevices.keySet()) {
////                            System.out.println("Inactive===> " + deviceToken);
////                            Date inactiveAsOf = inactiveDevices.get(deviceToken);
////
////                        }
//                        service = null;
//                        payload = null;
//                        apnsNotification = null;
//                        certStream = null;
//                        token = null;
//                    }
//                    //  }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
////    public void sendToIphone(List<MaFans> maFans, FansService fansService, String message) {
////        try {
////            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("WenderCastPush-1.p12");
////            ApnsService service
////                    = APNS.newService()
////                    .withCert(certStream, "12345")
////                    .withSandboxDestination() 
////                   // .withProductionDestination() // or 
////                    .build();
////            for (MaFans maFan : maFans) {
////                if (!(maFan.getDevicetoken() == null || maFan.getDevicetoken().trim().equals(""))) {
////                    if (maFan.getNotificationstatus().equalsIgnoreCase("On")) {
////                        String payload
////                                = APNS.newPayload()
////                                .alertBody(message)
////                                .badge(45)
////                                .sound("default")
////                                .build();
////                        String deviceToken = maFan.getDevicetoken();
////                        service.push(deviceToken, payload);
////                    }
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    public void sendToAndroid(List<MaFans> maFans, String strMessage) {
//
//        MulticastResult results = null;
//        List<String> ids = new ArrayList<>();
//        if (maFans != null) {
//            for (MaFans maFan : maFans) {
//                if (!(maFan.getDevicetoken() == null || maFan.getDevicetoken().trim().equals(""))) {
//                    //if (maFan.getNotificationstatus().equalsIgnoreCase("On")) {
//                    ids.add(maFan.getDevicetoken());
//                    // }
//                }
//            }
//        }
//
//        try {
//            if (!ids.isEmpty()) {
////partition list in 100
//                List<List<String>> devices = Lists.partition(ids, 100);
//                System.out.println("-------------Start Pushnotification-------------");
//                System.out.println("-------------Total Partiotion-------------");
//                System.out.println(devices);
//                for (List<String> list : devices) {
//                    String GOOGLE_SERVER_KEY = "AIzaSyDenQVFoLrVsOgLmZTZiu_1imtTqyFVaAM";
//                    String MESSAGE_KEY = "muddymessage";
//
//                    Sender sender = new Sender(GOOGLE_SERVER_KEY);
//                    Message message = new Message.Builder().timeToLive(30).addData(MESSAGE_KEY, strMessage).priority(Message.Priority.HIGH).build();
//
//                    results = sender.send(message, list, 10);
//                    //System.out.println("======GCM========");
//                   
//                    System.out.println("======ids========" + list);
//                     System.out.println("======results========" + results);
//                }
//                System.out.println("-------------End Pushnotification-------------");
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendToAndroid2(List<MaFans> maFans, String strMessage) {
//        synchronized (Pushnotification.class) {
//            MulticastResult results = null;
//            List<String> ids = new ArrayList<>();
//            if (maFans != null) {
//                for (MaFans maFan : maFans) {
//                    if (!(maFan.getDevicetoken() == null || maFan.getDevicetoken().trim().equals(""))) {
//                        //if (maFan.getNotificationstatus().equalsIgnoreCase("On")) {
//                        ids.add(maFan.getDevicetoken());
//                        // }
//                    }
//                }
//            }
//            try {
//                if (!ids.isEmpty()) {
//                    String GOOGLE_SERVER_KEY = "AIzaSyDenQVFoLrVsOgLmZTZiu_1imtTqyFVaAM";
//                    String MESSAGE_KEY = "muddymessage";
//
//                    Sender sender = new Sender(GOOGLE_SERVER_KEY);
//                    Message message = new Message.Builder().timeToLive(30).addData(MESSAGE_KEY, strMessage).priority(Message.Priority.HIGH).build();
//
//                    results = sender.send(message, ids, 30);
//                    System.out.println("======GCM========");
//                    System.out.println("======results========" + results);
//                    System.out.println("======ids========" + ids);
//                }
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    private void deleteInactiveDevices(ApnsService service, FansService fs) {
//        // get the list of the devices that no longer have your app installed from apple
//        //ignore the ="" after Date here, it's a bug...
//        Map<String, Date> inactiveDevices = service.getInactiveDevices();
//        for (String deviceToken : inactiveDevices.keySet()) {
//            MaFans maFans = fs.findByDevice(deviceToken);
//            maFans.setDevicetoken(null);
//            fs.save(maFans);
//        }
//
//    }
//}
