package com.example.Test_Application.SearchItem;

import com.example.Test_Application.model.SearchItem;
import com.example.Test_Application.repository.SearchItemRepository;
import com.example.Test_Application.service.SearchItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchItemsServiceTest {
    @Mock
    SearchItemRepository searchItemRepo;

    @Test
    public void getSearchItems() throws Exception {
        SearchItemService searchItemService = new SearchItemService(searchItemRepo);

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
}
