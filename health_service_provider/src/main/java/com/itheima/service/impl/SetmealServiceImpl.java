package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.SetmealDao;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    //依赖注入
    @Autowired
    private SetmealDao setmealDao;
    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setmealDao.add(setmeal);
        setCheckGroupAndSetmeal(setmeal.getId(),checkGroupIds);
    }
    //设置检查组合和检查项的关联关系
    public void setCheckGroupAndSetmeal(Integer setmealId,Integer[] checkGroupIds){
        if(checkGroupIds != null && checkGroupIds.length >0){
            for(Integer checkGroupId :checkGroupIds){
                Map<String, Integer> map =new HashMap<>();
                map.put("setmeal_id",setmealId);
                map.put("checkgroup_id",checkGroupId);
                setmealDao.setCheckGroupAndSetmeal(map);
            }
        }
    }
}
