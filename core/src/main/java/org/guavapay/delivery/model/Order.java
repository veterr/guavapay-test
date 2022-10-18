package org.guavapay.delivery.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    @Column(name = "creator_id")
//    private UUID creatorId;

    @Column(name = "name", unique = true, length = 255)
    private String name;

    @Column(name = "destination", length = 512)
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;

    @Column(name = "details", length = 1024)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private User courier;

    @OneToMany(fetch = FetchType.LAZY,
            targetEntity=Coordinates.class,
            cascade=CascadeType.ALL)
    @JoinColumn(name="ORDER_ID")
    @OrderBy("dateTime desc")
    private List<Coordinates> coordinates;
}
