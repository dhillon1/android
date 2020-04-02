package com.sd.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText inputIp;
    private Button searchIp, myIpSearch;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputIp = findViewById(R.id.mainEditText);
        searchIp = findViewById(R.id.mainSearch);
        myIpSearch = findViewById(R.id.mainMyInfo);

        searchIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = inputIp.getText().toString().trim();

                if(info.isEmpty()){
                    inputIp.setError("Enter valid  IP address");
                    inputIp.requestFocus();
                    return;
                }

                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url ="https://api.ipify.org?format=json";


                JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String ip = response.getString("ip");
                                    JsonObjectRequest jsonObject4 = new JsonObjectRequest(Request.Method.GET,"https://ipinfo.io/"+ip+"?token=9ffaf2ee5c712a", null,
                                            new Response.Listener<JSONObject>()
                                            {
                                                @Override
                                                public void onResponse(JSONObject response) {

                                                    Log.d("Response", "done");
                                                }
                                            },
                                            new Response.ErrorListener()
                                            {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(MainActivity.this, error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    );
                                    queue.add(jsonObject4);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                queue.add(jsonObject);


            }
        });

        myIpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}
