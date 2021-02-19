package com.oopclass.breadapp.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Entity
@Table(name = "receipts")
public class Receipt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
       
	private long id;
        
        
     
       

     private String createdAt;
        
        private String updatedAt;
            
            
            
            
         
	private String address;
	
	private String amount;
        
        private String name;
     	
        
        
	private LocalDate date;
      
        
       
        
        private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getAmount() {
       
        
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    

	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	

	@Override
	public String toString() {
		return "Receipt [id=" + id +", name=" + name + ", address=" + address + ", unit=" + unit + ", amount=" + amount + ", date=" + date + "]";
	}
        
        

        
        

	
}
