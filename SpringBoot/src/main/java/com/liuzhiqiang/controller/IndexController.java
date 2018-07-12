package com.liuzhiqiang.controller;

import com.liuzhiqiang.dao.UserMapper;
import com.liuzhiqiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/webRtc")
    ModelAndView webRtc(ModelMap modelMap) {
        return new ModelAndView("webrt", modelMap);
    }

    @RequestMapping("/webRtcTwo")
    ModelAndView webRtcTwo(ModelMap modelMap) {
        return new ModelAndView("webrt_two", modelMap);
    }
}
