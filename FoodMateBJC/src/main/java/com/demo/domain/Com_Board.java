package com.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.demo.dto.Com_Recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@DynamicInsert 
@DynamicUpdate
public class Com_Board {

		@Id
		@OneToOne
	    @JoinColumn(name="seq", nullable=false)
		private Com_Board_Detail com_board_detail; //게시글번호
		
		@OneToOne
		@JoinColumn(name="idx", nullable=false)
		private Com_Recipe com_recipe; //레시피번호
		
		
	}
