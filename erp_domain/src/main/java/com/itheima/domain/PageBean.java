package com.itheima.domain;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:25
 */
import java.util.List;

public class PageBean<T> {

    //需要直接获取的参数
    private Integer currPage;   //当前页
    private Integer pageSize;   //每页显示的数
    private Integer totalCount; //总记录数
    private List<T> list;       //从数据库里获取到的每页数据

    //通过计算获取到的参数
    private Integer totalPage;      //总页数
    private Integer beginIndex; 	//数据库查询limit的起始参数
    private Integer begin;		    //每个页面显示的起始页数
    private Integer end;		    //每个页面显示的终止数

    //无参构造
    public PageBean() {	}

    //有参构造
    public PageBean(Integer currPage, Integer pageSize, Integer totalCount) {
        super();
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage=(totalCount-1+pageSize)/pageSize;
        this.beginIndex=(currPage-1)*pageSize;
        this.begin = 1;	//设置起始页码数为1
        this.end = 5;	//每个页面最多显示5个页码数

        if (totalPage<=end) {//当总的页数小于等于每页最多显示的页码数
            this.end=totalPage;
        }else {//当总的页数大于每页最多显示的页码数
            this.begin = this.currPage-2;
            this.end = this.currPage+2;

            if(this.end>this.totalPage) {
                this.end=this.totalPage;
                this.begin = this.totalPage-4;
            }

            if(this.begin<1) {
                this.begin=1;
                this.end=this.begin+4;
            }
        }
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public Integer getBegin() {
        return begin;
    }

    public Integer getEnd() {
        return end;
    }

}
