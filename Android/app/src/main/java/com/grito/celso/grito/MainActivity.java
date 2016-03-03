package com.grito.celso.grito;

import android.os.Bundle;
import android.sax.Element;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    RequestQueue rq;
    TextView textView;
    EditText editText;
    Button btnR;
    Button btnG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rq = Volley.newRequestQueue(this);
        textView = (TextView) findViewById(R.id.textView);
        btnG = (Button) findViewById(R.id.buttonGo);
        btnR = (Button) findViewById(R.id.buttonRefresh);
        editText = (EditText) findViewById(R.id.editText);

        textView.setMovementMethod(new ScrollingMovementMethod());
        btnR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getMsg();
            }
        });

        btnG.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (editText.getText().toString() != "") {
                    sendMsg();
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.18:8080/axis2/services/HelloClass/getMsg"; // Endereço do servidor

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMsg();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void getMsg() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.18:8080/axis2/services/HelloClass/getMsg"; // Endereço do servidor

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String text = handXML(response);
                        textView = (TextView) findViewById(R.id.textView);
                        textView.setText(text);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void sendMsg() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.18:8080/axis2/services/HelloClass/insertMsg?msg="; // Endereço do servidor
        editText = (EditText) findViewById(R.id.editText);
        final String msg = editText.getText().toString();
        url+= msg;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMsg();
                        editText = (EditText) findViewById(R.id.editText);
                        editText.setText("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public String handXML(String str)  {
        String res = str.replace("<ns:return>", "\n");
        res = res.replaceAll("<[^>]+>", "");
        return res;
    }
}
