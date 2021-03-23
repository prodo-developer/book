package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.BookOrder;
import com.teckstudy.book.domain.entity.Member;
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
import static com.teckstudy.book.domain.entity.QBookOrder.bookOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BookOrderRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void testEntity() {
        queryFactory = new JPAQueryFactory(em);
        //given
        Member member1 = new Member("member1", "1234", "백성락", Gender.MALE,
                "1990-09-12", "010-2027-1163", "서울특별시 봉천동",  YesNoStatus.Y,  MemberStatus.NORMAL);
        Member member2 = new Member("member2", "1234", "최진솔", Gender.FEMALE,
                "1993-08-03", "010-6776-4684", "서울특별시 금천구",  YesNoStatus.Y,  MemberStatus.NORMAL);
        em.persist(member1);
        em.persist(member2);

        BookOrder bookOrder1 = new BookOrder(member1.getMember_sn(), YesNoStatus.N, 11000,
                            "서울시 강남구 신사동호랭이", OrderStatus.WAIT);
        BookOrder bookOrder2 = new BookOrder(member1.getMember_sn(), YesNoStatus.N, 21000,
                "서울시 관악구 청담동", OrderStatus.PAYCOM);
        BookOrder bookOrder3 = new BookOrder(member2.getMember_sn(), YesNoStatus.N, 15000,
                "서울시 강남구 SM엔터테인먼트", OrderStatus.CONFIRM);
        BookOrder bookOrder4 = new BookOrder(member2.getMember_sn(), YesNoStatus.N, 10000,
                "서울시 광진구 무민전시회", OrderStatus.COMPLETE);
        em.persist(bookOrder1);
        em.persist(bookOrder2);
        em.persist(bookOrder3);
        em.persist(bookOrder4);
    }

    @Test
    public void orderTest() {
        // 내부조인(inner join)이면 join과 where을 사용하고 아닐시에는 on 절 사용
        // .where(member.member_id.eq("member1"))
        List<Tuple> result = queryFactory
                .select(bookOrder, member)
                .from(bookOrder)
                .inerJoin(member).on(member.member_id.eq("member1"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple " + tuple);
        }
    }
}