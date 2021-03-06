package huacloud.jwdsj.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import base.model.Result;
import princess.common.annotations.PermissionAx;
import princess.common.constants.BaseConstants.Ids;
import princess.common.core.util.json.JsonUtil;
import princess.common.core.util.web.WebUtil;
import princess.common.security.Principal;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Principal principal = SecurityContextHolder.getPrincipal();

        String path = WebUtil.getPathWithinApplication(request);
        if (!authc(path)) {
            return true;
        }

        if (principal == null) {
            principal = Principal.NONE;
        }

        if (Principal.NONE.equals(principal)) {
            Result<?> result = Result.ofMessage(401, "未授权的访问");
            String json = JsonUtil.encode(result);
            WebUtil.writeJson(json, request, response);
            return false;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            PermissionAx ax = hm.getMethodAnnotation(PermissionAx.class);
            if (ax != null) {
                String ryId = principal.getRyId();
                String[] gnIds = principal.getGnIds();
                if (!Ids.ADMIN_ID.equals(ryId) && !intersect(ax.value(), gnIds)) {
                    Class<?> returnType = (Class<?>) hm.getReturnType().getGenericParameterType();
                    if (Result.class.isAssignableFrom(returnType)) {
                        Result<?> result = Result.ofMessage(403, "没有访问该资源的权限");
                        String json = JsonUtil.encode(result);
                        WebUtil.writeJson(json, request, response);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean intersect(String[] array, String[] valueToFinds) {
        for (String value : array) {
            for (String valueToFind : valueToFinds) {
                if (valueToFind.equals(value)) {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean authc(String path) {
        if (path.startsWith("/s/")//
                || path.startsWith("/api/login") //
                || path.startsWith("/api/logout")) {
            return false;
        }
        return true;
    }
}
