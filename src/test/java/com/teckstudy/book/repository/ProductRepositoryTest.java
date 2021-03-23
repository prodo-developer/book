package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.BookOrder;
import com.teckstudy.book.domain.entity.Member;
import com.teckstudy.book.domain.entity.Product;
import com.teckstudy.book.domain.entity.ProductOption;
import com.teckstudy.book.domain.entity.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.teckstudy.book.domain.entity.QProduct.product;
import static com.teckstudy.book.domain.entity.QProductOption.productOption;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ProductRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    ProductRepository productRepository;

    /**
     * 상품등록 테스트
     */

    @Test
    public void testProduct() {
        queryFactory = new JPAQueryFactory(em);
        //given
        Product product1 = new Product("해리포터", ProductType.RADIO, 50000, 30);
        Product product2 = new Product("반지의제왕", ProductType.CHECK, 35000, 50);
        em.persist(product1);
        em.persist(product2);

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.getProduct_name());
            System.out.println(product.getProduct_option());
        }
    }

    /**
     * 상품등록 및 상품 옵션 테스트
     */
    @Test
    public void testProductOption() {
        queryFactory = new JPAQueryFactory(em);
        //given
        Product product1 = new Product("오라클과 SQL", ProductType.RADIO, 50000, 30);
        Product product2 = new Product("Real Mysql", ProductType.CHECK, 35000, 50);
        em.persist(product1);
        em.persist(product2);

        ProductOption productOption1 = new ProductOption(product1.getProduct_sn(), "DB모음집 시리즈", 4500, 20);
        ProductOption productOption2 = new ProductOption(product1.getProduct_sn(), "파우치", 1500, 100);
        ProductOption productOption3 = new ProductOption(product1.getProduct_sn(), "악세사리", 500, 200);

        em.persist(productOption1);
        em.persist(productOption2);
        em.persist(productOption3);

        // 리스트 조회
//        List<Product> result = queryFactory
//                .selectFrom(product)
//                .fetch();

        List<Tuple> result = queryFactory
                .select(productOption, product)
                .from(productOption)
                .leftJoin(product).on(product.product_sn.eq(productOption.product_sn))
                .fetch();

        for (Tuple results : result) {
            System.out.println("product = " + results);
        }
    }
}