package com.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikeTicketMODEL {
    private Long id;

    @NotNull
    @NotBlank
    private String bikeCode;

    @NotNull
    @NotBlank
    private String bikeColor;

    @NotNull
    private Long ticketType;

    @NotNull
    private Long bikeType;
}
