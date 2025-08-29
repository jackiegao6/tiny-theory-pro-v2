package com.gzc.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    ILLEGAL_PHONE("0003", "手机号格式错误"),
    ILLEGAL_CODE("0003", "手机号格式错误"),
    ;

    private String code;
    private String info;

}
