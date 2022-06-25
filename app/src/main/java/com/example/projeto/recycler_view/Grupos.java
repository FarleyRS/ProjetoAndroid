package com.example.projeto.recycler_view;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grupos {

    private static String TAG;
    private static FirebaseDatabase database;
    private static DatabaseReference databaseReference;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String NomeGrupo;
    private String TemaGrupo;
    private String id_administrador;
    private String DataEntrega;

    public Grupos(){ }

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
    public String getNomeGrupo() {
        return NomeGrupo;
    }

    public String getTemaGrupo() {
        return TemaGrupo;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public String getDataEntrega() {
        return DataEntrega;
    }

    static ArrayList<Grupos> gruposList = new ArrayList();
    // metodo para mostrar os grupos na pagina Home
    public static List<Grupos> getGrupos(){
        db.collection("Grupos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Grupos g = document.toObject(Grupos.class);
                                gruposList.add(g);
                            }
                        } else {
                            Log.d(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });
        return  gruposList;
    }
}
