package com.example.demo.persistence;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="STORE")
public class StoreEntity  {
 
    @Id
    @Column(name="key")
    private Integer key;
     
    @Column(name="value")
    private String value;
     
 
    @Override
    public String toString() {
        return "StoreEntity [key=" + key + 
                ", value=" + value ;
    }
}
