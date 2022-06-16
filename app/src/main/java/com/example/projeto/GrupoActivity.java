package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.projeto.fragments.CriarGrupoFragment;
import com.example.projeto.fragments.EntrarGrupoFragment;

public class GrupoActivity extends AppCompatActivity {

    CriarGrupoFragment cgf = new CriarGrupoFragment();
    EntrarGrupoFragment etg = new EntrarGrupoFragment();
    Button criarGrup = findViewById(R.id.btCriarGrupo);
    Button entraGrup = findViewById(R.id.btEntrarGrupo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

       Toolbar myToolbar = findViewById(R.id.my_toolbar);
       setSupportActionBar(myToolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container2, cgf);
        transaction.commit();

        opButtom();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void opButtom(){
        if(criarGrup.isPressed()){
            criarGrup.setOnClickListener(view -> {
                openFragment(cgf);
            });
        }else if(entraGrup.isPressed()){
            entraGrup.setOnClickListener(view -> {
                openFragment(etg);
            });
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}