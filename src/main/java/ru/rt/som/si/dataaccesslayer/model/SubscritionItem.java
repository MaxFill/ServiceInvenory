package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "fsubscription")
public class SubscritionItem {
    @Id
    private Long objectId;

    private String subscriptionId;

    @Formula("(SELECT client_item.client_id FROM client_item WHERE client_item_id=client_item.object_id)")
    private String clientId;

    private String sbranch;
    private String affiliate;

    private Date validFrom;
    private Date validTo;

    @OneToOne
    @JoinColumn(name = "clientItemId")
    private ClientItem clientItem;
}
