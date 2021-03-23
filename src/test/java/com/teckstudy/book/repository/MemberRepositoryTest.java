package com.teckstudy.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.*;
import com.teckstudy.book.domain.entity.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.teckstudy.book.domain.entity.QMember.member;
import static com.teckstudy.book.domain.entity.QSnsInfo.snsInfo;
import static com.teckstudy.book.domain.entity.QVertity.vertity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void testEntity() {
        queryFactory = new JPAQueryFactory(em);
        //given
        Member member1 = new Member("member1", "1234", "홍길동", Gender.MALE,
                "1990-09-12", "010-2027-1163", "서울특별시 봉천동",  YesNoStatus.Y,  MemberStatus.NORMAL);
        em.persist(member1);

        SnsInfo snsInfo1 = new SnsInfo(member1.getMember_sn(), "123456789", SnsType.K);
        em.persist(snsInfo1);

        Vertity vertity = new Vertity(member1.getMember_sn(), "4684", YesNoStatus.Y, AuthType.PHONE);
        em.persist(vertity);

        // 영속 컨텍스트를 플러시 하는방법
        // 1. em.flush();
        // 2. 트랜잭션 커밋 : 플러시 자동호출
        // 3. JPQL 쿼리실행 - 플러시 자동 호출
    }

    @Test
    public void memberTest() {
        // 엔티티가 없을때는 반드시 빌드를 해줘야합니다. (Gradle -> querydsl < Tasks < other < compileQuerydsl
        // QMember m = new QMember("m"); // 방법 1
        // QMember m = QMember.member; // 방법 2
        // 방법 : 매개변수 파라미터에 QMember.member 선언 후 alt+enter를 통해 static 선언
        List<Member> findMember = queryFactory
                .selectFrom(member)
                .join(member.snsInfo, snsInfo)
                .join(member.vertity, vertity)
                .where(member.member_id.eq("member1"))
                .fetch();

        for (Member member1 : findMember) {
            System.out.println("member1 ==== " + member1);
            assertThat(member1.getMember_id()).isEqualTo("member1");
        }

    }

}