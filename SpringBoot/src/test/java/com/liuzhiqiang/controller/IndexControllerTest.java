package com.liuzhiqiang.controller;

import com.liuzhiqiang.SpringBootAppliction;
import com.liuzhiqiang.dao.UserMapper;
import com.liuzhiqiang.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by SCKJ on 2018/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = SpringBootAppliction.class)
@WebAppConfiguration
public class IndexControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    public UserMapper userMapper;
    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())  //默认每次执行请求后都做的动作
                .alwaysExpect(status().isOk())
                .build();
    }
    @Test
    public void index() throws Exception {
        MvcResult responseString = mockMvc.perform(
                get("/index")
                        .param("id","11")
                        .param("name","18")
                        .requestAttr("id",12))
                .andExpect(status().isOk())
                .andReturn();
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);
        User user = userMapper.selectByPrimaryKey(Long.parseLong("1"));
        assertTrue(responseString == responseString );
    }

}