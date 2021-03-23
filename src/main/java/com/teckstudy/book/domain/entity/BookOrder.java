package com.teckstudy.book.domain.entity;

import com.teckstudy.book.domain.entity.enums.OrderStatus;
import com.teckstudy.book.domain.entity.enums.YesNoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_sn;

    private Long member_sn;

    @Enumerated(EnumType.STRING)
    private YesNoStatus order_yn;

    @Column(length = 11)
    private Integer order_price;

    @Column(length = 100)
    private String order_address;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_sn")
    private Member order;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    private PayInfo payInfo;

    @OneToMany(mappedBy = "bookOrder")
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();

    public BookOrder(Long member_sn, YesNoStatus order_yn, Integer order_price, String order_address, OrderStatus order_status) {
        this.member_sn = member_sn;
        this.order_yn = order_yn;
        this.order_price = order_price;
        this.order_address = order_address;
        this.order_status = order_status;
    }
}
