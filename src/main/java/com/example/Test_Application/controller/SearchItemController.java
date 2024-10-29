package com.example.Test_Application.controller;

import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    public SearchItemController() { }

    @GetMapping("/searchItems")
    public List<SearchItem> getSearchItems() throws Exception {
        return searchItemService.getSearchItems();
    }

    @PostMapping("/searchItems/add")
    public ResponseEntity<SearchItem> addSearchItem(@RequestBody SearchItem searchItem) {
        SearchItem savedSearchItem = searchItemService.addSearchItem(searchItem);
        return ResponseEntity.ok(savedSearchItem);
    }
}
