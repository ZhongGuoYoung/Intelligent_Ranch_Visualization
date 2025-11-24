package com.mlm.mlmserver.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.entity.Response;
import com.mlm.mlmserver.service.WordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/word/")
public class WordController {
    @Resource
    private WordService ws;

    @PostMapping("create")
    public Object create(@RequestBody JSONObject word) {
        return new Response<Object>().success(ws.create(word));
    }

}
