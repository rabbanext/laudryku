package project.akbaralzaini.laudryku.rest;

import java.util.List;

import project.akbaralzaini.laudryku.model.JenisBarang;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JenisBarangApiInterface {

    @GET("JenisBarang/index/{id_laundry}")
    Call<List<JenisBarang>> getJenisBarang(@Path("id_laundry") String id_laundry);

    @POST("JenisBarang/create/{id_laundry}")
    Call<JenisBarang> postJenisBarang(@Body JenisBarang jenisBarang,@Path("id_laundry") int id_laundry);
}
