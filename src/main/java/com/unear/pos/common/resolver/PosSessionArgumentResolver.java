package com.unear.pos.common.resolver;

import com.unear.pos.common.dto.PosSessionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PosSessionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentPosSession.class)
                && parameter.getParameterType().equals(PosSessionInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        PosSessionInfo sessionInfo = (PosSessionInfo) request.getSession().getAttribute("posSessionInfo");
        if (sessionInfo == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return sessionInfo;
    }

}
