package lepur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lepur.recycleview.R;

public class Detalji extends AppCompatActivity {
    private TextView genusLabel;
    private TextView nameLabel;
    private TextView familyLabel;
    private TextView order;
    private TextView carbohydrates;
    private TextView protein;
    private TextView fat;
    private TextView calories;
    private TextView sugar;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        poveziKomponente();
        definirajDogadaje();
    }

    @SuppressLint("SetTextI18n")
    private void definirajDogadaje() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent i = getIntent();
        Fruit o = (Fruit) i.getSerializableExtra("fruit");
        genusLabel.setText(o.getGenus());
        nameLabel.setText(o.getName());
        familyLabel.setText(o.getFamily());
        order.setText(o.getOrder());
        carbohydrates.setText(Float.toString(o.getNutritions().getCarbohydrates()));
        protein.setText(Float.toString(o.getNutritions().getProtein()));
        fat.setText(Float.toString(o.getNutritions().getFat()));
        calories.setText(Float.toString(o.getNutritions().getCalories()));
        sugar.setText(Float.toString(o.getNutritions().getSugar()));
    }

    private void poveziKomponente() {
        genusLabel = findViewById(R.id.detailsFruitGenus);
        nameLabel = findViewById(R.id.detailsFruitName);
        familyLabel = findViewById(R.id.detailsFruitFamily);
        order = findViewById(R.id.detailsFruitOrder);
        carbohydrates = findViewById(R.id.detailsFruitCarbo);
        protein = findViewById(R.id.detailsFruitProtein);
        fat = findViewById(R.id.detailsFruitFat);
        calories = findViewById(R.id.detailsFruitCalories);
        sugar = findViewById(R.id.detailsFruitSugar);
        backButton = findViewById(R.id.btnNazad);
    }
}