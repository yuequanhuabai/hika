package com.e.hika.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Tag(name = "分页参数")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    @Schema(description = "当前页")
    private Integer pageCurrent = 1;

    @Schema(description = "显示条数")
    private Integer pageSize = 10;
}
