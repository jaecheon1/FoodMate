package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.demo.dto.MemberData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name="RecommendHistory")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Recommend_History {

	@Id
	private String history_id;
	
	@ManyToOne
	@JoinColumn(name = "idx", nullable=false)
	private foodRecipe recommend_food;
	
	@ManyToOne
	@JoinColumn(name = "no_data", nullable=false)
	private MemberData h_no_data;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	private Date recommend_date;
}
