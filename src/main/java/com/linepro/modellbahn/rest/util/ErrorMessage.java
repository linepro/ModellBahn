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
    @ApiModelProperty(value = "The error code", example = "")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonSetter("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonGetter("userMessage")
    @ApiModelProperty(value = "The error message for user consumption", example = "")
    public String getUserMessage() {
        return userMessage;
    }

    @JsonSetter("userMessage")
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @JsonGetter("developerMessage")
    @ApiModelProperty(value = "The detailed error message for developer debugging", example = "")
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
