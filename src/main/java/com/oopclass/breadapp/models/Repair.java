package com.oopclass.breadapp.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Entity
@Table(name = "repairs")
public class Repair {

    
    
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private String address;
        
     private LocalDate date;
     
     
	
	private String contact;
        
        private String name;
     	
        private String status;
        
        private String unit;
        
        private String createdAt;
        
        private String updatedAt;

        
        
        
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

   


   
       
      
       
        
       

  
        
        
        
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
        
        
        

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
        
        
        
        
        
	

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
        
       
        
       

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
        
	
	

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

  

    

	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	

	@Override
	public String toString() {
		return "Repair [id=" + id +", name=" + name + ", address=" + address + ", unit=" + ", date=" + date +", createdAt=" + createdAt + "]";
	}
        
        

        
        

	
}
