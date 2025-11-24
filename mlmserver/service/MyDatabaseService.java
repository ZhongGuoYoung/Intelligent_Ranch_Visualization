package com.mlm.mlmserver.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.utils.PageUtils;

public interface MyDatabaseService {
    // 以下4个方法，不可通过接口暴露到外面
    JSONArray selectSql(String sql);

    int insertSql(String sql);

    int updateSql(String sql);

    int deleteSql(String sql);

    // 登录
    JSONObject login(String table, String elements, String username, String password);

    // 查询
    JSONArray select(String table, String elements, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue, String group, String order, Integer offset, Integer count);

    JSONArray select(String table, String elements, JSONArray collection, String group, String order, Integer offset, Integer count);

    // 分页查询。
    PageUtils selectByPaging(String table, String elements, JSONArray collection, String order, int page, int pageSize, boolean prevPage);

    // 删除
    int delete(String table, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue);

    int delete(String table, JSONArray where);

    int deleteByIn(String table, boolean binary, String field, String[] values);

    int deleteByLike(String table, boolean binary, String field, String value);

    int deleteByEqual(String table, boolean binary, String field, String value);

    int deleteByBetween(String table, boolean binary, String field, String leftValue, String rightValue);

    // 添加
    int insert(String table, JSONArray elements, JSONArray values);

    int insertBatch(String table, JSONArray elements, JSONArray collection);

    // 更新
    int update(String table, JSONArray collection, String field, Object value);
}
