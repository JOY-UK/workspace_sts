package com.green.Board.vo;

import lombok.*;


//생성자를 구현하는 어노테이션
//@NoArgsConstructor        - 매개변수가 없는 기본생성자
//@AllArgsConstructor       - 멤버변수 모두를 매개변수로 받는 생성자
//@RequiredArgsConstructor  -

@Data // 기본생성자 , setter, getter, toString 생성해줌.
@Builder // 매개변수로 받는 다수의 생성자 생성
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

