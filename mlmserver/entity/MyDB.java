package com.mlm.mlmserver.entity;

import com.alibaba.fastjson2.JSONArray;
import com.mlm.mlmserver.config.ValidatedRules;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MyDB {
    private String elements = "*";   // 查询字段。默认：*。多个属性用英文逗号隔开

    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Page.class)
    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Select.class)
    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Delete1.class)
    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Delete2.class)
    private String table;      // 表名称。必须
    private boolean binary = false;    // 区分大小写。默认：false
    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Delete1.class)
    private String field;      // 条件字段。
    @NotBlank(message = "必须属性，不能为空", groups = ValidatedRules.Delete1.class)
    private String type;       // 查询类型。可选值有 >、>=、=、<=、<、!=、like、notLike、leftLike、notLeftLike、rightLike、notRightLike、between、notBetween、leftBetween、notLeftBetween、rightBetween、notRightBetween、in、notIn、isNull、isNotNull
    private String value;      // 条件字段值。>、>=、=、<=、<、!=、like、leftLike、rightLike value
    private String[] values;   // 条件字段值。in values
    private String leftValue;  // 条件字段值。between、leftBetween、rightBetween leftValue and rightValue
    private String rightValue; // 条件字段值。between、leftBetween、rightBetween leftValue and rightValue
    private String group;      // 分组字段。
    private String order;      // 排序字段。多个属性用英文逗号隔开
    private Integer offset;    // limit offset,count
    private Integer count;     // limit offset,count

    @Size(message = "必须属性，不能为空", min = 1, groups = ValidatedRules.Delete2.class)
    private JSONArray collection = new JSONArray();

    private int page = 1;
    private int pageSize = 10;   // pageSize与count属性含义一致
    private boolean prevPage = true;// 当 page > 1 时，如果当前页无数据，则自动查询上一页。只触发一次。

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public boolean getBinary() {
        return binary;
    }

    public void setBinary(boolean binary) {
        this.binary = binary;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public JSONArray getCollection() {
        return collection;
    }

    public void setCollection(JSONArray collection) {
        this.collection = collection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }
}
