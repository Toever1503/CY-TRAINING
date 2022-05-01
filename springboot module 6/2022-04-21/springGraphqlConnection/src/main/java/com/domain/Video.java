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

    @Transient
    private CustomConnection<Staff> staffs;

    @Transient
    private CustomConnection<Studio> studios;

    @Transient
    private CustomConnection<Character> characters;

    public CustomConnection<Staff> staffs(String q, int page, int perPage) {
        return null;
    }

    public CustomConnection<Studio> studios(String q, int page, int perPage) {
        return null;
    }

    public CustomConnection<Character> characters(String q, int page, int perPage) {
        return null;
    }

}
