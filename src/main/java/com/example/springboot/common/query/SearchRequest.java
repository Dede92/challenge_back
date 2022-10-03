package com.example.springboot.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchRequest implements Serializable {

    private static final long serialVersionUID = 8514625832019794838L;

    private List<FilterRequest> filters;

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters))
            return new ArrayList<>();
        return this.filters;
    }

}