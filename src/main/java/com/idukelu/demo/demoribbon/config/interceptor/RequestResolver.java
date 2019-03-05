package com.idukelu.demo.demoribbon.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

public class RequestResolver {

    private RequestResolver() {}

    private static final String SEPARATOR = System.getProperty("line.separator");

    private static long innerTime;

    private static long outerTime;

    public static StringBuilder resolve(HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();


        builder.append("In Site: ").append(SEPARATOR)
                .append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<").append(SEPARATOR)
                .append(" ").append(request.getMethod()).append(" : ").append(request.getRequestURL()).append(SEPARATOR)
                .append("【Inbound】").append(SEPARATOR)
                .append(" Encoding: ").append(request.getCharacterEncoding()).append("; ");

        String contentType = request.getContentType();
        if (contentType != null) {
            builder.append(" ContentType: ").append(contentType);
        }
        builder.append(SEPARATOR);

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.size() > 0) {
            builder.append(" Params: ");
            for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                builder.append("[").append(param.getKey()).append( ": ").append(Arrays.toString(param.getValue())).append("]; ");
            }
        }
        builder.append(SEPARATOR);

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames.hasMoreElements()) {
            builder.append(" Headers: ");
            while (headerNames.hasMoreElements()){
                builder.append("[").append(headerNames.nextElement()).append(":")
                        .append(request.getHeader(headerNames.nextElement())).append("]; ");
            }
        }

        innerTime = System.currentTimeMillis();

        return builder;
    }

    public static StringBuilder resolve(HttpServletResponse response) {

        StringBuilder builder = new StringBuilder();
        builder.append("Out Site: ").append(SEPARATOR)
                .append("【Outbound】").append(SEPARATOR)
                .append(" Status: ").append(response.getStatus()).append("; ")
                .append("Encoding: ").append(response.getCharacterEncoding()).append("; ");

        String contentType = response.getContentType();
        if (contentType != null) {
            builder.append("ContentType: ").append(contentType);
        }
        builder.append(SEPARATOR);

        Collection<String> headerNames = response.getHeaderNames();
        if (headerNames.size() > 0) {
            builder.append(" Header: ");
            for (String headerName : headerNames) {
                builder.append("[").append(headerName).append(":")
                        .append(response.getHeader(headerName)).append("]; ");
            }
        }

        outerTime = System.currentTimeMillis();
        builder.append(SEPARATOR).append(" Time-consuming：").append(outerTime-innerTime).append(" ms").append(SEPARATOR)
                .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return builder;
    }
}