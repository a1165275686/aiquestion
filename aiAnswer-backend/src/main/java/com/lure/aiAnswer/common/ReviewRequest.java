package com.lure.aiAnswer.common;

import lombok.Data;

/**
 * 审核请求
 *
 */
@Data
public class ReviewRequest {

    /**
     * id
     */
    private Long id;

    /**
     * 状态 0-待审核 1-通过 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;


    private static final Long serialVersionUID = 1L;
}
