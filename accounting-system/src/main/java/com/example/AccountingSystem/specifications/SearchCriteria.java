package com.example.AccountingSystem.specifications;

import com.example.AccountingSystem.specifications.enums.SearchOperation;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
}
