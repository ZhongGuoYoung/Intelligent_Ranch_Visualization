package com.mlm.mlmserver.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.dao.MyDatabaseDao;
import com.mlm.mlmserver.service.MyDatabaseService;
import com.mlm.mlmserver.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MyDatabaseServiceImpl implements MyDatabaseService {
    @Resource
    private MyDatabaseDao dbd;

    @Override
    public JSONArray selectSql(String sql) {return dbd.selectSql(sql);}

    @Override
    public int insertSql(String sql) {return dbd.insertSql(sql);}

    @Override
    public int updateSql(String sql) {return dbd.updateSql(sql);}

    @Override
    public int deleteSql(String sql) {return dbd.deleteSql(sql);}

    @Override
    public JSONObject login(String table, String elements, String username, String password) {return dbd.login(table, elements, username, password);}

    //	查询
    @Override
    public JSONArray select(String table, String elements, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue, String group, String order, Integer offset, Integer count) {
        type = getType(type, "");
        JSONArray result = dbd.select(table, elements, binary, field, type, value, values, leftValue, rightValue, order, offset, count);
        return group == null || group.isEmpty() ? result : groupByElement(result, group);
    }

    @Override
    public JSONArray select(String table, String elements, JSONArray collection, String group, String order, Integer offset, Integer count) {
//		集合参数初始化
        for (int i = 0; i < collection.size(); i++) {
            JSONObject item = collection.getJSONObject(i);
            item.put("type", getType(item.getString("type"), ""));
            if (!item.containsKey("binary")) {item.put("binary", false);}
            if (!item.containsKey("connector")) {item.put("connector", "and");}
        }
        JSONArray result = dbd.selectByCollection(table, elements, collection, order, offset, count);
        return group == null || group.isEmpty() ? result : groupByElement(result, group);
    }

    //	 分页查询。
    @Override
    public PageUtils selectByPaging(String table, String elements, JSONArray collection, String order, int page, int pageSize, boolean prevPage) {
//		集合参数初始化
        for (int i = 0; i < collection.size(); i++) {
            JSONObject item = collection.getJSONObject(i);
            item.put("type", getType(item.getString("type"), ""));
            if (!item.containsKey("binary")) {item.put("binary", false);}
            if (!item.containsKey("connector")) {item.put("connector", "and");}
        }
        PageUtils pu = new PageUtils(page, pageSize);
        int offset = pu.getOffset();
        JSONArray result = dbd.selectByCollection(table, elements, collection, order, offset, pageSize);
//		查询上一页
        if (result.isEmpty() && offset >= pageSize && prevPage) {
            offset = offset - pageSize;
            pu.setPage(page - 1);
            result = dbd.selectByCollection(table, elements, collection, order, offset, pageSize);
        }
        pu.setData(result);
//		获取总条目数
        int total = dbd.selectCountByCollection(table, collection);
        pu.setTotal(total);
        pu.setPages(pu.getPages(total, pageSize));
        return pu;
    }

    //	删除
    @Override
    public int delete(String table, boolean binary, String field, String type, String value, String[] values, String leftValue, String rightValue) {
        type = getType(type, "=");
        return dbd.delete(table, binary, field, type, value, values, leftValue, rightValue);
    }

    @Override
    public int deleteByIn(String table, boolean binary, String field, String[] values) {
        return dbd.delete(table, binary, field, "in", null, values, null, null);
    }

    @Override
    public int deleteByLike(String table, boolean binary, String field, String value) {
        return dbd.delete(table, binary, field, "like", value, null, null, null);
    }

    @Override
    public int deleteByEqual(String table, boolean binary, String field, String value) {
        return dbd.delete(table, binary, field, "eq", value, null, null, null);
    }

    @Override
    public int deleteByBetween(String table, boolean binary, String field, String leftValue, String rightValue) {
        return dbd.delete(table, binary, field, "between", null, null, leftValue, rightValue);
    }

    @Override
    public int delete(String table, JSONArray collection) {
//		集合参数初始化
        for (int i = 0; i < collection.size(); i++) {
            JSONObject item = collection.getJSONObject(i);
            item.put("type", getType(item.getString("type"), "="));
            if (!item.containsKey("binary")) {item.put("binary", false);}
            if (!item.containsKey("connector")) {item.put("connector", "and");}
        }
        return dbd.deleteByCollection(table, collection);
    }

    //	添加
    @Override
    public int insert(String table, JSONArray elements, JSONArray values) {
        JSONObject target = new JSONObject();
        target.put("table", table);
        target.put("elements", elements);
        target.put("elementsValue", values);
        int code = dbd.insert(target);
//		执行完 insert 后，主键自增 id 的值会被绑定到键 id 上。
        return code != 0 ? target.getIntValue("id", -1) : code;
    }

    //	批量添加
    @Override
    public int insertBatch(String table, JSONArray elements, JSONArray collection) {
        return dbd.insertBatch(table, elements, collection);
    }

    //	更新
    @Override
    public int update(String table, JSONArray collection, String field, Object value) {
        return dbd.update(table, collection, field, value);
    }


    /**
     * 根据 element 将 array 分组
     */
    public JSONArray groupByElement(JSONArray array, String element) {
        JSONArray result = new JSONArray();
        JSONObject tree = new JSONObject();
        array.stream()
             .map(JSONObject.class::cast)
             .forEachOrdered(item -> {
                 String key = item.getString(element);
                 if (tree.containsKey(key)) {
                     result.getJSONObject(tree.getIntValue(key)).getJSONArray("data").add(item);
                 } else {
                     tree.put(key, tree.size());
                     result.add(new JSONObject() {{
                         put("key", element);
                         put("val", key);
                         put("data", new JSONArray() {{add(item);}});
                     }});
                 }
             });
        return result;
    }


    /**
     * 将 =、!=、>、>=、<=、< 转换为字母，type == null 时，type = defaultType。
     * 因为在 <when test="type == ''"></when> 中无法输入<。
     * 在 type == '?' 中，? 至少需要两个字母，一个字母会被识别为 char
     */
    public String getType(String type, String defaultType) {
        String t = type == null ? defaultType : type;
        switch (t) {
            case "=":
                t = "eq";
                break;
            case "!=":
                t = "ne";
                break;
            case ">":
                t = "gt";
                break;
            case ">=":
                t = "ge";
                break;
            case "<=":
                t = "le";
                break;
            case "<":
                t = "lt";
                break;
        }
        return t;
    }
}