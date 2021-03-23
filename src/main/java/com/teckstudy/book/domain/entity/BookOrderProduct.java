package com.teckstudy.book.domain.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_product_sn;

    private Long product_sn;

    private Long order_sn;

    @Column(length = 10)
    @NotNull
    private Integer product_cnt;

    private Long product_option_sn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BookOrder bookOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductOption productOption;
}
