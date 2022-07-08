/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Utils.Statitics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.Category;
import models.JobOffer;

/**
 *
 * @author wawa
 */
public class JobofferService {

    //prefix
    private String JobofferPrefix = "/Joboffers";

    //var
    static JobofferService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<JobOffer> Joboffers = new ArrayList<JobOffer>();

    //constructor
    private JobofferService() {
        req = new ConnectionRequest();
    }

    //Get
    public static JobofferService getInstance() {
        if (instance == null) {
            instance = new JobofferService();
        }

        return instance;
    }

    //Ajout
    public boolean addJoboffer(JobOffer p) {

        //build URL
        String addURL = Statitics.BASE_URL + JobofferPrefix + "/add";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("jobDescription", p.getJobDescription());
        req.addArgument("AverageSallary", String.valueOf(p.getAverageSallary()));
        req.addArgument("totalPlaces", String.valueOf(p.getTotalPlaces()));
        req.addArgument("Status", p.getStatus());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createdAt = format.format(p.getCreatedDate());
        String updatedAt = format.format(p.getUpdatedDate());
        req.addArgument("CreatedDate", createdAt);
        req.addArgument("UpdatedDate", updatedAt);
        req.addArgument("category", String.valueOf(p.getCategory().getId_cat()));

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    //Select
    public List<JobOffer> fetchJoboffers() {

        //URL
        String selectURL = Statitics.BASE_URL + JobofferPrefix + "/showAll";

        //1
        req.setPost(false);
        //2
        req.setUrl(selectURL);
        //3
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String response = new String(req.getResponseData());
                //parsing
                //..
                Joboffers = parseJoboffers(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return Joboffers;
    }

    //PARSING JSON
    public List<JobOffer> parseJoboffers(String jsonText) {

        //parser
        Joboffers = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                JobOffer p = new JobOffer();
                p.setJobDescription((String) item.get("jobDescription"));
                p.setAverageSallary((Integer) item.get("AverageSallary"));
                p.setTotalPlaces((Integer) item.get("totalPlaces"));
                p.setStatus((String) item.get("Status"));
                p.setCreatedDate((Date) item.get("CreatedDate"));
                p.setUpdatedDate((Date) item.get("UpdatedDate"));
                p.setCategory((Category) item.get("category"));

                Joboffers.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return Joboffers;
    }

}
