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
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_option_sn;

    private Long product_sn;

    @Column(length = 100)
    private String product_option_name;

    @Column(length = 11)
    @NotNull
    private Integer plus_price;

    @Column(length = 10)
    private Integer stock_cnt;

    @OneToMany(mappedBy = "productOption")
    private List<ProductOption> productOption = new ArrayList<>();

    public ProductOption(Long product_sn, String product_option_name, Integer plus_price, Integer stock_cnt) {
        this.product_sn = product_sn;
        this.product_option_name = product_option_name;
        this.plus_price = plus_price;
        this.stock_cnt = stock_cnt;
    }
}
