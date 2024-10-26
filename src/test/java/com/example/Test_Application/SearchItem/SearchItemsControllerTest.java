package com.example.Test_Application.SearchItem;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Test_Application.controller.SearchItemController;
import com.example.Test_Application.exceptions.SearchItemListNotFoundException;
import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.service.SearchItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SearchItemController.class)
public class SearchItemsControllerTest {
    @MockBean
    private SearchItemService searchItemService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetSearchItems() throws Exception {
        List<SearchItem> mockSearchItems = Arrays.asList(
                new SearchItem(1L, "testTerm1"),
                new SearchItem(2L, "testTerm2")
        );

        given(searchItemService.getSearchItems()).willReturn(mockSearchItems);

        String expectedJson = "[{\"id\":1,\"searchTerm\":\"testTerm1\"},{\"id\":2,\"searchTerm\":\"testTerm2\"}]";
        ResultActions result = mockMvc.perform(get("/searchItems").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(searchItemService).getSearchItems();
    }

    @Test
    public void testGetSearchItemsNotFound() throws Exception {
        when(searchItemService.getSearchItems()).thenThrow(new SearchItemListNotFoundException());

        ResultActions result = mockMvc.perform(get("/searchItems"))
            .andExpect(status().isNotFound());

        verify(searchItemService).getSearchItems();
    }

    @Test
    public void testAddSearchItem() throws Exception {
        SearchItem searchItem = new SearchItem();
        searchItem.setSearchTerm("adding new search item");


        String expectedJson = new ObjectMapper().writeValueAsString(searchItem);
        when(searchItemService.addSearchItem(any(SearchItem.class))).thenReturn(searchItem);

        ResultActions result = mockMvc.perform(post("/searchItems/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchItem)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(searchItemService).addSearchItem(any(SearchItem.class));
    }
}