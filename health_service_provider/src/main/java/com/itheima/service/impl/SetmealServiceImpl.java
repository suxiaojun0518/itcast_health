package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private JedisPool jedisPool;
    //依赖注入
    @Autowired
    private SetmealDao setmealDao;
    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setmealDao.add(setmeal);
        this.setCheckGroupAndSetmeal(setmeal.getId(),checkGroupIds);
        //将图片名称保存到redis集合中
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,fileName);

    }
    //分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询是基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page =setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
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
