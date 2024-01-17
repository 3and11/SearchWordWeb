package com.wang.searchwordweb.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class searchResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private basic basic;
    private List<web> web;
}
