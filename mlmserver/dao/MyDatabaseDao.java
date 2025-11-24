package com.mlm.mlmserver.dao;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public interface MyDatabaseDao {
    JSONArray selectSql(String sql);

    int insertSql(String sql);

    int updateSql(String sql);

    int deleteSql(String sql);

    // 登录
    JSONObject login(String table, String elements, String username, String password);

    // 查询
    JSONArray select(String table, String elements, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue, String order, Integer offset, Integer count);

    JSONArray selectByCollection(String table, String elements, JSONArray collection, String order, Integer offset, Integer count);

    int selectCountByCollection(String table, JSONArray collection);

    // 删除
    int delete(String table, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue);

    int deleteByCollection(String table, JSONArray collection);

    // 添加
    int insert(JSONObject jsonObject);

    int insertBatch(String table, JSONArray elements, JSONArray collection);

    // 更新
    int update(String table, JSONArray collection, String field, Object value);
}