package com.wbw.apidriver.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignAuthRequestInterceptor implements RequestInterceptor {

    private final String serviceName;

    public FeignAuthRequestInterceptor(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("serviceName", serviceName);
    }
}
