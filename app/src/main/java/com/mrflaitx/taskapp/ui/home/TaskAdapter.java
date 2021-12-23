package com.mrflaitx.taskapp.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrflaitx.taskapp.databinding.ItemRvBinding;
import com.mrflaitx.taskapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<User> list = new ArrayList<>();
    private ItemRvBinding binding;
    private OnItemClick listener;

    public void setList(List<User> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    // Инициализация интерфейса
    public void setListener(OnItemClick listener){
        this.listener = listener;
    }

    public void removeItem(int position){
        Log.e("TAG", "removeItem: "+ list.get(position));
        list.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(v ->{
                listener.onLongClick(getAdapterPosition());
                return true;
            });

        }

        public void onBind(User user) {
            binding.nameTv.setText(user.getName());
            binding.surNameTv.setText(user.getSurname());

            itemView.setOnClickListener(v -> {
                listener.onClick(user.getName());
            });
        }
    }

    interface OnItemClick{
        void onClick(String txt);
        void onLongClick(int position);
    }
}
