package com.ehire.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import com.ehire.entities.candidat;
import com.ehire.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class candidatService {

    public static candidatService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<candidat> listOffres;

    private candidatService() {
        cr = new ConnectionRequest();
    }

    public static candidatService getInstance() {
        if (instance == null) {
            instance = new candidatService();
        }
        return instance;
    }

    public ArrayList<candidat> getAll() {
        listOffres = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/Condidat/showAll");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listOffres = getList();
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

        return listOffres;
    }

    private ArrayList<candidat> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                candidat offre = new candidat (
//                        (int) Float.parseFloat(obj.get("id").toString()),
                        (String) obj.get("fullName"),
                        (String) obj.get("phoneNumber"),
                     
                        (String) obj.get("email"),
                          (String) obj.get("cv_url")
                );

                listOffres.add(offre);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOffres;
    }

    public int add(candidat candidat) {
        return manage(candidat, false, true);
    }

    public int edit(candidat candidat, boolean imageEdited) {
        return manage(candidat, true, imageEdited);
    }

    public int manage(candidat offre, boolean isEdit, boolean imageEdited) {
        MultipartRequest cr = new MultipartRequest();
        cr.setHttpMethod("POST");
        cr.setFilename("file", "Offre.jpg");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/offre/edit");
            cr.addArgumentNoEncoding("id", String.valueOf(offre.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/offre/add");
        }

        cr.addArgumentNoEncoding("fullName", offre.getFullName());
        cr.addArgumentNoEncoding("phoneNumber", offre.getPhoneNumber());
        cr.addArgumentNoEncoding("email", String.valueOf(offre.getEmail()));
           cr.addArgumentNoEncoding("cv_url", String.valueOf(offre.getCv_url()));

       

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

    public int delete(int offreId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/offre/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(offreId));

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
