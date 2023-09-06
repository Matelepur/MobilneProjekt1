package lepur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lepur.recycleview.R;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickInterface {
    private RecyclerView recyclerView;
    private AdapterListe adapterListe;
    private static final String ALL_FRUITS_ROUTE = "/fruit/all";
    private static final String SPECIFIC_FRUIT_ROUTE = "/fruit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poveziKomponente();
        dohvatiPodatke();
    }

    private void dohvatiPodatke() {
        adapterListe = new AdapterListe(this);
        adapterListe.setItemClickInterface(this);
        recyclerView.setAdapter(adapterListe);
        dohvatiVoce();
    }

    private void dohvatiVoce() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getString(R.string.REST_API) + ALL_FRUITS_ROUTE);
                    HttpURLConnection httpURLConnection =
                            (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(15000);
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.connect();
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    Fruit[] odgovor = new Gson().fromJson(bufferedReader,Fruit[].class);

                    bufferedReader.close();
                    inputStreamReader.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapterListe.setFruits(Arrays.asList(odgovor));
                            adapterListe.notifyDataSetChanged();
                        }
                    });


                }catch(Exception e){
                    Log.d("REST exception",e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void poveziKomponente() {
        recyclerView=findViewById(R.id.rvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(Fruit fruit) {
        Intent i = new Intent(this,Detalji.class);
        i.putExtra("fruit", fruit);
        startActivity(i);
    }
}