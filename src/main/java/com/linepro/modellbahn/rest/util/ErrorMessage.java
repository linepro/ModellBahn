package com.linepro.modellbahn.rest.util;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.linepro.modellbahn.util.ToStringBuilder;

import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect(fieldVisibility = Visibility.PUBLIC_ONLY)
class ErrorMessage {

    private int errorCode;

    private String userMessage;

    private String developerMessage;

    private Link moreInfo;

    public ErrorMessage() {
    }

    public ErrorMessage(final int errorCode, final String userMessage) {
        this(errorCode, userMessage, null);
    }

    public ErrorMessage(final int errorCode, final String userMessage, final String developerMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    @JsonGetter("errorCode")
    @ApiModelProperty(value = "Error code", example = "500")
    public int getErrorCode() {
        return errorCode;
    }

    @JsonSetter("errorCode")
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonGetter("userMessage")
    @ApiModelProperty(value = "Error message for user consumption", example = "Internal Server Error")
    public String getUserMessage() {
        return userMessage;
    }

    @JsonSetter("userMessage")
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @JsonGetter("developerMessage")
    @ApiModelProperty(value = "Detailed error message for developer debugging", example = "NullPointerException")
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
                .append("errorCode", errorCode)
                .append("userMessage", userMessage)
                .append("developerMessage", developerMessage)
                .append("moreInfo", moreInfo)
                .toString();
    }
}
