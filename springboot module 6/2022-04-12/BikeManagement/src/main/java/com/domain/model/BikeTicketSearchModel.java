package com.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikeTicketSearchModel {
    private String bikeCode;
    private String bikeColor;
    private Long bikeType;
    private Long ticketType;
    private Float maxPrice;
    private Float minPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeEnd;
}
