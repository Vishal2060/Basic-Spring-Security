package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="role")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters=true)
@EqualsAndHashCode(of={"roleId"})
@Builder
public class Role {

    @Id
    @Column(name="role_id")
    private Integer roleId;
    
    @Column(name="role_name")
    private String roleName;
}
