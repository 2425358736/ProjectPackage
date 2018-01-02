package com.liuzhiqiang.controller;

import com.liuzhiqiang.dao.UserMapper;
import com.liuzhiqiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhiqiang on 2018/1/2.
 */
@RestController
public class IndexController {
    @Autowired
    public UserMapper userMapper;
    @RequestMapping("/index")
    ModelAndView index(ModelMap modelMap) {
        User user = userMapper.selectByPrimaryKey(Long.parseLong("1"));
        modelMap.put("map",user);
        return new ModelAndView("index", modelMap);
    }
}
