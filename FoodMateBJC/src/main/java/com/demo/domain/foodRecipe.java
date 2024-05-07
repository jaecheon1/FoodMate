package com.demo.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
public class foodRecipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_idx_generator")
    @SequenceGenerator(name="food_idx_generator", sequenceName = "RECIPE_SEQ", allocationSize = 1)
	private int idx;
	private String name;
	private String images;
	private String recipecategory;
	private String keywords;
	private String recipeingredientparts;
	@ColumnDefault("0")
	private int calories;
	@ColumnDefault("0")
	private int fatcontent;
	@ColumnDefault("0")
	private int saturatedfatcontent;
	@ColumnDefault("0")
	private int cholesterolcontent;
	@ColumnDefault("0")
	private int sodiumcontent;
	@ColumnDefault("0")
	private int carbohydratecontent;
	@ColumnDefault("0")
	private int fibercontent;
	@ColumnDefault("0")
	private int sugarcontent;
	@ColumnDefault("0")
	private int proteincontent;
	private String recipeinstructions;
	
}