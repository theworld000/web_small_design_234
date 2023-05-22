package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    int statu;
    String msg;
    boolean success = true;
    String fileName;

    public static ResponseResult fail(){
        return new ResponseResult(500, "出错了", false, null);
    }
}
