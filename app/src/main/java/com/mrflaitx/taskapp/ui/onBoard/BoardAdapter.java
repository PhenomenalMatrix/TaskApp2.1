package com.mrflaitx.taskapp.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrflaitx.taskapp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] list = {"its 1 screen","its 2 screen","its 3 screen"};
    private int[] listImg = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background};

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.boardIv);
            title = itemView.findViewById(R.id.boardFirstTv);
            description = itemView.findViewById(R.id.boardSecondTv);
        }

        public void onBind(int position) {
            title.setText(list[position]);
            imageView.setImageResource(listImg[position]);
        }
    }
}
