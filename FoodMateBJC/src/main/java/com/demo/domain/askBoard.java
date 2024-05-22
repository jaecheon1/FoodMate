package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class askBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiry_id;
    private String name;
    private String email;
    private String message;
    @ColumnDefault("sysdate")
    private Date regdate;
    private String subject;
    private String comments;
    // status 속성 추가
    private String status;
    
    

}
