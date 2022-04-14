package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bike_tickets")
public class BikeTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bike_code", nullable = false)
    private String bikeCode;

    @Column(name = "bike_color")
    private String bikeColor;

    @ManyToOne
    @JoinColumn(name = "ticket_type", nullable = false)
    private BikeType ticketType;

    @ManyToOne
    @JoinColumn(name = "bike_type", nullable = false)
    private BikeType bikeType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_start", nullable = false)
    private Date timeStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    private Date timeEnd;

}
