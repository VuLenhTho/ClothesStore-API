package com.vulenhtho.clothesboot.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class BriefProductFilterResponse {
    private Integer totalPages;
    private Integer currentPage;
    List<BriefProductWebResponse> products;
}
