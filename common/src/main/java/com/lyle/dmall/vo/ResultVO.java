package com.lyle.dmall.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("")
public class ResultVO {

//    响应给前端的状态码
    private int code;

//    响应给前端的消息
    private String msg;

//    响应给前端的数据
    private Object data;
}
