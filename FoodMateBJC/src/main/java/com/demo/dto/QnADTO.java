package com.demo.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QnADTO {
	@Id
	private int id;
    private String title;
    private String content;
    private String author;

}
