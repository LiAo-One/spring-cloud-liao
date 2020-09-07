package com.liao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * TODO:
 * @author LiAo
 * @date 2020/8/4 16:09
 */
@Data               // 所有的get set toString .....
@NoArgsConstructor  // 创建一个无参的构造函数
// @AllArgsConstructor // 自动添加构造函数
public class Payment implements Serializable {
    private Long id;

    private String serial;

    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }
}