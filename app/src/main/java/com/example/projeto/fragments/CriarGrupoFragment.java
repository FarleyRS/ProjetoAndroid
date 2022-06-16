package com.example.projeto.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentHostCallback;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projeto.R;
import com.example.projeto.recycler_view.Grupos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CriarGrupoFragment extends Fragment {
    TextView tvNomeGrupo;
    TextView tvTemaGrupo;
    TextView tvDataEntrega;
    String usuarioID;

    public CriarGrupoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_criar_grupo, container, false);

        //Instacia os editText
        tvNomeGrupo = view.findViewById(R.id.edtNomeGrupo);
        tvDataEntrega = view.findViewById(R.id.edtDataEntraga);
        tvTemaGrupo = view.findViewById(R.id.edtTemaGrupo);

        Button button = view.findViewById(R.id.btConcluir);
        button.setOnClickListener(view1 -> {
            Grupos g = new Grupos();
            g.criarGrupo(tvNomeGrupo.getText().toString(),tvTemaGrupo.getText().toString(),tvDataEntrega.getText().toString(),usuarioID);
            getActivity().finish();
        });

        //Recebe o id do usuario atual
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}