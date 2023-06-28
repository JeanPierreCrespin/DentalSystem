package com.dental.system.exception;

import java.sql.Timestamp;

public class GlobalResponse <T>{
    public String path;
    public String timestamp;
    public T body;
    public Integer lenght;
    public String error;
}
