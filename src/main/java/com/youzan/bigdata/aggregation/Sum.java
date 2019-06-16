package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.base.Maps2;

import java.util.Map;

/**
 * 统计和
 * Created by maoxiajun on 18/1/12.
 */
public class Sum extends BaseAggs<Sum> {

    private String field;
    private Integer missing;

    /**
     * 设定统计查询返回的结果名称
     * @param aggName 统计结果名称
     */
    public Sum(String aggName) {
        super(aggName);
    }

    /**
     * 指定需要统计的字段
     * @param field 字段名
     * @return self
     */
    public Sum field(String field) {
        this.field = field;
        return this;
    }

    /**
     * 指定统计字段中不存在值的记录返回的默认值，比如sum(A), 文档1不存在A字段, 设置missing=10, 文档1返回10作为本条记录的统计值
     * @param missing 默认值
     * @return self
     */
    public Sum missing(int missing) {
        this.missing = missing;
        return this;
    }

    @Override
    public String cond() {
        return "sum";
    }

    @Override
    public Object value() {
        Map<String, Object> value = Maps2.of("field", this.field);
        if (null != missing) {
            value.put("missing", missing);
        }
        return toBodyWithNested(value);
    }
}
