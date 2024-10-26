package com.example.Test_Application.service;

import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchItemService {
    private SearchItemRepository searchItemRepo;

    public SearchItemService(SearchItemRepository searchItemRepository) {
        this.searchItemRepo = searchItemRepository;
    }

    public List<SearchItem> getSearchItems() throws Exception {
        List<SearchItem> searchItems = searchItemRepo.findAll();
        return searchItems;
    }
}
