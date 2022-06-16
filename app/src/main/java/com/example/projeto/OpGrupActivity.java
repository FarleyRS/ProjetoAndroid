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

import java.util.function.BiConsumer;

public class OpGrupActivity extends AppCompatActivity {

    CriarGrupoFragment cgf = new CriarGrupoFragment();
    EntrarGrupoFragment etg = new EntrarGrupoFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_grup);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container2, cgf);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickCriar(View view){
        openFragment(cgf);
    }
    public void onClickEntrar(View view){
        openFragment(etg);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}