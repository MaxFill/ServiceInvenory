package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "cfs_item")
public class CfsItem {
    @Id
    private Long objectId;

    private String cfsId;
    private String cfsStatus;

    private String cfsFinBlockState;
    private String cfsAdmBlockState;
    private String cfsSuspentionState;

    private String specCatId;
    private String specId;
    private String specName;
    private String specVersion;

    private Date validFrom;
    private Date validTo;

    @OneToMany(mappedBy = "cfsItemId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CfsAttributeItem> attributes;

    @OneToMany(mappedBy = "cfsItemId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CfsReferenceItem> references;

    @OneToOne
    @JoinColumn(name = "clientItemId")
    private ClientItem clientItem;

    @OneToOne
    @JoinColumn(name = "fsubscriptionId")
    private SubscritionItem subscriptionItem;

    @OneToMany(mappedBy = "cfsItemId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RfsItem> rfss;
}