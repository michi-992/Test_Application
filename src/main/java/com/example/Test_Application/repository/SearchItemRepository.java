package com.example.Test_Application.repository;

import com.example.Test_Application.model.SearchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchItemRepository extends JpaRepository<SearchItem, Long> {
}
