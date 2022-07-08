/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author wawa
 */
public class JobOffer {
    private int id;
    private String jobDescription;
    private int AverageSallary;
    private int totalPlaces;
    private String Status;
    private Date CreatedDate;
    private Date UpdatedDate;
    private Category category;

    public JobOffer() {
    }

    public JobOffer(String jobDescription, int AverageSallary, int totalPlaces, String Status, Date CreatedDate, Date UpdatedDate, Category category) {
        this.jobDescription = jobDescription;
        this.AverageSallary = AverageSallary;
        this.totalPlaces = totalPlaces;
        this.Status = Status;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
        this.category = category;
    }

    public JobOffer(int id, String jobDescription, int AverageSallary, int totalPlaces, String Status, Date CreatedDate, Date UpdatedDate, Category category) {
        this.id = id;
        this.jobDescription = jobDescription;
        this.AverageSallary = AverageSallary;
        this.totalPlaces = totalPlaces;
        this.Status = Status;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobDescription() {
        return jobDescription;
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

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JobOffer{" + "id=" + id + ", jobDescription=" + jobDescription + ", AverageSallary=" + AverageSallary + ", totalPlaces=" + totalPlaces + ", Status=" + Status + ", CreatedDate=" + CreatedDate + ", UpdatedDate=" + UpdatedDate + ", category=" + category + '}';
    }


    
    
}
