package com.wildcodeschool.library.repository;

import com.wildcodeschool.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingOrDescriptionContaining(String textT    , String textD);
}
