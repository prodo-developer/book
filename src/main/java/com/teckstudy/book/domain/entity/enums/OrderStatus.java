package com.teckstudy.book.domain.entity.enums;

public enum OrderStatus {
    WAIT("입금대기", "100"),
    PAYCOM("결제완료", "110"),
    TRANSIT("배송중", "120"),
    COMPLETE("배송완료", "130"),
    CONFIRM("구매확정", "140");

    private String legacyName;
    private String legacyCode;

    OrderStatus(String legacyName, String legacyCode) {
        this.legacyName = legacyName;
        this.legacyCode = legacyCode;
    }
}
