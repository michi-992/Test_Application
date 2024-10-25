package com.example.Test_Application.repository;

import com.example.Test_Application.model.SearchItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SearchItemRepository {
    public Optional<List<SearchItem>> getSearchItems() {
        return Optional.empty();
    }
}
