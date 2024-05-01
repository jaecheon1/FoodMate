package com.demo.domain;

import com.demo.dto.QnADTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnA_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = QnADTO.class)
    @JoinColumn(name = "qseq", nullable = false)
    private QnADTO qna; // 해당 질문(QnA)에 대한 정보
    private String questionDetail; // 질문의 상세 내용
    private String answerDetail; // 질문에 대한 답변
    

    
}