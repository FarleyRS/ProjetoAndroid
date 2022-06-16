package com.example.projeto.recycler_view;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grupos {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String nome;
    private String tema;
    private String id_administrador;
    private Date data_de_entrega;

    public Grupos(){ }

    public Grupos(String nome){
        this.nome = nome;
    }

    public Grupos(String nome, String tema, Date data_de_entrega, String id_administrador) {
        this.nome = nome;
        this.tema = tema;
        this.data_de_entrega = data_de_entrega;
        this.id_administrador = id_administrador;
    }

    public void criarGrupo(String nome, String tema, String data_de_entrega, String id_administrador){
        Map<String, Object> dados = new HashMap<>();
        dados.put("NomeGrupo",nome);
        dados.put("TemaGrupo",tema);
        dados.put("DataEntrega",data_de_entrega);
        dados.put("id_administrador", id_administrador);

        db.collection("Grupos")
                .add(dados)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public String getNome() {
        return nome;
    }

    public String getTema() {
        return tema;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public Date getData_de_entrega() {
        return data_de_entrega;
    }

    // metodo para mostrar os grupos na pagina Home
    public static List<Grupos> getGrupos(){
        List<Grupos> gruposList = new ArrayList<>();
        gruposList.add(new Grupos("Desenvolvimento WEB"));
        gruposList.add(new Grupos("Desenvolvimento Android"));
        gruposList.add(new Grupos("Topicos Especiais"));
        return gruposList;
    }
}
