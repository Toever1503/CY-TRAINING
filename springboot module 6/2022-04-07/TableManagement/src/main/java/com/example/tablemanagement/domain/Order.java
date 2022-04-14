package com.example.tablemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cus_name", nullable = false, length = 100)
    private String cusName;

    @Column(name = "cus_phone", nullable = false, length = 12)
    private String cusPhone;

    @Column(name ="total_price")
    private Integer totalPrice;

    @Column(name = "discount")
    private Integer discount;

    @Column(name ="final_price")
    private Integer finalPrice;

    @Column(name ="date_order")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table orderTable;

    @ManyToOne
    @JoinColumn(name ="staff_id")
    private Staff orderStaff;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cusName='" + cusName + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
