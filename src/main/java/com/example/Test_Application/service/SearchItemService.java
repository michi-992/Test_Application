package com.example.Test_Application.service;

import com.example.Test_Application.exceptions.SearchItemListNotFoundException;
import com.example.Test_Application.model.SearchItem;

import java.util.List;

public interface SearchItemService {
    List<SearchItem> getSearchItems() throws SearchItemListNotFoundException;
    SearchItem addSearchItem(SearchItem searchItem);
}