package com.wang.searchwordweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.searchwordweb.pojo.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface wordMapper extends BaseMapper<Word> {
    List<Word> selectToday();

}
