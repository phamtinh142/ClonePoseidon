package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.Fragment.ProfileFragment;
import com.arsenal.santiha.cloneposeidon.Fragment.NotificationFragment;
import com.arsenal.santiha.cloneposeidon.Fragment.HomeFragment;
import com.arsenal.santiha.cloneposeidon.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static String Uid = "";

    private BottomNavigationView bottomNavigation;

    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        AnhXa();

        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();



        setFragment(homeFragment);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_notification:
                        if (Uid.equals("")) {
                            Toast.makeText(MainActivity.this, "Bạn phải đăng nhập trước !", Toast.LENGTH_LONG).show();
                        } else {
                            setFragment(notificationFragment);
                        }
                        return true;
                    case R.id.navigation_profile:
                        setFragment(profileFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });




    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }


    private void AnhXa() {
        bottomNavigation =  findViewById(R.id.bottom_navigation);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo !");
        alertDialog.setMessage("Thoát ứng dụng ?");
        alertDialog.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction transaction = fm.beginTransaction();
////            transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    transaction.replace(R.id.frag_container, new HomeFragment());
//
//                    transaction.commit();
//                    return true;
//                case R.id.navigation_dashboard:
//                    if (Uid.equals("")){
//                        Toast.makeText(MainActivity.this, "Bạn phải đăng nhập trước !", Toast.LENGTH_LONG).show();
//                    } else {
//                        transaction.replace(R.id.frag_container, new NotificationFragment());
//                        transaction.commit();
//                    }
//                    return true;
//
//                case R.id.navigation_notifications:
//                    transaction.replace(R.id.frag_container, new ProfileFragment());
//                    transaction.commit();
//                    return true;
//            }
//
//
//            return false;
//        }
//    };

}
