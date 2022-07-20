package com.ehire.entities;

public class jobs {

    private int id;
    private String jobDescription;
    private int AverageSallary;
    private int totalPlaces;
    private String Status;
    private String category;
   
    public jobs(int id, String jobDescription,  int AverageSallary, int totalPlaces, String Status, String category) {
        this.id = id;
        this.jobDescription = jobDescription;
        this.AverageSallary = AverageSallary;
         this.totalPlaces = totalPlaces;
          this.Status = Status;
           this.category = category;
           
    }
      public jobs(String jobDescription, String category) {
   
        this.jobDescription = jobDescription;
        
           this.category = category;
           
    }
        public jobs(String jobDescription) {
   
      
           this.category = category;
           
    }

    public int getId() {
        return id;
    }

    public String getJobDescription() {
        return jobDescription;
    }

 
    public int getTotalPlaces() {
        return totalPlaces;
    }

    public String getStatus() {
        return Status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getAverageSallary() {
        return AverageSallary;
    }

    public void setAverageSallary(int AverageSallary) {
        this.AverageSallary = AverageSallary;
    }

    

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    

    
}
