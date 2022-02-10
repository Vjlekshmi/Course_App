package com.example.courseappactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getcoursetitle,getdescription,getduration,getvenue,getdate;
    String apiurl="https://mountzioncollege.herokuapp.com/addcourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.ctitle);
        ed2=(EditText)findViewById(R.id.des);
        ed3=(EditText)findViewById(R.id.dur);
        ed4=(EditText)findViewById(R.id.venue);
        ed5=(EditText)findViewById(R.id.date);
        b1=(AppCompatButton)findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcoursetitle=ed1.getText().toString();
                getdescription=ed2.getText().toString();
                getduration=ed3.getText().toString();
                getvenue=ed4.getText().toString();
                getdate=ed5.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        ed1.setText("");
                        ed2.setText("");
                        ed3.setText("");
                        ed4.setText("");
                        ed5.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<>();
                        hm.put("courseTitle",getcoursetitle);
                        hm.put("courseDescription",getdescription);
                        hm.put("courseDuration",getduration);
                        hm.put("courseVenue",getvenue);
                        hm.put("courseDate",getdate);


                     return hm;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);


            }
        });




    }
}