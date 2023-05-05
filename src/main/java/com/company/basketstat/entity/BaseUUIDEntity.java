package com.company.basketstat.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "BST_BASE_UUID_ENTITY", indexes = {
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_TEAM", columnList = "TEAM_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_PLAYER", columnList = "PLAYER_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_STATISTIC", columnList = "STATISTIC_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_TEAM_ONE", columnList = "TEAM_ONE_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_TEAM_TWO", columnList = "TEAM_TWO_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_WINNER", columnList = "WINNER_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_LOSER", columnList = "LOSER_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_GAME_RESULT", columnList = "GAME_RESULT_ID"),
        @Index(name = "IDX_BST_BASE_UUID_ENTITY_GAME", columnList = "GAME_ID")
})
@Entity(name = "bst_BaseUUIDEntity")
public class BaseUUIDEntity {
    @InstanceName
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;


    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}