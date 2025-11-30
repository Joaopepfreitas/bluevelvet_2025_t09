package com.musicstore.bluevelvet.api.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private Long id;

    private String name;

    private Long parentId;

}
