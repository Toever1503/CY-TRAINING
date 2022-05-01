package com.graphql;

import com.graphql.model.CustomPageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class GraphqlUtil {
    public static Pageable getPageable(Map<String, Object> map) {
        Pageable pageable = null;
        if (map.get("sort") != null) {
            Map<String, Object> sortMap = (Map<String, Object>) map.get("sort");
            pageable = PageRequest.of((int) map.get("page"),
                    (int) map.get("perPage"),
                    Sort.by((Sort.Direction) sortMap.get("direction"), sortMap.get("by").toString()));
        } else
            pageable = PageRequest.of((int) map.get("page"), (int) map.get("perPage"));
        return pageable;
    }

    public static CustomPageInfo getPageInfoFromPage(Page page) {
        CustomPageInfo pageInfo = new CustomPageInfo();
        pageInfo.setPage(page.getNumber());
        pageInfo.setPerPage(page.getSize());
        pageInfo.setTotalPage(page.getTotalPages());
        pageInfo.setHasNextPage(page.hasNext());
        pageInfo.setHasPreviousPage(page.hasPrevious());
        return pageInfo;
    }

}
