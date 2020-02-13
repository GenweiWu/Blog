package com.njust.test.rawtype;

public class MyResponse<T>
{
    private T body;
    
    public T getBody()
    {
        return body;
    }
    
    public void setBody(T body)
    {
        this.body = body;
    }
    
    @Override
    public String toString()
    {
        return "MyResponse{" +
            "body=" + body +
            '}';
    }
}
