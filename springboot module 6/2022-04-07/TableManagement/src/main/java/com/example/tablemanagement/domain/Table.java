package com.example.tablemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "date_picker")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePicker;

    @Column(name = "price", nullable = false)
    private Integer price;

    public String toJson() {
        return new JSONObject(this).toString().replace("\"", "'");
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", status=" + status +
                ", datePicker=" + datePicker +
                ", price=" + price +
                '}';
    }
}
