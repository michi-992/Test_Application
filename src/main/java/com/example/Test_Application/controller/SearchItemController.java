package com.example.Test_Application.controller;

import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.service.SearchItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SearchItemController {
    private SearchItemService searchItemService;

    public SearchItemController(SearchItemService searchItemService) {
        this.searchItemService = searchItemService;
    }

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
