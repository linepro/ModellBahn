package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.linepro.modellbahn.util.ToStringBuilder;

import javax.ws.rs.core.Link;

@JsonAutoDetect(fieldVisibility = Visibility.PUBLIC_ONLY)
class ErrorMessage {

    private String errorCode;

    private String userMessage;

    private String developerMessage;

    private Link moreInfo;

    public ErrorMessage() {
    }

    public ErrorMessage(final String errorCode, final String userMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    @JsonGetter("errorCode")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonSetter("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonGetter("userMessage")
    public String getUserMessage() {
        return userMessage;
    }

    @JsonSetter("userMessage")
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @JsonGetter("developerMessage")
    public String getDeveloperMessage() {
        return developerMessage;
    }

    @JsonSetter("developerMessage")
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @JsonGetter("moreInfo")
    public Link getMoreInfo() {
        return moreInfo;
    }

    @JsonSetter("moreInfo")
    public void setMoreInfo(Link moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errorCode", errorCode).append("userMessage", userMessage)
                .append("developerMessage", developerMessage).append("moreInfo", moreInfo)
                .toString();
    }
}
