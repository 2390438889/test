package com.lujie.mongo_demo.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author:卢杰
 * 分页信息实体类
 * 时间：2018-8-16
 */
@Component
public class PageBean implements Serializable {
    /**
     * request请求数据
     */
    private Integer page;   //页码参数
    private Integer limit;  //每页数据量
    /**
     * response响应数据
     */
    private String msg; //状态信息字段名称
    private Integer code;   //数据状态的字段名称
    private Integer count;  //数据总数的字段名称
    private List data;      //数据列表的字段名称

    public PageBean() {
    }

    public PageBean(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public PageBean(String msg, Integer code, Integer count, List data) {
        this.msg = msg;
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", limit=" + limit +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
