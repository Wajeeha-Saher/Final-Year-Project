package com.example.dell6440.myapplication;


public class Constants {

    public static class ApiEndPoints {

        public static final String ApiUrl = "https://0a2581b2.ngrok.io/"; // This should end with /

        public static final String LoginURL = ApiUrl + "login";
        public static final String GetDriverDetails = ApiUrl + "api/driver";
        public static final String review = ApiUrl + "api/Customer/feedback";
        public static final String Place_Order = ApiUrl + "api/Orders";

    }
}
