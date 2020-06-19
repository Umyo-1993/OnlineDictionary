package com.example.onlinewordsmartapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class ShowActivity extends AppCompatActivity {
    private RecyclerView RecyclerList;
    private DatabaseReference UsersRef;
    EditText searchbar;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<ArrayList<String>> finarrayList=new ArrayList<ArrayList<String>>();
    SearchView searchView;
    ArrayAdapter<String>arrayAdapter;
    Button searchbutton;


    public FirebaseRecyclerAdapter<GetSet, ShowViewHolder> adapter ;
    public FirebaseRecyclerAdapter<GetSet, ShowViewHolder> adapter2;


  /*  @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        UsersRef = FirebaseDatabase.getInstance().getReference();
        RecyclerList = findViewById(R.id.recycler_list);
        searchbar = findViewById(R.id.searchbar);
        searchbutton=findViewById(R.id.searchbutton);
        RecyclerList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerList.setHasFixedSize(true);
        UsersRef.keepSynced(true);
        Search("");
            searchbar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString() != null) {
                        Search(s.toString());
                    } else {
                        Search("");
                    }
                }
            });

        }



  /*      searchbar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        searchbar.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                if(s.toString()!=null)
                {
                    Loaddata(s.toString());
                }
           }
       });

    }*/
/*public void Loaddata(String data)
{
    final Query query=UsersRef.startAt(data).endAt(data+"\uf8ff");
    FirebaseRecyclerOptions<GetSet> options = new FirebaseRecyclerOptions.Builder<GetSet>().setQuery(query, GetSet.class).build();
    adapter2 = new FirebaseRecyclerAdapter<GetSet, ShowViewHolder>(options) {
        @Override
        protected void onBindViewHolder(ShowViewHolder showViewHolder, int i, GetSet getSet) {
            String wordname = String.valueOf(getSet.getWordname());
            String wordmeaning = String.valueOf(getSet.getWordmeaning());
            String wordroot = String.valueOf(getSet.getWordroot());
            showViewHolder.wordname.setText(wordname);
            showViewHolder.wordmeaning.setText(wordmeaning);
            showViewHolder.wordroot.setText(wordroot);
            Toast.makeText(ShowActivity.this, ""+query.toString() , Toast.LENGTH_SHORT).show();


        }

        @NonNull
        @Override
        public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ShowActivity.this).inflate(R.layout.latlng, parent, false);
            ShowViewHolder showViewHolder = new ShowViewHolder(view);
            return showViewHolder;
        }

    };


    RecyclerList.setAdapter(adapter2);

 */

public void Search(String data)
{
    Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();

    Query query=UsersRef.orderByChild("wordname").startAt(data).endAt(data+"\u8ff8");

    FirebaseRecyclerOptions<GetSet> options = new FirebaseRecyclerOptions.Builder<GetSet>().setQuery(query, GetSet.class).build();
    adapter = new FirebaseRecyclerAdapter<GetSet, ShowViewHolder>(options) {
        @Override
        protected void onBindViewHolder(ShowViewHolder showViewHolder, int i, GetSet getSet) {
            String wordname = String.valueOf(getSet.getWordname());
            String wordmeaning = String.valueOf(getSet.getWordmeaning());
            String wordroot = String.valueOf(getSet.getWordroot());
            showViewHolder.wordname.setText(wordname);
            showViewHolder.wordmeaning.setText(wordmeaning);
            showViewHolder.wordroot.setText(wordroot);


        }

        @NonNull
        @Override
        public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ShowActivity.this).inflate(R.layout.latlng, parent, false);
            ShowViewHolder showViewHolder = new ShowViewHolder(view);
            return showViewHolder;
        }


    };

    adapter.startListening();
    RecyclerList.setAdapter(adapter);
}


}
class ShowViewHolder extends RecyclerView.ViewHolder
{
    TextView wordname,wordmeaning,wordroot;
    public ShowViewHolder(@NonNull View itemView) {
        super(itemView);
        wordname=itemView.findViewById(R.id.wordname);
        wordmeaning=itemView.findViewById(R.id.wordmeaning);
        wordroot=itemView.findViewById(R.id.wordroot);
    }
}

