package com.example.Test_Application;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Test_Application.controller.SearchItemController;
import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        ResultActions result = mockMvc.perform(get("/searchItems"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(searchItemService).getSearchItems();
    }
}