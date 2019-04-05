package com.nidara.sabanasblancas.gestion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InsertDelayInterceptor extends HandlerInterceptorAdapter {

    @Value("${gestion.sabanasblancas.spinnerDelayMillis:0}")
    private long spinnerDelay;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if (spinnerDelay > 0) {
            Thread.sleep(spinnerDelay);
        }

        return true;
    }

}
