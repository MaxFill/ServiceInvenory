package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "rfs_item")
public class RfsItem {
    @Id
    private Long objectId;
    private Long cfsItemId;

    @Formula("(SELECT cfs_item.cfs_id FROM cfs_item WHERE (cfs_item_id=cfs_item.object_id))")
    private String cfsId;

    private String rfsId;
    private String rfsStatus;

    private String rfsSpecCatId;
    private String rfsSpecId;
    private String rfsSpecName;
    private String rfsSpecVersion;

    private Date validFrom;
    private Date validTo;

    @OneToMany(mappedBy = "rfsItemId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RfsAttributeItem> attributes;
}