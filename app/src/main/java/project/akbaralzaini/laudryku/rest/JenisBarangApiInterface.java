package project.akbaralzaini.laudryku.rest;

import java.util.List;

import project.akbaralzaini.laudryku.model.JenisBarang;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JenisBarangApiInterface {

    @GET("JenisBarang/index/{id_laundry}")
    Call<List<JenisBarang>> getJenisBarang(@Path("id_laundry") String id_laundry);
}
