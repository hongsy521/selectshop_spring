package com.sparta.myselectshop.entity;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.naver.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "product") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    // 다대일 단방향
    @ManyToOne(fetch = FetchType.LAZY)  // 회원 정보가 필요할 때만 조회할 수 있도록하기 위해 fetchType=Lazy 옵션 걸기
    @JoinColumn(name="user_id",nullable = false) // userId 무조건 들어오게끔
    private User user;

    @OneToMany(mappedBy = "product")  // 타겟이 되는 엔티티의 필드 JoinColumn으로 관계를 맺고 있는 외래키의 주인 필드
    private List<ProductFolder> productFolderList = new ArrayList<>();

    public Product(ProductRequestDto requestDto,User user) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.user=user;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myprice=requestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice=itemDto.getLprice();
    }
}