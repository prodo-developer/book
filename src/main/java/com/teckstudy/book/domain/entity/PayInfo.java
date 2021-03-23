package com.teckstudy.book.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pay_sn;

    private Long order_sn;

    private Integer total_price;

    @OneToOne(mappedBy = "payInfo",fetch = FetchType.LAZY)
    private BookOrder bookOrder;
}
