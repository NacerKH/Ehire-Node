package com.ehire.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;


import com.ehire.entities.jobs;
import com.ehire.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class jobsService {

    public static jobsService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<jobs> listAbonnements;

    private jobsService() {
        cr = new ConnectionRequest();
    }

    public static jobsService getInstance() {
        if (instance == null) {
            instance = new jobsService();
        }
        return instance;
    }

    public ArrayList<jobs> getAll() {
        listAbonnements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/joboffers/showAll");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listAbonnements = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAbonnements;
    }

    private ArrayList<jobs> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                jobs jobs = new jobs(
                         (int) Float.parseFloat(obj.get("id").toString()),
                        (String) obj.get("jobDescription"),
                         (int) Float.parseFloat(obj.get("AverageSallary").toString()),
                          (int) Float.parseFloat(obj.get("totalPlaces").toString()),
                          (String) obj.get("Status"),
                          (String) obj.get("category")
                        
                );

                listAbonnements.add(jobs);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listAbonnements;
    }

   

    public int add(jobs jobs) {
        return manage(jobs, false);
    }

    public int edit(jobs jobs) {
        return manage(jobs, true);
    }

    public int manage(jobs jobs, boolean isEdit) {
        cr = new ConnectionRequest();
                
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/abonnement/edit");
            cr.addArgument("id", String.valueOf(jobs.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/abonnement/add");
        }
        
        cr.setHttpMethod("POST");
        cr.setPost(true);
        
       
        cr.addArgument("duree", jobs.getCategory());
         cr.addArgument("duree", jobs.getJobDescription());
 cr.addArgument("duree", jobs.getStatus());
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int abonnementId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/abonnement/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(abonnementId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
