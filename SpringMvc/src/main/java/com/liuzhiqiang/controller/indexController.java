package com.liuzhiqiang.controller;

import com.liuzhiqiang.dao.UserMapper;
import com.liuzhiqiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Action;

/**
 * Created by lzq on 2018/1/7.
 */
@RestController
public class indexController {
    @Autowired
    public UserMapper userMapper;
    @RequestMapping("index")
    public ModelAndView index(ModelMap modelMap){
        User user = userMapper.selectByPrimaryKey(new Long("1"));
        modelMap.put("user", user);
        return new ModelAndView("index", modelMap);
    }
}
