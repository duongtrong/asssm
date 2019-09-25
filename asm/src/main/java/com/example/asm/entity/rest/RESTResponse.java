package com.example.asm.entity.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Tạo dữ liệu trả về cho client qua api, đáp ứng một số yêu cầu từ mobile.
 * Không chứa http status code vì phải là tham số trong spring ResponseEntity.
 * */
public class RESTResponse {

    private HashMap<String, Object> response;

    // MUST be private.
    private RESTResponse() {
        this.response = new HashMap<>();
    }

    public HashMap<String, Object> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, Object> response) {
        this.response = response;
    }

    public void addResponse(String key, Object value) {
        this.response.put(key, value);
    }

    public static class Error {

        private HashMap<String, String> errors;
        private int status;
        private String message;

        public Error() {
            this.errors = new HashMap<>();
            this.status = 0;
            this.message = "";
        }

        public Error setStatus(int status) {
            this.status = status;
            return this;
        }

        public Error setMessage(String message) {
            this.message = message;
            return this;
        }

        public Error addError(String key, String value) {
            this.errors.put(key, value);
            return this;
        }

        public Error addErrors(HashMap<String, String> errors) {
            this.errors.putAll(errors);
            return this;
        }

        public HashMap<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            String errorKey = "error";
            if (this.errors.size() > 1) {
                errorKey = "errors";
            }
            restResponse.addResponse(errorKey, this.errors);
            return restResponse.getResponse();
        }
    }

    public static class SimpleError {

        private int code;
        private String message;

        public SimpleError() {
            this.code = 0;
            this.message = "";
        }

        public SimpleError setCode(int code) {
            this.code = code;
            return this;
        }

        public SimpleError setMessage(String message) {
            this.message = message;
            return this;
        }

        public HashMap<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.code);
            restResponse.addResponse("message", this.message);
            return restResponse.getResponse();
        }
    }


    public static class Success {

        private int status;
        private String message;
        private List<Object> datas;
        private RESTPagination pagination;

        public Success() {
            this.status = 1;
            this.message = "Thành công";
            this.datas = new ArrayList<>();
        }

        public Success setStatus(int status) {
            this.status = status;
            return this;
        }

        public Success setMessage(String message) {
            this.message = message;
            return this;
        }

        public Success setPagination(RESTPagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public Success addData(Object obj) {
            this.datas.add(obj);
            return this;
        }

        public Success addDatas(List listObj) {
            this.datas.addAll(listObj);
            return this;
        }

        public HashMap<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            if (this.datas.size() == 1) {
                restResponse.addResponse("data", this.datas.get(0));

            } else {
                restResponse.addResponse("datas", this.datas);
            }
            if (this.pagination != null) {
                restResponse.addResponse("pagination", this.pagination);
            }
            return restResponse.getResponse();
        }

        public HashMap<String, Object> buildDatas() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            restResponse.addResponse("datas", this.datas);
            if (this.pagination != null) {
                restResponse.addResponse("pagination", this.pagination);
            }
            return restResponse.getResponse();
        }
    }
}
