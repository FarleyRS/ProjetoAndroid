package com.example.projeto.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projeto.R;
import com.example.projeto.recycler_view.AdapterGrupos;
import com.example.projeto.recycler_view.Grupos;

import java.util.List;

public class HomeFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected List<Grupos> grupos;
    protected AdapterGrupos adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        grupos = Grupos.getGrupos();
        recyclerView.setAdapter(adapter = new AdapterGrupos(getActivity(), grupos, onClickGrupos()));
        return view;
    }

    protected AdapterGrupos.GruposOnClickListener onClickGrupos(){
        return  new AdapterGrupos.GruposOnClickListener() {
            @Override
            public void onClickGrupos(AdapterGrupos.GruposViewHolder holder, int idx) { //quando clicar no card view
                List<Grupos> grupos = Grupos.getGrupos();
                Grupos g = grupos.get(idx);
                Toast.makeText(getActivity(), "Grupo "+g.getNomeGrupo(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}