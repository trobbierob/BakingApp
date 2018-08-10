package services;

import java.util.List;

import model.Recipe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WebService {

    String BASE_URL = "http://d17h27t6h515a5.cloudfront.net/";
    String FEED = "topher/2017/May/59121517_baking/baking.json";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(FEED)
    Call<Recipe[]> recipes();

    @GET(FEED)
    Call<List<Recipe>> recipeList();
}
