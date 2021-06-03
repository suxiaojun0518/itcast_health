package com.itheima.dao;


import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    public List<CheckItem> findCheckitem();
    public Page<CheckGroup> findPage(String queryPageBean);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void deleteAssociation(Integer id);
    public void edit(CheckGroup checkGroup);
}
