package com.estsoft.demo.repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity // 테이블 선언
public class Member {
    @Id // id pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 전략 -> 아이디를 입력하지 않아도 자동 증가 1,2,3,...
    @Column(name = "id", updatable = false)
    private Long id;

    @Column
    private String name;
}
