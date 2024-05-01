package com.berkhayta.springbootblogrestapi.exceptions;

import lombok.Getter;


@Getter


public class BlogAppException extends RuntimeException
{
    private ErrorType errorType;

    public BlogAppException(ErrorType errorType)
    {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BlogAppException(ErrorType errorType, String customMessage)
    {
        super(customMessage);
        this.errorType = errorType;
    }
}
