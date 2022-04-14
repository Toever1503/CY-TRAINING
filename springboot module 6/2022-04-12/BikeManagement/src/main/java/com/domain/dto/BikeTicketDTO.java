package com.domain.dto;

import com.domain.BikeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikeTicketDTO {
    private Long id;

    private String bikeCode;

    private String bikeColor;

    private BikeType ticketType;

    private BikeType bikeType;

    private Date timeStart;

    private Date timeEnd;
}
