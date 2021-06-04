package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setCheckGroupAndSetmeal(Map map);
    public Page<Setmeal> findPage(String queryPageBean);
}
