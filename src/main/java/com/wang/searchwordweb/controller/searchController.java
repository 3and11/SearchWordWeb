package com.wang.searchwordweb.controller;

import cn.hutool.core.date.DateUnit;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.searchwordweb.mapper.wordMapper;
import com.wang.searchwordweb.config.youDaoConf;
import com.wang.searchwordweb.pojo.*;
import com.wang.searchwordweb.utils.AuthV3Util;
import com.wang.searchwordweb.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/search")
public class searchController {
    @Autowired
    youDaoConf youDaoConf;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    wordMapper wordMapper;
    String wordApp = "WORD_APP:";
    @RequestMapping("/word={word}")
    public  Object  searchWord(@PathVariable("word") String word , Model model) throws NoSuchAlgorithmException {
        log.info("查找单词：{}",word);
        //从redis中查找
        searchResult result=searchFromRedis(word);
        //如果redis中没有从api中查找将数据存入redis
        if (result==null){
            searchResult apiResult=searchFromApi(word);
            redisTemplate.opsForValue().set(wordApp+word,apiResult);

            model.addAttribute("basic",apiResult.getBasic());
            model.addAttribute("web",apiResult.getWeb());

            return "result" ;
        }else {
            model.addAttribute("basic",result.getBasic());
            model.addAttribute("web",result.getWeb());
        }

        return "result" ;
    }

    private searchResult searchFromRedis(String word) {
        return (searchResult) redisTemplate.opsForValue().get(wordApp+word);
    }

    private searchResult searchFromApi(String word) throws NoSuchAlgorithmException {
        // 添加请求参数
        Map<String, String[]> params = createRequestParams();
        params.put("q",new String[]{word});

        // 添加鉴权相关参数
        AuthV3Util.addAuthParams(youDaoConf.getAppKey(), youDaoConf.getSecretKey(), params);
        // 请求api服务
        byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");

        // 解析结果
        searchResult apiResult = new searchResult();
        JSONObject jsonObject = JSON.parseObject(new String(result));
        log.info("查找资源：{}",new String(result));
        basic basic = JSON.parseObject(JSONObject.toJSONString(jsonObject.get("basic")), basic.class);

        List<web> web = JSONArray.parseArray(JSON.toJSONString(jsonObject.get("web")), web.class);
        apiResult.setWeb(web);
        apiResult.setBasic(basic);
        return apiResult;
    }


    /**
     * 创建请求参数
     * @return
     */
    private static Map<String, String[]> createRequestParams() {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */
        String from = "en";
        String to = "zh";
//        String vocabId = "";
        return new HashMap<>() {{
            put("from", new String[]{from});
            put("to", new String[]{to});
//            put("vocabId", new String[]{vocabId});
        }};
    }

}
