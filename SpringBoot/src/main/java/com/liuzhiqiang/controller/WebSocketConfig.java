package com.liuzhiqiang.controller;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author 刘志强
 * @created 2018/7/11.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * Created by JJJ on 2017/5/11.
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
