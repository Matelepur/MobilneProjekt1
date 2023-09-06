package lepur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lepur.recycleview.R;

public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {

    private List<Fruit> fruits;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public AdapterListe(Context context){
        layoutInflater = LayoutInflater.from(context);
        fruits = new ArrayList<>();
    }

    public void setItemClickInterface(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public Red onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.red_liste,parent,false);

        return new Red(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Red holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitFamily.setText(fruit.getFamily());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView fruitName;
        private TextView fruitFamily;

        public Red(@NonNull View itemView){
            super(itemView);
            fruitName = itemView.findViewById(R.id.fruitName);
            fruitFamily =itemView.findViewById(R.id.fruitFamily);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickInterface==null){
                return;
            }
            itemClickInterface.onItemClick(fruits.get(getAdapterPosition()));
        }
    }

    public interface ItemClickInterface{
        void onItemClick(Fruit fruit);
    }

}
