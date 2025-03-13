package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Geo{

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String lat;

    private String lng;

	@OneToOne(mappedBy = "geo")


    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


}