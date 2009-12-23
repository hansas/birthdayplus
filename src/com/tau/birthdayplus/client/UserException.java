package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserException extends Throwable
implements IsSerializable{
	
	String message;

    public UserException(Exception e)
    {
        message = e.getMessage();
        
    }

    private  UserException()
    {
    }

    public UserException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
