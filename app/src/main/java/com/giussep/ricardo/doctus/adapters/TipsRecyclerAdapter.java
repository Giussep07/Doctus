package com.giussep.ricardo.doctus.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giussep.ricardo.doctus.R;
import com.giussep.ricardo.doctus.models.Tip;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsRecyclerAdapter extends RecyclerView.Adapter<TipsRecyclerAdapter.ViewHolder> {

    private List<Tip> tipList;
    private OnTipsRecyclerAdapter mListener;

    public interface OnTipsRecyclerAdapter {
        void onEditarPressed(Tip tip);

        void onEliminarPressed(Tip tip, int position);
    }

    public TipsRecyclerAdapter(List<Tip> tipList, OnTipsRecyclerAdapter mListener) {
        this.tipList = tipList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tip tip = tipList.get(position);

        holder.textViewTitulo.setText(tip.getTitulo());
        holder.textViewFecha.setText(tip.getFecha());
        holder.textViewDescripcion.setText(tip.getDescripcion());

        holder.buttonEditar.setOnClickListener(v -> mListener.onEditarPressed(tip));

        holder.buttonEliminar.setOnClickListener(v -> mListener.onEliminarPressed(tip, position));
    }

    @Override
    public int getItemCount() {
        return tipList != null ? tipList.size() : 0;
    }

    public void removeTip(int position) {
        tipList.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        MaterialCardView cardView;
        @BindView(R.id.textView_titulo)
        AppCompatTextView textViewTitulo;
        @BindView(R.id.textView_fecha)
        AppCompatTextView textViewFecha;
        @BindView(R.id.textView_descripcion)
        AppCompatTextView textViewDescripcion;
        @BindView(R.id.button_editar)
        MaterialButton buttonEditar;
        @BindView(R.id.button_eliminar)
        MaterialButton buttonEliminar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
