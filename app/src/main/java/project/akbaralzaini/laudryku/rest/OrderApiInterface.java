package project.akbaralzaini.laudryku.rest;

import java.util.List;

import project.akbaralzaini.laudryku.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface OrderApiInterface {

    @GET("Order/index")
    Call<List<Order>> getOrder();
}
