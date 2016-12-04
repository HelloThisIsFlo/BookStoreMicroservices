package com.shockn745.presentation.exception;

/**
 * @author Kempenich Florian
 */
public class ErrorInfo {

    public final String url;
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

}
