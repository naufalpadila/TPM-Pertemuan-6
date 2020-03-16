package com.example.testapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapi.database.DataDiri;

public class DataDiriAdapater extends RecyclerView.Adapter<DataDiriAdapater.DataDiriViewHolder> {
    private DataDiri[] list;
    private Context context;

    public DataDiriAdapater(DataDiri[] list,Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DataDiriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datadiri, parent, false);
        return new DataDiriViewHolder(view);
    }

    @Override
    // Untuk nampilin di RecyclerView
    public void onBindViewHolder(@NonNull DataDiriViewHolder holder, int position) {
        final DataDiri item = list[position];

        holder.tvNama.setText(item.getNama());
        holder.tvAlamat.setText(item.getAlamat());
        holder.tvKelamin.setText(""+item.getJkelamin());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getNama());
                intent.putExtra("address", item.getAlamat());
                intent.putExtra("gender", item.getJkelamin());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class DataDiriViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvAlamat, tvKelamin;
        View itemView;
        LinearLayout itemLayout;

        public DataDiriViewHolder(@NonNull View itemView){
            super((itemView));

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvKelamin = itemView.findViewById(R.id.tv_kelamin);
            itemLayout = itemView.findViewById(R.id.item_layout);

            this.itemView = itemView;
        }
    }
}
