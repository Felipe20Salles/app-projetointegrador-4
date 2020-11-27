package com.example.projetointegrador.retrofit;

import com.example.projetointegrador.classes.Post;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostService {

    String  API_ROUTE  =  "usuario" ;
    @Headers({

            "Content-type: application/json"

    })
    @POST( API_ROUTE )
    Call<Post> sendPosts (@Body Post posts );
}
