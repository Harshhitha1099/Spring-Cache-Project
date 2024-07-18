package com.development.springcachedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="department")
public class Department {

    @Id
    private Integer id;

    private String name;

    private String description;

    private String status;

}
