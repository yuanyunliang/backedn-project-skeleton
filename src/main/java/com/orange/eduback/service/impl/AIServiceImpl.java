package com.orange.eduback.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.orange.eduback.service.AIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.messages.Message;

@Service
public class AIServiceImpl implements AIService {

    private static final Logger log = LoggerFactory.getLogger(AIServiceImpl.class);
    //调用AI接口的客户端标识
    @Value("${ai.clientId}")
    private String clientId;
    //AI服务提供赏
    @Value("${ai.provider.openai}")
    private String providerId;
    //AI服务的baseURL
    @Value("${ai.server.baseURL}")
    private String baseUrl;

    /**
     * 调用AI中台的chat接口
     * @param query
     * @return
     */
    @Override
    public String query(String query) {
        log.info("调用AI中台的AI服务商 {},chat接口,查询内容:{}", providerId, query);
        JSONObject param = JSONUtil.createObj();
        param.put("clientId", clientId);
        param.put("providerId", providerId);
        param.put("question", query);

        String result;
        try {
            result = HttpUtil.createPost(baseUrl + "/chat")
                    .header("Content-Type", "application/json")
                    .body(param.toString())
                    .execute()
                    .body();
            log.info("调用AI中台的AI服务商 {},chat接口,查询结果:{}", providerId, result);
        } catch (Exception e) {
            log.error("调用AI中台的AI服务商 {},chat接口时发生错误: {}", providerId, e.getMessage());
            return "Error occurred while querying AI service";
        }

        JSONObject message = JSONUtil.parseObj(result);
        log.info("JSONObject message的值：{},content:{}", message, message.get("content"));
        return JSONUtil.toJsonStr(message.get("content"));//message.get("content").toString();
    }
}
