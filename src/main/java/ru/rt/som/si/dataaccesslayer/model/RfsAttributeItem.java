package ru.rt.som.si.dataaccesslayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "rfs_attributes")
public class RfsAttributeItem {
    @Id
    private Long objectId;
    private Long rfsItemId;
    private String name;
    private String value;
}