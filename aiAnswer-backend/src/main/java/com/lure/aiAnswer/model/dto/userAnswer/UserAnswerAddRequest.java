package com.lure.aiAnswer.model.dto.userAnswer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建用户答案请求
 */
@Data
public class UserAnswerAddRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 用户答案（JSON 数组）
     */
    private List<String> choices;

    /**
     * 流水号
     */
    private String serialNumber;


    private static final long serialVersionUID = 1L;
}