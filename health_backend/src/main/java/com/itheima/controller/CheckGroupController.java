package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组管理
 * **/

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    //依赖注入
    @Reference
    private CheckGroupService checkGroupService;

    //添加检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.add(checkGroup,checkitemIds);

        }catch (Exception e){
            //新增失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        //新增成功
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    //查询检查项
    @RequestMapping("/findCheckitem")
    public Result findCheckitem(){
        try {
            List<CheckItem> list = checkGroupService.findCheckitem();
            return new Result(true, MessageConstant.ADD_CHECKITEM_FAIL,list);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }
    //分页查询检查组
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.findPage(queryPageBean);
        return pageResult;
    }

    //查询检查项
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckGroup checkGroup =checkGroupService.findById(id);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS,checkGroup);
        }catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

    }

    //通过检查组Id查询检查项Id
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try {
            List<Integer> findCheckItemIdsByCheckGroupId =checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS,findCheckItemIdsByCheckGroupId);
        }catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
    //编辑检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.edit(checkGroup,checkitemIds);

        }catch (Exception e){
            //编辑失败
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        //编辑成功
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    //查询所有
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        if(checkGroupList != null && checkGroupList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
            result.setData(checkGroupList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);}
}
