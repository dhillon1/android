package com.sd.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private EditText inputIp;
    private Button searchIp, myIpSearch;
    private String info;
    private TextView ip,city,region,country,loc,timezone,demoInfo;
    private ConstraintLayout constraintLayoutDemo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputIp = findViewById(R.id.mainEditText);
        searchIp = findViewById(R.id.mainSearch);
        myIpSearch = findViewById(R.id.mainMyInfo);
        constraintLayoutDemo = findViewById(R.id.constraitLayoutDemo);
        ip = findViewById(R.id.demoIp);
        city = findViewById(R.id.demoCity);
        region = findViewById(R.id.demoRegion);
        country = findViewById(R.id.demoCountry);
        loc = findViewById(R.id.demoLocation);
        timezone = findViewById(R.id.demoTimezone);
        demoInfo = findViewById(R.id.demoInfo);
        constraintLayoutDemo.setVisibility(GONE);
        demoInfo.setVisibility(GONE);


        searchIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayoutDemo.setVisibility(GONE);
                demoInfo.setVisibility(GONE);
                info = inputIp.getText().toString().trim();

                if(info.isEmpty()){
                    inputIp.setError("Enter valid  IP address");
                    inputIp.requestFocus();
                    return;
                }

                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObject4 = new JsonObjectRequest(Request.Method.GET,"https://ipinfo.io/"+info+"?token=9ffaf2ee5c712a", null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response4) {


                                try {
                                    constraintLayoutDemo.setVisibility(View.VISIBLE);
                                    demoInfo.setVisibility(GONE);
                                    ip.setText(response4.getString("ip"));
                                    city.setText(response4.getString("city"));
                                    region.setText(response4.getString("region"));
                                    country.setText(response4.getString("country"));
                                    loc.setText(response4.getString("loc"));
                                    timezone.setText(response4.getString("timezone"));

                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                demoInfo.setVisibility(View.VISIBLE);
                                constraintLayoutDemo.setVisibility(GONE);
                                demoInfo.setText("Internet not connected or IP not formatted properly. The syntax of IP should be x.x.x.x");
                            }
                        }
                );
                queue.add(jsonObject4);




            }
        });

        myIpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayoutDemo.setVisibility(GONE);
                demoInfo.setVisibility(GONE);
                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url ="https://api.ipify.org?format=json";


                JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    final String name = response.getString("ip");
                                    JsonObjectRequest jsonObject4 = new JsonObjectRequest(Request.Method.GET,"https://ipinfo.io/"+name+"?token=9ffaf2ee5c712a", null,
                                            new Response.Listener<JSONObject>()
                                            {
                                                @Override
                                                public void onResponse(JSONObject response4) {


                                                    try {
                                                        constraintLayoutDemo.setVisibility(View.VISIBLE);
                                                        ip.setText(response4.getString("ip"));
                                                        city.setText(response4.getString("city"));
                                                        region.setText(response4.getString("region"));
                                                        country.setText(response4.getString("country"));
                                                        loc.setText(response4.getString("loc"));
                                                        timezone.setText(response4.getString("timezone"));


                                                    } catch (JSONException e) {
                                                        Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            },
                                            new Response.ErrorListener()
                                            {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(MainActivity.this, "1",Toast.LENGTH_SHORT).show();
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
                                demoInfo.setVisibility(View.VISIBLE);
                                constraintLayoutDemo.setVisibility(GONE);
                                demoInfo.setText("Internet not connected");
                            }
                        }
                );

                queue.add(jsonObject);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


}
