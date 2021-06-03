package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> findPage(String str);
    public void deleteById(Integer id);
    public Long findCountByCheckItemId(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
}
