package com.jmrsoftware.csvtobeanapplication.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class RealEstateTransaction {
    @Id
    private Long id;
    @CsvBindByName
    private String street;
    @CsvBindByName
    private String city;
    @CsvBindByName
    private int zip;
    @CsvBindByName
    private String state;
    @CsvBindByName
    private int beds;
    @CsvBindByName
    private int baths;
    @CsvBindByName
    private int sq_ft;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private Date sale_date;
    @CsvBindByName
    private Double price;
    @CsvBindByName
    private Float latitude;
    @CsvBindByName
    private Float longitude;
}
