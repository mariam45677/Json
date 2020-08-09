package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class PockAdapter extends RecyclerView.Adapter<PockAdapter.PockHolder> {
    private  List<Pock> mobileList =  new ArrayList<>();
    private RequestQueue requestQueue;
    PockAdapter(Context context){
        requestQueue = Volley.newRequestQueue(context);
        relase();

    }
    public void relase() {
        String Url ="https://pokeapi.co/api/v2/pokemon?limit=151";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("Results");

                    for (int i = 0; i < results.length(); i++){
                        JSONObject result = results.getJSONObject(i);
                        mobileList.add(new Pock(result.getString("name"),
                                result.getString("url")
                        ));
                }
                    notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("cs50", "JsonEror", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "PockList");

            }
        });
        requestQueue.add(req);

    }



    @NonNull
    @Override
    public PockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.oock_rew, parent, false);
       return new PockHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull PockHolder holder, int position) {
        Pock cur = mobileList.get(position);
        holder.textView.setText(cur.getName());
        holder.vv.setTag(cur);


    }

    @Override
    public int getItemCount() {
        return mobileList.size();
    }

    public static class PockHolder extends RecyclerView.ViewHolder{
       private TextView textView;
       private LinearLayout vv;
        public PockHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.Tv_vv);
            vv = itemView.findViewById(R.id.bb);
            vv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pock curr = (Pock) vv.getTag();
                    Intent i = new Intent (view.getContext(),PockActivity.class);
                    i.putExtra("name",curr.getName());
                   // i.putExtra("num",curr.getNum());
                  view.getContext().startActivity(i);


                }
            });

        }
    }
}
