package com.dental.system.exception;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class GlobalResponseService {

    private String TIMEZONE = "America/Argentina/Buenos_aires";

    public SimpleDateFormat simpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
        return sdf;
    }

    public  String getDateString() {
        return simpleDateFormat().format(new Date());
    }
    public  GlobalResponse badRequest(String stringError){
        GlobalResponse response = new GlobalResponse();
        try{
            response.body = null;
            response.path =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest().getRequestURI();
            response.error = stringError;
            if(stringError == null || stringError.equals("")){
                response.error = "Error desconocido.";
            }
            response.timestamp = getDateString();
            return response;
        }catch (IllegalStateException e){
            throw  new RuntimeException(e);
        } finally {
            response = new GlobalResponse();
        }

    }
}
