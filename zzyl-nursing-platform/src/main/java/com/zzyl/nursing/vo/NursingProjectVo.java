package com.zzyl.nursing.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "护理项目")
public class NursingProjectVo {

    /**
     * 项目名称
     */
    private String label;

    /**
     * 值（ID）
     * 护理项目名称不回显bug解决
     */
    private String value;
}