package com.graphql;

import com.graphql.connection.CustomPageable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDataloaderKey {
    private Object id;
    private CustomPageable pageable;
}
