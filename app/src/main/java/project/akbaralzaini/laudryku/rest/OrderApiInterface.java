package project.akbaralzaini.laudryku.rest;

import java.util.List;

import project.akbaralzaini.laudryku.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderApiInterface {

    @GET("Order/index")
    Call<List<Order>> getOrder();

    @POST("Order/create")
    Call<Order> createOrder(@Body Order order);


}
