package project.akbaralzaini.laudryku.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.model.Order;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder>{
    List<Order> orderList;

    public OrderListAdapter(List<Order> orderList){
        this.orderList = orderList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order, parent, false);
        OrderListAdapter.MyViewHolder mViewHolder = new OrderListAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, int position) {

        holder.tvNamaPemesan.setText(orderList.get(position).getNama_pemesan());
        holder.tvTanggalOrder.setText(orderList.get(position).getTanggal_selesai());
        holder.tvTotalOrder.setText("Rp. "+orderList.get(position).getTotal_bayar());
        switch (orderList.get(position).getStatus()){
            case "dilaundry":
                holder.ivStatusOrder.setImageResource(R.drawable.waiting);
                break;
            case "selesai":
                holder.ivStatusOrder.setImageResource(R.drawable.delay);
                break;
            default:
                holder.ivStatusOrder.setImageResource(R.drawable.done);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent detailOrder =  new Intent()
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPemesan,tvTanggalOrder,tvTotalOrder;
        ImageView ivIconOrder,ivStatusOrder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPemesan = itemView.findViewById(R.id.nama_pemesan);
            tvTanggalOrder = itemView.findViewById(R.id.tanggal_order);
            tvTotalOrder = itemView.findViewById(R.id.total_order);
            ivIconOrder = itemView.findViewById(R.id.foto_order);
            ivStatusOrder = itemView.findViewById(R.id.status_order);
        }
    }
}
