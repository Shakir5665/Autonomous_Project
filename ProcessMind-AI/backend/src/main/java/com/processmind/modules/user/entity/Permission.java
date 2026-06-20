package com.processmind.modules.user.entity;

import com.processmind.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "permissions", schema = "processmind")
public class Permission extends BaseEntity {

    @Column(name = "resource", nullable = false, length = 100)
    private String resource;

    @Column(name = "action", nullable = false, length = 100)
    private String action;

    @Column(name = "description", length = 255)
    private String description;

}