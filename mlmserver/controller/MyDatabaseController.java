package com.mlm.mlmserver.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.config.ValidatedRules;
import com.mlm.mlmserver.entity.MyDB;
import com.mlm.mlmserver.entity.Response;
import com.mlm.mlmserver.service.MyDatabaseService;
import com.mlm.mlmserver.utils.PageUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 操作数据库接口。
 */
@RestController
@RequestMapping("/db/")
public class MyDatabaseController {
    @Resource
    private MyDatabaseService dbs;

    // 查询记录
    @GetMapping("select")
    public Response<JSONArray> selectByGet(@Validated(ValidatedRules.Select.class) MyDB mydb) {
        JSONArray result = dbs.select(mydb.getTable(), mydb.getElements(), mydb.getBinary(), mydb.getField(), mydb.getType(), mydb.getValue(), mydb.getValues(), mydb.getLeftValue(), mydb.getRightValue(), mydb.getGroup(), mydb.getOrder(), mydb.getOffset(), mydb.getCount());
        return new Response<JSONArray>().success(result);
    }

    @PostMapping("select")
    public Object selectByPost(@Validated(ValidatedRules.Select.class) @RequestBody MyDB mydb) {
        JSONArray result = mydb.getCollection().isEmpty()
                ? dbs.select(mydb.getTable(), mydb.getElements(), mydb.getBinary(), mydb.getField(), mydb.getType(), mydb.getValue(), mydb.getValues(), mydb.getLeftValue(), mydb.getRightValue(), mydb.getGroup(), mydb.getOrder(), mydb.getOffset(), mydb.getCount())
                : dbs.select(mydb.getTable(), mydb.getElements(), mydb.getCollection(), mydb.getGroup(), mydb.getOrder(), mydb.getOffset(), mydb.getCount());
        return new Response<JSONArray>().success(result);
    }

    // 分页查询。
    @PostMapping("selectByPaging")
    public Object selectByPagingPost(@Validated(ValidatedRules.Page.class) @RequestBody MyDB mydb) {
        PageUtils result = dbs.selectByPaging(mydb.getTable(), mydb.getElements(), mydb.getCollection(), mydb.getOrder(), mydb.getPage(), mydb.getPageSize(), mydb.getPrevPage());
        return new Response<PageUtils>().success(result);
    }


    // 删除。返回删除的行数。get请求只实现单条件删除
    @GetMapping("delete")
    public Object deleteByGet(@Validated(ValidatedRules.Delete1.class) MyDB mydb) {
        int result = dbs.delete(mydb.getTable(), mydb.getBinary(), mydb.getField(), mydb.getType(), mydb.getValue(), mydb.getValues(), mydb.getLeftValue(), mydb.getRightValue());
        return new Response<Object>().success(result);
    }

    // 删除。返回删除的行数。post请求可以实现多条件删除
    @PostMapping("delete")
    public Object deleteByPost(@Validated(ValidatedRules.Delete2.class) @RequestBody MyDB mydb) {
        int result = dbs.delete(mydb.getTable(), mydb.getCollection());
        return new Response<Object>().success(result);
    }


    // 添加。返回主键自增 id 的值。（0：无添加）
    @PostMapping("insert")
    public Object insertByPost(@RequestBody JSONObject obj) {
        // 唯一约束字段。不建议使用。唯一约束应该在数据库中设置
        // String uniqueField = obj.getString("uniqueValue");	obj.remove("uniqueValue");
        // 唯一约束字段值。不建议使用。唯一约束应该在数据库中设置
        // Object uniqueValue = obj.getString("uniqueField");	obj.remove("uniqueField");
        // if (uniqueValue == null) { uniqueValue = obj.getString(uniqueField); }

        // 表名称。必须
        String table = obj.getString("table");
        obj.remove("table");
        // 校验
        String validate = "";
        if (table == null || table.isEmpty()) {
            validate += "table：必须参数，且不能为空。";
        }
        if (validate.isEmpty()) {
            JSONArray elements = new JSONArray();
            JSONArray values = new JSONArray();
            obj.forEach((k, v) -> {
                if (v != null) {
                    elements.add(k);
                    values.add(v);
                }
            });
            int result = dbs.insert(table, elements, values);
            return new Response<Object>().success(result);
        } else {
            return new Response<String>().error(0, validate);
        }
    }

    // 添加。返回添加的行数。（0：无添加）
    @PostMapping("insertBatch")
    public Object insertBatchByPost(@RequestBody JSONObject obj) {
        // 表名称。必须
        String table = obj.getString("table");
        // 插入数据集合。必须
        JSONArray collection = obj.getJSONArray("collection");
        // 校验
        String validate = "";
        if (table == null || table.isEmpty()) {
            validate += "table：必须参数，且不能为空。";
        }
        if (collection == null || collection.size() == 0) {
            validate += "elementsValues：必须参数，且不能为空。";
        }
        if (validate.isEmpty()) {
            JSONArray elements = collection.getJSONObject(0).keySet().stream().collect(Collectors.toCollection(JSONArray::new));
            int result = dbs.insertBatch(table, elements, collection);
            return new Response<Object>().success(result);
        } else {
            return new Response<String>().error(0, validate);
        }
    }


    // 更新。返回更新的行数。（0：无更新）（只有 = 更新）
    @PostMapping("update")
    public Object updateByPost(@RequestBody JSONObject obj) {
        // 唯一约束字段。不建议使用。唯一约束应该在数据库中设置
        // String uniqueField = obj.getString("uniqueValue");	obj.remove("uniqueValue");
        // 唯一约束字段值。不建议使用。唯一约束应该在数据库中设置
        // Object uniqueValue = obj.getString("uniqueField");	obj.remove("uniqueField");
        // if (uniqueValue == null) { uniqueValue = obj.getString(uniqueField); }

        // 表名称。必须
        String table = obj.getString("table");
        obj.remove("table");
        // 条件字段。必须。
        String field = obj.getString("field");
        obj.remove("field");
        // 条件字段值。必须
        Object value = obj.get("value");
        obj.remove("value");
        // 校验
        String validate = "";
        if (table == null || table.isEmpty()) {
            validate += "table：必须参数，且不能为空。";
        }
        if (field == null || field.isEmpty()) {
            validate += "field：必须参数，且不能为空。";
        }
        if (value == null || "".equals(value)) {
            validate += "value：必须参数，且不能为空。";
        }
        if (validate.isEmpty()) {
            JSONArray collection = new JSONArray();
            obj.forEach((k, v) -> {
                if (v != null) {
                    JSONObject item = new JSONObject();
                    item.put("key", k);
                    item.put("val", v);
                    collection.add(item);
                }
            });
            int result = dbs.update(table, collection, field, value);
            return new Response<Object>().success(result);
        } else {
            return new Response<String>().error(0, validate);
        }
    }

}
