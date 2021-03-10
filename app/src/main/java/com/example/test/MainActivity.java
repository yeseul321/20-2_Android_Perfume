package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SearchView searchView;
    private Spinner spinner;


    private ArrayList<perfume> arrayList = new ArrayList<>();
    private ArrayList<perfume> copy_List = new ArrayList<>();
    //현재 선택된 향수 저장
    public static Context context_main;
    public String current;

    //home
    private Button buttonPerfume;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference2;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context_main = this;

        //액티비티 6,7
        setContentView(R.layout.activity_main);

        //탭 구현
        TabHost tabHost = findViewById(R.id.host);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon1, null));
        spec.setContent(R.id.tab1);
        tabHost.addTab(spec);

        buttonPerfume = (Button) findViewById(R.id.tab_content1);
        buttonPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity_sign 연결
                Intent intent = new Intent(MainActivity.this, asking.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();


        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2, null));
        spec.setContent(R.id.tab2);
        tabHost.addTab(spec);


        //향수 리스트 출력 구현(리사이클러뷰)
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); // id 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화


        //layoutManager 설정
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("향수"); // Firebase 의 DB 테이블과 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Firebase 의 DB를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트를 초기화
                //반복문으로 데이터의 list 추출
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    perfume info = snapshot.getValue(perfume.class); // 만들어둔 perfume 객체에 데이터를 담는다.
                    arrayList.add(info); // 담은 데이터를 배열리스트에 넣고, 리사이클러뷰로 보낼 준비
                    copy_List.add(info);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //에러가 발생할 경우
                Log.e("MainActivity", String.valueOf(databaseError.toException()));
            }
        });

        searchView = (SearchView)findViewById(R.id.search_view) ;
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //드롭다운 버튼 구현
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner.getSelectedItem().toString();
                if (text.equals("별점순")) //별점순 출력
                {

                    Collections.sort(copy_List, new Descending());
                    adapter = new CustomAdapter(copy_List, getApplicationContext());
                    //현재 선택된 향수 저장
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            adapter.getFilter().filter(newText);
                            return false;
                        }
                    });
                    adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int pos) {
                            current = copy_List.get(pos).getName();
                            Intent intent = new Intent(getApplicationContext(),ShowInfoActivity.class);
                            startActivity(intent);
                        }

                    });
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);

                }
                else if(text.equals("이름순"))//이름순 출력
                {

                    adapter = new CustomAdapter(arrayList, getApplicationContext());
                    //현재 선택된 향수 저장
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            adapter.getFilter().filter(newText);
                            return false;
                        }
                    });
                    adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int pos) {
                            current = arrayList.get(pos).getName();
                            Intent intent = new Intent(getApplicationContext(),ShowInfoActivity.class);
                            startActivity(intent);
                        }

                    });
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);

                }
            }


            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        databaseReference2=database.getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        databaseReference2 = databaseReference2.child(uid);
        int id = item.getItemId();
        if (id == R.id.action_user) {
            if (user != null) {
                name = user.getDisplayName();
                Toast.makeText(getApplicationContext(), name + " 님 반갑습니다! ", Toast.LENGTH_SHORT).show();
                return true;
            }
        } else {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


    class Descending implements Comparator<perfume> {
        @Override
        public int compare(perfume p, perfume q) {
            String a=String.valueOf(p.getEstimating());
            String b=String.valueOf(q.getEstimating());
            return b.compareTo(a);
        }

    }


}