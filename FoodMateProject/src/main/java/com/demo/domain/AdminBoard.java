package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class AdminBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardnum_generator")
    @SequenceGenerator(name="boardnum_generator", sequenceName = "BOARDNUM_SEQ", allocationSize = 1)
    private int boardnum;
    private String userid;
    private String usercode;
    private int boardcode;
    private String nickname;
    private String title;
    private String content;
    private String image;
    private String tag;
   
	@Column(columnDefinition = "NUMBER default 0")
    private int boardcount;
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date regdate;
	
	
	
}