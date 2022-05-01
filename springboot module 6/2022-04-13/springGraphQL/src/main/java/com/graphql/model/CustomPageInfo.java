package com.graphql.model;

import graphql.relay.ConnectionCursor;
import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageInfo {
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int totalPage;
    private int page;
    private int perPage;

    public CustomPageInfo(int page, int perPage, int totalPage) {
        this.totalPage = totalPage;
        this.page = page;
        this.perPage = perPage == 0 ? 30 : perPage > 30 ? 30 : perPage;
    }
}
