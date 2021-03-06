package com.jsr.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성자 ID 객체값
    @ManyToOne
    @JoinColumn(name = "userId", nullable=false)
    private User userId;

    //사용자 이름
    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name ="accountType", nullable = false)
    private String accountType;

    //글 제목
    @Column(name = "title", nullable = false)
    private String title;

    //글 내용
    @Column(name = "content", nullable = false)
    private String content;

    //작성시간
    @Column(name = "createTime", nullable = false)
    private Timestamp createTime;

    //수정시간
    @Column(name = "modifyTime")
    private Timestamp modifyTime;

    //삭제시간
    @Column(name = "delTime")
    private Timestamp delTime;

    //삭제 처리 플래그
    @Column(name = "delFlag")
    private boolean delFlag;

    //글 좋아요수
    @Column(name = "likeCount")
    private int likeCount;

    //내가 좋아요한것 체크
    @Transient
    private Boolean myLike;


}