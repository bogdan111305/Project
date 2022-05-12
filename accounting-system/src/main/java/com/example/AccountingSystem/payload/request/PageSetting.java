package com.example.AccountingSystem.payload.request;

import com.example.AccountingSystem.specifications.SearchCriteria;
import com.example.AccountingSystem.specifications.SortCriteria;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class PageSetting {
    @NotEmpty
    private int page;
    @NotEmpty
    private int size;
    @NotEmpty
    private List<SortCriteria> sortCriteria;

    private List<SearchCriteria> searchCriteria;
}
