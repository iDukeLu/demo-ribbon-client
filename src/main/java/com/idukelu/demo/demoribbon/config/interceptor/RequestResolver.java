package com.idukelu.demo.demoribbon.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class RequestResolver {

    private RequestResolver() {}

    private static final String SEPARATOR = System.getProperty("line.separator");

    private static long innerTime;

    private static long outerTime;

    public static StringBuilder resolve(HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();


        builder.append("In Site: ").append(SEPARATOR)
                .append("=============================================================================================").append(SEPARATOR)
                .append(" ").append(request.getMethod()).append(" : ").append(request.getRequestURL()).append(SEPARATOR)
                .append("【Inner】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append(SEPARATOR)
                .append(" Encoding: ").append(request.getCharacterEncoding());

        String contentType = request.getContentType();
        if (contentType != null) {
            builder.append(" ContentType: ").append(contentType);
        }
        builder.append(SEPARATOR);

//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames.hasMoreElements()) {
//            builder.append(" Header: ").append(SEPARATOR);
//            while (headerNames.hasMoreElements()){
//                builder.append("    ").append(headerNames.nextElement()).append(": ")
//                        .append(request.getHeader(headerNames.nextElement())).append(SEPARATOR);
//            }
//        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.size() > 0) {
            builder.append(" Param:").append(SEPARATOR);
            for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                builder.append("    ").append(param.getKey() + ": " + Arrays.toString(param.getValue())).append(SEPARATOR);
            }
        }

        builder.append(" Time: ").append(SimpleDateFormat.getDateTimeInstance().format(new Date())).append(SEPARATOR)
                .append(" --------------------------------------------------------------------------------------------");

        innerTime = System.currentTimeMillis();

        return builder;
    }

    public static StringBuilder resolve(HttpServletResponse response) {

        StringBuilder builder = new StringBuilder();
        builder.append("Out Site: ").append(SEPARATOR)
                .append("【Outer】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<").append(SEPARATOR)
                .append(" Status: ").append(response.getStatus()).append(SEPARATOR)
                .append(" Encoding: ").append(response.getCharacterEncoding());

        String contentType = response.getContentType();
        if (contentType != null) {
            builder.append(" ContentType: ").append(contentType);
        }
        builder.append(SEPARATOR);

//        Collection<String> headerNames = response.getHeaderNames();
//        if (headerNames.size() > 0) {
//            builder.append(" Header: ").append(SEPARATOR);
//            for (String headerName : headerNames) {
//                builder.append("    ").append(headerName).append(": ")
//                        .append(response.getHeader(headerName)).append(SEPARATOR);
//            }
//        }

        builder.append(" Time: ").append(SimpleDateFormat.getDateTimeInstance().format(new Date())).append(SEPARATOR);

        outerTime = System.currentTimeMillis();
        builder.append(" --------------------------------------------------------------------------------------------").append(SEPARATOR)
                .append(" Time-consuming：").append((outerTime-innerTime)*0.01).append(" s").append(SEPARATOR)
                .append("=============================================================================================");

        return builder;
    }
}
