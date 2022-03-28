package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cfs_attributes")
public class CfsAttributeItem {
    @Id
    private Long objectId;
    private Long cfsItemId;
    private String name;
    private String value;
}
