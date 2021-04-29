package org.palms.mood.tracker.domain;

import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Entity
@Table(name = "ACTIVITY_CATEGORY")
@Data
public class ActivityCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ICON")
    private String icon;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SYS_CREATION_DATE", updatable = false)
    private Date sysCreationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SYS_UPDATE_DATE")
    private Date sysUpdateDate;

    @PrePersist
    void createdAt() {
        setSysCreationDate(new Date());
    }

    @PreUpdate
    void updatedAt() {
        setSysUpdateDate(new Date());
    }
}
