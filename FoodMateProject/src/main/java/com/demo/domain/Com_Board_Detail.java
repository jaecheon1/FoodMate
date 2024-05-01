package com.demo.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.demo.dto.Com_Recipe;
import com.demo.dto.MemberData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Com_Board_Detail {

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int seq; //게시글번호
			
			@OneToOne
		    @JoinColumn(name="idx", nullable=false)
		    private Com_Recipe com_recipe; //레시피번호
		    
		    @OneToOne
		    @JoinColumn(name="no_data", nullable=false)
		    private MemberData member_data; //회원번호
		    
		    @Temporal(value=TemporalType.TIMESTAMP)
		    @ColumnDefault("sysdate")
		    private String d_regdate; //작성일자 
		    
		    private int cnt;


			
		

}