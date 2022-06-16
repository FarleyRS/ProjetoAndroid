package com.example.projeto.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistreActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registre;
    private EditText email;
    private EditText senha;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        mAuth = FirebaseAuth.getInstance();
        registre = findViewById(R.id.btRegistrar);

        setRegistre(registre);

    }

    public void setRegistre(Button registre) {
        email = (EditText) findViewById(R.id.edtCreatEmailAddress);
        senha = (EditText) findViewById(R.id.edtCreatPassword);
        registre.setOnClickListener(view -> {
            if (email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
            else {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).
                        addOnCompleteListener(RegistreActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistreActivity.this, "Criado com sucesso!", Toast.LENGTH_SHORT).show();

                                    salvarDadosUser();

                                    startActivity(new Intent(RegistreActivity.this, LoginActivity.class));
                                } else {
                                    String error;
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        error = "Senha com no minimo 6 digitos";
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        error = "Usuario já existe";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        error = "Email errado";
                                    } catch (Exception e) {
                                        error = "Erro ao cadastrar user";
                                    }
                                    Toast.makeText(RegistreActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    public void salvarDadosUser(){
        Map<String, Object> data = new HashMap<>();
        data.put("Nome", email.getText().toString());
        data.put("Email", email.getText().toString());
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("Usuarios").document(usuarioID)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegistreActivity.this, "Cradastrado no banco", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistreActivity.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}