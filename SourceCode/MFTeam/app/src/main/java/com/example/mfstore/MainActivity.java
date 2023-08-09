package com.example.mfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.mfstore.eventListener.Replace_Fragment;
import com.example.mfstore.eventListener.Replace_Fragment_vidu;
import com.example.mfstore.fragment.FavouriteFragment;
import com.example.mfstore.fragment.HistoryFragment;
import com.example.mfstore.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private static final int Fragment_Home =0;
    private static final int Fragment_Favoutite =1;
    private static final int Fragment_History =2;

    private int curentFragment =  Fragment_Home; // đầu tin mở giao diện coi như xác định là home lun

    Replace_Fragment rf = new Replace_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ánh xạ id của drawlayout của activity_main.xml
        drawer = (DrawerLayout) findViewById(R.id.drawID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // khai báo NavigationView ánh xạ các id bên navigation của layout chính activity_main.xml
        NavigationView navigationView = findViewById(R.id.navigation_view_id);
        // code logic bắt sự kiện khi nhấn vào các icon menu button đc xây dựng trong Navigation bên file activity_main.xml
        navigationView.setNavigationItemSelectedListener(this);

        /*xử lý vào app một phát là cái giao diện đầu tiên là FragmentHome*/
        rf.replaceFragment(this, new HomeFragment());
        /* thg nếu mở ra mà mặc định HomeFragment thì đúng là nó hiển thị nhưng chưa đảm bảo là trang
         * fragment_home nó đc chọn nên cần xử lý thêm: để đảm bảo là chọn đc chính xác ở trang home*/
        navigationView.getMenu().findItem(R.id.nav_home_id).setChecked(true);

        /*xủ lý sự kiện cho button Cart(nhấn card chuyển giao diện)*/
        ImageButton cart_button = findViewById(R.id.cartButton);
        cart_button.setOnClickListener(new Replace_Fragment_vidu(this));
    }


    // method đc sinh ra khi implemenmts NavigationView.OnNavigationItemSelectedListener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home_id){
            if(curentFragment!=Fragment_Home){
                rf.replaceFragment(this, new HomeFragment());
                curentFragment=Fragment_Home;
            }
        }else if(id == R.id.nav_favourite_id){
            if(curentFragment!= Fragment_Favoutite){
                rf.replaceFragment(this, new FavouriteFragment());
                curentFragment= Fragment_Favoutite;
            }
        }else if(id == R.id.nav_history_id){
            if(curentFragment!=Fragment_History){
                rf.replaceFragment(this, new HistoryFragment());
                curentFragment=Fragment_History;
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        // nếu drawer: thanh điều hg đang bật menu khi nhấn back sẽ thoát cai menu thui
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            // còn nếu nó đã đóng rồi mà nhấn back thì thoát app lun
            super.onBackPressed();
        }
    }
}