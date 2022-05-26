package com.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "node_seq", sequenceName = "node_id")
    private Long id;
    @JoinColumn(name = "title")
    private String title;
    @JoinColumn(name = "content")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "created_date")
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "updated_date")
    private Date updatedDate;
}
