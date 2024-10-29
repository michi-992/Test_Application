package com.example.Test_Application.service;

import com.example.Test_Application.exceptions.SearchItemListNotFoundException;
import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemRepository searchItemRepo;

    public SearchItemServiceImpl() { }

    public SearchItemServiceImpl(SearchItemRepository searchItemRepo) {
        this.searchItemRepo = searchItemRepo;
    }

    @Override
    public List<SearchItem> getSearchItems() throws SearchItemListNotFoundException {
        List<SearchItem> searchItems = searchItemRepo.findAll();
        if (searchItems.isEmpty()) {
            throw new SearchItemListNotFoundException();
        }
        return searchItems;
    }

    @Override
    public SearchItem addSearchItem(SearchItem searchItem) {
        return searchItemRepo.save(searchItem);
    }
}