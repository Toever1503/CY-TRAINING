package com.domain;

import com.graphql.connection.CustomConnection;
import com.graphql.connection.DefaultCustomPageInfo;
import com.graphql.connection.DefaultCustomConnection;
import com.graphql.connection.DefaultCustomEdge;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "titleJp", length = 500)
    private String titleJp;

    @Lob
    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_create")
    private Date dateCreate;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_update")
    private Date dateUpdate;

    @Transient
    private CustomConnection<Studio> studios;

    @Transient
    private CustomConnection<Character> characters;

    public CustomConnection<Studio> studios(String q, int page, int perPage) {
        return null;
    }

    public CustomConnection<Character> characters(String q, int page, int perPage) {
        return null;
    }

}
