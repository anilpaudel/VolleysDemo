package np.com.anilpaudel.volleysdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String username="ashish";
    String password="gautam";
    String place="nawalparasi";
    Button button;
    TextView textView;
    String server_url="http://10.100.30.233/greetings.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.bn);
        textView=(TextView) findViewById(R.id.txt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                try {


                    String data = URLEncoder.encode("username", "UTF-8")
                            + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8")
                            + "=" + URLEncoder.encode(password, "UTF-8");
                }
                catch(IOException e)
                {

                }
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textView.setText("sexy and"+ response);
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Something went wrong....");
                        error.printStackTrace();
                        requestQueue.stop();

                    }
                })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map <String, String> params = new HashMap<String, String>();
                        params.put("name",username);
                        params.put("password",password);
                        params.put("address",place);
                        return  params;

                    }



                }


                        ;
                requestQueue.add(stringRequest);
            }
        });

    }
}
