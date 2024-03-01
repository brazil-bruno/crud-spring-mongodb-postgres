package com.bruno.apipostgres.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String _id;
    private String name;
    private String description;
    private Double price;
}
