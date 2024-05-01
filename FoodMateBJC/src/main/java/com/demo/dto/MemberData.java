package com.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MemberData {
	@Id
    private String id;
    private String password;
    private int age;
    private int height;
    private int weight;
    private String name;
    private String gender;
    private String email;
    private int nodata;
}