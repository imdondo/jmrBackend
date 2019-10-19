package com.jmrsoftware.csvtobeanapplication.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RealEstateTransaction {
    @Id
    private Long id;
    @CsvBindByPosition(position = 0)
    private String street;
    @CsvBindByPosition(position = 1)
    private String city;
    @CsvBindByPosition(position = 2)
    private int zip;
    @CsvBindByPosition(position = 3)
    private String state;
    @CsvBindByPosition(position = 4)
    private int beds;
    @CsvBindByPosition(position = 5)
    private int baths;
    @CsvBindByPosition(position = 6)
    private int sq_ft;
    @CsvBindByPosition(position = 7)
    private String type;
    @CsvBindByPosition(position = 8)
    private LocalDate sale_date;
    @CsvBindByPosition(position = 9)
    private Double price;
    @CsvBindByPosition(position = 10)
    private Float latitude;
    @CsvBindByPosition(position = 11)
    private Float longitude;

    public RealEstateTransaction(String s, String el_dorado_hills, int i, String ca, int i1, int i2, int i3, String residential, LocalDateTime dateTime, int i4, double v, double v1) {
    }
}
