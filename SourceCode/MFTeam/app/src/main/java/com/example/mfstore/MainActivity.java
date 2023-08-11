package com.example.mfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.mfstore.eventListener.Replace_Fragment;
import com.example.mfstore.eventListener.Replace_Fragment_Cart;
import com.example.mfstore.eventListener.Replace_Fragment_Photo;
import com.example.mfstore.fragment.FavouriteFragment;
import com.example.mfstore.fragment.HistoryFragment;
import com.example.mfstore.fragment.HomeFragment;

import com.example.mfstore.model.Photo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // nhóm chức năng properties dùng để tạo auto slider cho banner
    private Handler handler = new Handler();
    private Runnable auto_runslider_banner = new Runnable() {
        @Override
        public void run() {
            // nếu img là ảnh cúi trong banner thì nó sẽ xử lý quay lại ảnh đầu chạy tiêp
            if(viewPager2.getCurrentItem() == getListPhoto().size()-1){
                    viewPager2.setCurrentItem(0);
            }else{
                // chạy từ viewpager có img đầu tin + đến img cúi cùng
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    // nhóm properties của slider banner
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;



    // nhóm properties của navigation and drawelayout
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

        // gọi init() class khởi tạo lk id
        init();

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

        /* ****xử lý vào app một phát là cái giao diện đầu tiên là FragmentHome*****/
        rf.replaceFragment(this, new HomeFragment());
        /* thg nếu mở ra mà mặc định HomeFragment thì đúng là nó hiển thị nhưng chưa đảm bảo là trang
         * fragment_home nó đc chọn nên cần xử lý thêm: để đảm bảo là chọn đc chính xác ở trang home*/
        navigationView.getMenu().findItem(R.id.nav_home_id).setChecked(true);

        /* **** xủ lý sự kiện cho button Cart(nhấn card chuyển giao diện)******/
        ImageButton cart_button = findViewById(R.id.cartButton);
        cart_button.setOnClickListener(new Replace_Fragment_Cart(this));

        /* *****xử lý sự kiện(slider_banner vơi các img)******/
        Replace_Fragment_Photo event_photo = new Replace_Fragment_Photo(this, getListPhoto());
        /*PagerAdapter là lớp cung cấp dữ liệu và quản lý các trang (pages) trong ViewPager. Khi bạn
         gán một PagerAdapter cho ViewPager, bạn đang cho ViewPager biết làm cách nào để hiển thị và
         quản lý các trang.*/
        viewPager2.setAdapter(event_photo);
        // liên kết các circle là vòng tròn hiện thị chuyển trang trong banner
        circleIndicator3.setViewPager(viewPager2);


        // viewapger2 ghi nhận và thay đổi thực thi auto_slide->ử lý cho auto run
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                /* chức năng removeCallbacks này giúp sau:
                * => ta đã có xử lý autorunable trc đó rồi
                * => vì vậy khi bắtdđầu cho sự kiện tếp theo thì ta cần remove chức năng auto đó đi tr
                * sau đo nó sẽ bắt đầu thực hiện logic runable lại từ đâu */
                handler.removeCallbacks(auto_runslider_banner);
                handler.postDelayed(auto_runslider_banner,3000);
            }
        });

    }




    /*method listphoto ánh các img bên của chứa trong drawable phục vụ cho banner*/
    private List<Photo> getListPhoto(){
       List<Photo> listImg = new ArrayList<>();
       listImg.add(new Photo(R.drawable.banner1));
        listImg.add(new Photo(R.drawable.banner2));
        listImg.add(new Photo(R.drawable.banner3));
        listImg.add(new Photo(R.drawable.banner4));
        listImg.add(new Photo(R.drawable.banner5));
       return  listImg;
    }


    /* method đc sinh ra khi implemenmts NavigationView.OnNavigationItemSelectedListener*/
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

        // đóng thanh điều hướng về phía bên trái(nghĩa là khi nhấn chọn item trong menu mở ra giao diện tương ứng thì menu sẽ ẩn đi)
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    // init() ánh xạ id các class fragment tương ứng
    public void init(){
        // ánh xạ view của ViewPager trogn activity_main.xml
        viewPager2 = (ViewPager2) findViewById(R.id.viewpager2_id);
        // ánh xạ id của CicleIndivator bên activity_main.xml
        circleIndicator3 = (CircleIndicator3) findViewById(R.id.circle3_id);
    }

    // chức năng xử lý cho nút back khoong thoát mất app
    @Override
    public void onBackPressed() {
        // nếu drawer: thanh điều hg đang bật menu khi nhấn back sẽ thoát cái menu thui
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            // còn nếu nó đã đóng rồi mà nhấn back thì thoát app lun
            super.onBackPressed();
        }
    }
}