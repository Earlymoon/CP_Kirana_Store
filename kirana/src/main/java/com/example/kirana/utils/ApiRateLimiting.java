package com.example.kirana.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.LogRecord;

@Component
public class ApiRateLimiting implements Filter {
    private static final int LIMIT=10;

//    atomicinteger used because of its thread safe prorperty
    private AtomicInteger countRequest=new AtomicInteger(0);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(countRequest.incrementAndGet()>LIMIT){
            ((HttpServletResponse)response).setStatus(429);
            response.getWriter().write("You are sending too many requests, Wait for some time!");
        }else{
            chain.doFilter(request,response);
        }

        resetCountRequestAfterOneMinute();



    }
    public void resetCountRequestAfterOneMinute(){
        new Thread(()->{
            try{

                Thread.sleep(60000);
                countRequest.set(0);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

    }


}
