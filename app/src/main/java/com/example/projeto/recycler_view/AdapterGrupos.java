package com.example.projeto.recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.R;

import java.util.List;

public class AdapterGrupos  extends RecyclerView.Adapter<AdapterGrupos.GruposViewHolder> {
    protected static final String TAG = "if-android";
    private final List<Grupos> grupos;
    private final Context context;
    private final GruposOnClickListener onClickListener;

    public interface GruposOnClickListener{
        void onClickGrupos(GruposViewHolder holder, int idx);
    }
    public AdapterGrupos(Context context, List<Grupos> grupos,GruposOnClickListener onClickListener){
        this.context = context;
        this.grupos = grupos;
        this.onClickListener = onClickListener;
    }
    @Override
    public GruposViewHolder onCreateViewHolder(ViewGroup viewGrup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.andapter_grupos, viewGrup,false);
        GruposViewHolder holder = new GruposViewHolder(view);
        return holder;
    }
    @Override
    public int getItemCount() {
        return this.grupos != null ? this.grupos.size() : 0;
    }
    @Override
    public void onBindViewHolder(@NonNull final GruposViewHolder holder, final int position) {

        Grupos g = grupos.get(position);
        holder.tNome.setText(g.getNome());

        if(onClickListener != null){
            holder.view.setOnClickListener(view ->
                    onClickListener.onClickGrupos(holder,position));
        }

    }
    public static class GruposViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        private View view;
        public GruposViewHolder(View view) {
            super(view);
            this.view = view;
            tNome = (TextView) view.findViewById(R.id.card_nome_grup);
        }
    }
}

