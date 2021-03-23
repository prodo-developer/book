package com.teckstudy.book.domain.entity;

import com.sun.istack.NotNull;
import com.teckstudy.book.domain.entity.enums.ProductType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_sn;

    @Column(length = 30)
    private String product_name;

    @Enumerated(EnumType.STRING)
    private ProductType product_option;

    @Column(length = 11)
    @NotNull
    private Integer price;

    @Column(length = 10)
    private Integer stock_cnt;

    @OneToMany(mappedBy = "product")
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();

    public Product(String product_name, ProductType product_option, Integer price, Integer stock_cnt) {
        this.product_name = product_name;
        this.product_option = product_option;
        this.price = price;
        this.stock_cnt = stock_cnt;
    }
}
