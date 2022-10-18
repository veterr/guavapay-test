package org.guavapay.delivery.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "EXECUTION_ID")
    private UUID executionId;

    @Column(name = "LATITUDE")
    public Long latitude;

    @Column(name = "LONGITUDE")
    public Long longitude;

    @Column(name = "DATE_TIME")
    public Date dateTime;
}
