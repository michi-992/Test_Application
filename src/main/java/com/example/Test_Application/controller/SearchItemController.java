package com.example.Test_Application.controller;

import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchItemController {
    private SearchItemService searchItemService;

    public SearchItemController(SearchItemService searchItemService) {
        this.searchItemService = searchItemService;
    }

    @GetMapping("/searchItems")
    public List<SearchItem> getSearchItems() throws Exception {
        return searchItemService.getSearchItems();
    }
}
