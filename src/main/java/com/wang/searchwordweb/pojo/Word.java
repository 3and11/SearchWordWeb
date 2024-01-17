package com.wang.searchwordweb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
public class Word implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private  String word;
    private String ukSpeak;

    @TableField(value = "date_time")
    private Date date;


    public Word(String word, String ukSpeak, Date date) {
        this.word = word;
        this.ukSpeak = ukSpeak;
        this.date = date;
    }
}
