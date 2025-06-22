package com.example.demo.dto;

public class StripeResponse {

    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;

    StripeResponse(Builder builder){
        this.status = builder.status;
        this.message = builder.message;
        this.sessionId = builder.sessionId;
        this.sessionUrl = builder.sessionUrl;
    }


    public String getStatus() {return status;}
    public String getMessage() {return message;}
    public String getSessionId() {return sessionId;}
    public String getSessionUrl() {return sessionUrl;}

    public static class Builder {
        private String status;
        private String message;
        private String sessionId;
        private String sessionUrl;

        public Builder(){}

        public Builder status(String status){
            this.status = status;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder sessionId(String sessionId){
            this.sessionId = sessionId;
            return this;
        }

        public Builder sessionUrl(String sessionUrl){
            this.sessionUrl = sessionUrl;
            return this;
        }

        public StripeResponse build(){
            return new StripeResponse(this);
        }
    }
}
