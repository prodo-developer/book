package com.teckstudy.book.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teckstudy.book.domain.entity.enums.SnsType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsInfo extends BaseEntity {

    @Id
    private Long member_sn;

    @Column(length = 100)
    private String sns_code;

    @Enumerated(EnumType.STRING)
    private SnsType sns_type;

    @OneToOne(mappedBy = "snsInfo", fetch = LAZY)
    private Member member;

    public SnsInfo(Long member_sn, String sns_code, SnsType sns_type) {
        this.member_sn = member_sn;
        this.sns_code = sns_code;
        this.sns_type = sns_type;
    }
}
