package com.graphql.model;

import com.domain.Staff;
import lombok.Data;

import java.util.List;

@Data
public class StaffConnection  {
    private List<Staff> nodes;
    private CustomPageInfo pageInfo;

    public StaffConnection(List<Staff> content, CustomPageInfo pageInfoFromPage) {
        this.nodes = content;
        this.pageInfo = pageInfoFromPage;
    }
}
