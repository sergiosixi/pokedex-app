package com.ejemplo.recyclerpokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String serverUrl = "http://192.168.1.15:8090/";
    private String urlPokemons = serverUrl + "proyecto_dsw/api/service/pokemon";
    private RecyclerView recyclerViewPokemons;
    private PokemonRecyclerAdapter adapter;
    private boolean puedeCargar = false;
    private List<Pokemon> lista = new ArrayList<Pokemon>();
    private int offset = 0;
    private EditText etName;
    private String nextUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewPokemons = (RecyclerView) findViewById(R.id.rvPokemons);
        adapter = new PokemonRecyclerAdapter();
        etName = (EditText) findViewById(R.id.etName);
        findViewById(R.id.btnBuscar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offset = 0;
                adapter.clear();
                String name = etName.getText().toString();
                getObtenerPokemons(name,offset);
            }
        });

        recyclerViewPokemons.setAdapter(adapter);
        recyclerViewPokemons.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerViewPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int itemsVisibles = recyclerView.getLayoutManager().getChildCount();
                    int itemsTotales = recyclerView.getLayoutManager().getItemCount();
                    int primerItemVisible = ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (puedeCargar) {

                        if (itemsVisibles + primerItemVisible >= itemsTotales) {
                            String name = etName.getText().toString();
                            offset+=20;
                            puedeCargar = false;
                            getObtenerPokemons(name,offset);
                        }
                    }
                }
            }
        });

        getObtenerPokemons("",offset);
    }

    public void getObtenerPokemons(String name, int offset) {
        String urlBusqueda = urlPokemons+"?name="+name+"&offset="+offset;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (
                Request.Method.GET,
                urlBusqueda,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                                puedeCargar = true;
                                lista = new ArrayList<Pokemon>();
                                for(int i = 0; i<response.length();i++){
                                 try{
                                    JSONObject jsonPokemon = response.getJSONObject(i);
                                    final String url = jsonPokemon.getString("photoUrl");
                                    final String nombre = jsonPokemon.getString("name");
                                    final Pokemon nuevoPokemon = new Pokemon(nombre, url);
                                    lista.add(nuevoPokemon);
                                }catch (JSONException ex){
                                    ex.printStackTrace();
                                }
                            }

                            adapter.setAddListPokemons(lista);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


}
