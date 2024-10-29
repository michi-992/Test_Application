package com.example.Test_Application.SearchItem;

import com.example.Test_Application.exceptions.SearchItemListNotFoundException;
import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemRepository;
import com.example.Test_Application.service.SearchItemService;
import com.example.Test_Application.service.SearchItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SearchItemsServiceTest {
    @Mock
    SearchItemRepository searchItemRepo;

    @InjectMocks
    SearchItemServiceImpl searchItemService;

    @Autowired
    SearchItemRepository realSearchItemRepo;

    @Test
    public void getSearchItems() throws Exception {
        List<SearchItem> searchItems = List.of(
                new SearchItem(1L, "searchTerm1"),
                new SearchItem(2L, "searchTerm2")
        );
        List<SearchItem> searchItemsList = searchItems;

        when(searchItemRepo.findAll()).thenReturn(searchItemsList);

        List<SearchItem> foundSearchItems = searchItemRepo.findAll();

        assertThat(foundSearchItems).isEqualTo(searchItemsList);

        verify(searchItemRepo).findAll();
    }

    @Test
    public void getSearchItemsNotFound() {
        when(searchItemRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(SearchItemListNotFoundException.class, () -> {
            searchItemService.getSearchItems();
        });

        verify(searchItemRepo).findAll();
    }

    @Test
    public void addSearchItem() {
        SearchItem searchItem = new SearchItem();
        searchItem.setSearchTerm("new search item");

        when(searchItemRepo.save(any(SearchItem.class))).thenReturn(searchItem);

        SearchItem result = searchItemService.addSearchItem(searchItem);

        assertThat(result).isNotNull();
        assertThat(result.getSearchTerm()).isEqualTo("new search item");

        verify(searchItemRepo).save(searchItem);
    }
}
