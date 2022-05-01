package com.graphql.model;

import com.domain.Studio;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudioConnection {
    private List<Studio> nodes;
    private CustomPageInfo pageInfo;
}
