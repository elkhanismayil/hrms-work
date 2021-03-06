package com.company.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Regions implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "region_id", nullable = false)
    private Integer id;

    @Column(name = "region_name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employers employers;

    @OneToMany(mappedBy = "regions", targetEntity = Countries.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Countries> countries;


}
