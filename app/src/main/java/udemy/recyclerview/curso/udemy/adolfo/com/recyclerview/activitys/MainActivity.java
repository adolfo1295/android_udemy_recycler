package udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.R;
import udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.adapters.AmigoAdapter;
import udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.models.Amigo;

public class MainActivity extends AppCompatActivity {

    private AmigoAdapter mAdapter;
    private List<Amigo> mAmigoList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
        //addAmigo();
        addAmigos(5);
    }

    private void initRecycler(){
        //se crea una lista vacia
        mAmigoList = new ArrayList<>();
        //se declara el recycler view para poder trabajar con el
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //tambien un layoutmanager para especificar que usaremos un linear layout, con rientacion vertical en le recycler view
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //lepaamos el layout manager al recycler
        mRecyclerView.setLayoutManager(mLayoutManager);
        //esto permite al añadir un item, el recycler se rediriga hacia el primer item
        mRecyclerView.setHasFixedSize(true);
        //se añade  un item animador
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //esta es una de las parter mas importantes, donde se manda a llamar el adapter que creamos anteriormente
        //le pasamos de parametros (lista,layout,listener), que son aprametros que ya tenemos preparados
        //solo lo mandamos a llamar, llenamos los aprametros y listo! ya tenemos un adapter creado, pero no seteado.
        mAdapter = new AmigoAdapter(mAmigoList, R.layout.card_item, new AmigoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Amigo amigo, int pos) {
                Toast.makeText(MainActivity.this, "Presionaste el item: "+pos+"\nNombre: "+amigo.getNombre(), Toast.LENGTH_SHORT).show();
                changeAmigo(pos);
            }

            @Override
            public void onItemLongClck(Amigo amigo, int pos) {
                deleteAmigo(pos);
                Toast.makeText(MainActivity.this, "bay bay amigo "+amigo.getNombre()+" :(", Toast.LENGTH_SHORT).show();
            }
        });
        //aqui se setea el adapter al ecycler view
        mRecyclerView.setAdapter(mAdapter);
    }

    //añadiendo datos a la lista
    private void addAmigo(){
        Amigo amigo = new Amigo("Adolfin","Chavez",22);
        mAmigoList.add(amigo);
        mAdapter.notifyDataSetChanged();
    }

    //añadiendo mas amigos
    private void addAmigos(int numAmigos){
        for (int i = 1;i<=numAmigos;i++){
            mAmigoList.add(new Amigo("nombre"+i,"apellido"+i,22+i));
            mAdapter.notifyDataSetChanged();
        }
    }

    //para eliminar a un amigo :(
    private void deleteAmigo(int pos){
        mAmigoList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    //para cambiar datos
    private void changeAmigo(int pos){
        mAmigoList.get(pos).setNombre("Nombre perron");
        mAdapter.notifyDataSetChanged();
    }

    //eliminar todos los amigos :(
    public void deleteAll(View view) {
        mAmigoList.clear();
        mAdapter.notifyDataSetChanged();
    }
}
