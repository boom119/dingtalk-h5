package com.dingtalk.util;

import com.dingtalk.constant.UrlConstant;
import com.dingtalk.exception.InvokeDingTalkException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 获取access_token工具类
 */
@Slf4j
public class AccessTokenUtil {

    private static String accessToken;
    private static Instant expiresAt;

    @Value("${dingtalk.app_key}")
    private static String appKey;

    @Value("${dingtalk.app_secret}")
    private static String appSecret;

    @Value("${dingtalk.agent_id}")
    private static String agentId;

    @Value("${dingtalk.corp_id}")
    private static String corpId;
    private static final ReentrantLock lock = new ReentrantLock();


    /**
     * 在使用accessToken时，请注意：
     * accessToken的有效期为7200秒（2小时），有效期内重复获取会返回相同结果并自动续期，过期后获取会返回新的accessToken。
     * 开发者需要缓存accessToken，用于后续接口的调用。因为每个应用的accessToken是彼此独立的，所以进行缓存时需要区分应用来进行存储。
     * 不能频繁调用接口，否则会受到频率拦截。
     *
     * @return
     */

    public static String getAccessToken() {
        if(accessToken == null || Instant.now().isAfter(expiresAt)) {
            lock.lock(); // 确保不会有并发请求同时尝试获取token
            try {
                if(accessToken == null || Instant.now().isAfter(expiresAt)) {
                    refreshToken();
                }
            } finally {
                lock.unlock();
            }
        }
        return accessToken;
    }

    private static void refreshToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ACCESS_TOKEN_URL);
        OapiGettokenRequest request = new OapiGettokenRequest();

        request.setAppkey("dingq87y6ea5ydh9lepl");
        request.setAppsecret("H4oJrL21_rd-UkfXONTh1CcFUm0qxLPs91QYJyKyDBJm6sRzPLOb0QvGaHU7dOHR");
        request.setHttpMethod(HttpMethod.GET.name());

        try {
            OapiGettokenResponse response = client.execute(request);
            if (response.isSuccess()) {
                accessToken = response.getAccessToken();
                // 根据expires_in设置过期时间，留出一定的缓冲时间，例如减少5分钟
                expiresAt = Instant.now().plusSeconds(response.getExpiresIn() - 300);
            } else {
                throw new InvokeDingTalkException(response.getErrorCode(), response.getErrmsg());
            }

        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }


}

