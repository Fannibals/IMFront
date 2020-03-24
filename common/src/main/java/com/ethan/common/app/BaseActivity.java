package com.ethan.common.app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ethan.common.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindows();

        if (initArgs(getIntent().getExtras())){
            setContentView(getContentLayoutId());
            initWidget();
            initData();
        }else{
            finish();
        }
    }

    // init windows ,but when to use?
    protected void initWindows(){

    }

    /**
     * init some arguments
     * @param bundle
     * @return
     */
    protected boolean initArgs(Bundle bundle){
        return true;
    }

    protected abstract int getContentLayoutId();

    /**
     * init the layout
     */
    protected void initWidget(){
        ButterKnife.bind(this);
    }

    protected void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        // when click the back btn on the navigation bar, finish itself
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * checking whether the customized fragment has intercept the back method
     * @see BaseFragment#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments.size() > 0){
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment){
                    if (((BaseFragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }


        super.onBackPressed();
        finish();
    }
}
