package com.indev.ruraldevelopment.Activity.restApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
        public  static final  String BASE_URL="https://ruraldevelopment.indevconsultancy.in/api/";
        public  static final  String BASE_URL1="https://ruraldevelopment.indevconsultancy.in/uploads/committee_member/";
        public  static final  String BASE_URL2="https://ruraldevelopment.indevconsultancy.in/uploads/infrastructure/";
        public  static final  String BASE_URL3="https://ruraldevelopment.indevconsultancy.in/uploads/traning/";
        public  static final  String BASE_URL4="https://ruraldevelopment.indevconsultancy.in/uploads/resource_mapping/";
        public  static final  String BASE_URL5="https://ruraldevelopment.indevconsultancy.in/uploads/stakeholder_survey/";
        public  static final  String BASE_URL6="https://ruraldevelopment.indevconsultancy.in/uploads/village_profile/";
        public  static final  String BASE_URL7="https://ruraldevelopment.indevconsultancy.in/uploads/resource_monitor/";
        public  static final  String BASE_URL8="https://ruraldevelopment.indevconsultancy.in/uploads/infra_monitor/";

        private static Retrofit retrofit = null;
        public static Retrofit getRuralClient() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60000, TimeUnit.SECONDS)
                    .readTimeout(60000, TimeUnit.SECONDS)
                    .addInterceptor(logging).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            return retrofit;
        }
}
