package project.akbaralzaini.laudryku.rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import project.akbaralzaini.laudryku.model.Laundry;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface LaundryApiInterface {

    @GET("Laundry/getOne/{id_user}")
    Call<Laundry> getOne(@Path("id_user") String id_user);


    @Multipart
    @POST("Laundry/create")
    Call<Laundry> createLaundry(@Part MultipartBody.Part foto,
                                @Part("id_user") RequestBody id_user,
                                @Part("nama_laundry") RequestBody nama_laundry,
                                @Part("nama_pemilik") RequestBody nama_pemilik,
                                @Part("alamat") RequestBody alamat,
                                @Part("tlp") RequestBody tlp);
}
