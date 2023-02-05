package com.example.sayehwebservices.services;
import java.io.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service


public class ResultInquery {
   public String connectWithNetToMagfaServerAndRequestSmsSend(String nationalCode) throws IOException {
       OkHttpClient client = new OkHttpClient().newBuilder()
               .build();
       MediaType mediaType = MediaType.parse("application/json");
       RequestBody body = RequestBody.create(
               mediaType,
               "{\r\n\"senders\":" +
                       " [\"300097500059\"],\r\n\"messages\":" +
                       "[\"TEST MESSAGE 856\"],\r\n\"recipients\"" +
                       ":[\""+nationalCode+"\"]\r\n}");
       Request request = new Request.Builder()
               .url("https://sms.magfa.com/api/http/sms/v2/send")
               .method("POST", body)
               .addHeader("Authorization", "Basic dGFuemltXzAwMDk1L21jbHNfaXRlOkM2b1ppQUdQSlFkQ0Q1TlU=")
               .addHeader("Content-Type", "application/json")
               .build();
       Response response = client.newCall(request).execute();
//       System.out.println(response.body().string());
       return response.body().string();
   }

}

