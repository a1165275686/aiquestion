package com.lure.aiAnswer.model.statistic;

import lombok.Data;

@Data
public class AppAnswerCountDTO {

    private Long appId;

    /**
     * 用户提交答案数
     */
    private Long answerCount;
}