package project.akbaralzaini.laudryku.userInterface;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.adapter.OrderListAdapter;
import project.akbaralzaini.laudryku.model.Laundry;
import project.akbaralzaini.laudryku.model.Order;
import project.akbaralzaini.laudryku.rest.ApiClient;
import project.akbaralzaini.laudryku.rest.OrderApiInterface;
import project.akbaralzaini.laudryku.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvOrder;
    private RecyclerView.Adapter mAdapter;
    private OrderApiInterface orderApiInterface;
    TextView nama_laundry;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        rvOrder = rootView.findViewById(R.id.list_recent);
        SharedPrefManager sharedPrefManager = new SharedPrefManager(Objects.requireNonNull(getContext()));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvOrder.setLayoutManager(mLayoutManager);
        orderApiInterface = ApiClient.getClient().create(OrderApiInterface.class);

        //deklarasi isi
        nama_laundry = rootView.findViewById(R.id.nama_laundry);
        Laundry l = sharedPrefManager.getLaundry();
        nama_laundry.setText(l.getNama_laundry());

        refresh();
        return rootView;
    }


    private void refresh() {
        Call<List<Order>> listCall = orderApiInterface.getOrder();
        listCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> dataListOrder = response.body();
                mAdapter = new OrderListAdapter(dataListOrder);
                rvOrder.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }


}
