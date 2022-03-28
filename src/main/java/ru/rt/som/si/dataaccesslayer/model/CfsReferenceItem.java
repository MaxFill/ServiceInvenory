package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cfs_references")
public class CfsReferenceItem {
    @Id
    private Long objectId;

    private Long cfsItemId;
    private String name;
    private String specId;
    private String cfsItemRef;
}
