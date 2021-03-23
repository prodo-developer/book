package com.teckstudy.book.repository;

import com.teckstudy.book.domain.entity.BookOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderProductRepository extends JpaRepository<BookOrderProduct, Long> {

}
