package com.itheima.service;


import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckGroupService {
    //添加检查组
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);
    public List<CheckItem> findCheckitem();
    //分页查询检查组
    public PageResult findPage(QueryPageBean queryPageBean);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    public List<CheckGroup> findAll();
}
