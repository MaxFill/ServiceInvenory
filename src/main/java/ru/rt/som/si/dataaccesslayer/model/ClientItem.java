package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "client_item")
public class ClientItem {
    @Id
    private Long objectId;

    private String clientName;
    private String clientId;

    private Date validFrom;
    private Date validTo;
}
