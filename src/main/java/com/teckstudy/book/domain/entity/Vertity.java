package com.teckstudy.book.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teckstudy.book.domain.entity.enums.AuthType;
import com.teckstudy.book.domain.entity.enums.YesNoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vertity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_serial_sn;

    private Long member_sn;

    @Column(length = 100)
    private String auth_code;

    @CreatedDate
    private LocalDateTime auth_date;

    @Enumerated(EnumType.STRING)
    private YesNoStatus auth_yn;

    @Enumerated(EnumType.STRING)
    private AuthType member_auth_type;

    @OneToOne(mappedBy = "vertity", fetch = LAZY)
    private Member member;

    public Vertity(Long member_sn, String auth_code, YesNoStatus auth_yn, AuthType member_auth_type) {
        this.member_sn = member_sn;
        this.auth_code = auth_code;
        this.auth_yn = auth_yn;
        this.member_auth_type = member_auth_type;
    }
}
