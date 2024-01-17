package com.wang.searchwordweb.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Data
public class web implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String key;
    private List<String> value;
}
