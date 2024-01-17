package com.wang.searchwordweb.pojo;

/**
 * Copyright 2024 json.cn
 */
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class basic {

    private List<String> exam_type;
    @JSONField(name = "us-phonetic")
    private String us_phonetic;
    private String phonetic;
    @JSONField(name = "uk-phonetic")
    private String uk_phonetic ;
    @JSONField(name = "uk-speech")
    private String uk_speech;
    private List<String> explains;
    @JSONField(name = "us-speech")
    private String us_speech;

    @Override
    public String toString() {
        return "basic{" +
                "exam_type=" + exam_type +
                ", us_phonetic='" + us_phonetic + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", uk_phonetic='" + uk_phonetic + '\'' +
                ", uk_speech='" + uk_speech + '\'' +
                ", explains=" + explains +
                ", us_speech='" + us_speech + '\'' +
                '}';
    }
}