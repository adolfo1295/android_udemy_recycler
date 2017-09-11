package udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.R;
import udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.models.Amigo;

/**
 * Created by Adolfo Chavez on 07/09/2017.
 */

public class AmigoAdapter extends RecyclerView.Adapter<AmigoAdapter.ViewHolder>{

    //se declara la lista con parametro un objeto amigo
    private List<Amigo> mAmigoList;
    //tambien se declara la interface que creamos abajo, esta para usarla como cliclkisteners
    private OnItemClickListener mOnItemClickListener;
    private int layout;

    public AmigoAdapter(List<Amigo> amigoList, int layout, OnItemClickListener onItemClickListener) {
        mAmigoList = amigoList;
        this.layout = layout;
        mOnItemClickListener = onItemClickListener;
    }

    //se crea primero esta clase ViewHolder, que extienda de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        //se declaran los componentes que estan dentro del card_item
        public TextView tvNombre, tvApellido, tvEdad;

        //este metodo en espesifico nos permitira declararlos y poder tilizarlos al momento de bindearlos
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            this.tvApellido = (TextView) itemView.findViewById(R.id.tvApellido);
            this.tvEdad = (TextView) itemView.findViewById(R.id.tvEdad);
        }

        //metodo para hacer el bindeo de los datos, le pasamos de parametros
        //un objeto amigo, y un itemclicklistener, finales para que puedan ser utlizados dentro del metodo
        public void bind(final Amigo amigo, final OnItemClickListener onItemClickListener){
            //vemos que usamos el objeto para llenar los componentes del ui, por este motivo declaramos como parametro amigo
            tvNombre.setText(amigo.getNombre());
            tvApellido.setText(amigo.getApellido());
            tvEdad.setText(String.valueOf(amigo.getEdad()));
            //ya declarados los componentes, lo que sigue es hacer los listeners, que se hacen de la siguiente manera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //aqui mandamos a llamar el listener que declaramos final en lso aprametros del metodo, asi para poder llamar
                    //la iterface que creamos anteriormente, le pasamos los aprametros que nos pide (Amigo amigo, int position)
                    onItemClickListener.onItemClick(amigo,getAdapterPosition());
                }
            });
            //aqui es practicamente lo mismo que lo anterior soloq ue sera cn un longClick
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClck(amigo,getAdapterPosition());
                    return true;
                }
            });
        }

    }

    //aqui es donde se crea y se regresa el viewholder
    @Override
    public AmigoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder mViewHolder = new ViewHolder(v);
        return mViewHolder;
    }

    //aqui se utiliza el parametro bind que creamos desde el ViewHolder, puedes presionar
    //control+b en "bind" y redirigirte a donde se creo el metodo
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mAmigoList.get(position),mOnItemClickListener);
    }

    //obtiene el tamaño de la lista
    @Override
    public int getItemCount() {
        return mAmigoList.size();
    }

    //se crea la interface que nos permitira jugar con los listeners
   public interface OnItemClickListener{
       void onItemClick(Amigo amigo, int pos);
       void onItemLongClck(Amigo amigo, int pos);
   }

}
