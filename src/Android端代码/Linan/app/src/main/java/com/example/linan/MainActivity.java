package com.example.linan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

/**
 * @brief 主活动
 * Created by 11357 on 2018/12/20.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
         /** <对于bootombar实例的有关设置，如尺寸，颜色，图标等*/
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#2c9975")
                .setIconHeight(20)
                .setIconWidth(20)
                .setTitleSize(9)
                .setTitleIconMargin(5)
                .addItem(Fragment1.class,
                        "首页",
                        R.drawable.main_tab_notice_nor,
                        R.drawable.main_tab_notice_checked)
                .addItem(Fragment2.class,
                        "查询",
                        R.drawable.main_tab_search_nor,
                        R.drawable.main_tab_search_checked)
                .addItem(Fragment3.class,
                        "换乘",
                        R.drawable.main_tab_buschange_nor,
                        R.drawable.main_tab_buschange_checked)
                .addItem(Fragment4.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();
    }

    /**
     * 为碎片三站点线路查询的onActivityResult方法，从actitity_address_search方法中返回数据，
     * 根据请求码与结果码获得数据，将碎片三中的地址输入栏修改
     * @param[in] requestCode
     * @param[in] resultCode
     * @param[in] data
     * @return void
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnedData = data.getStringExtra("data_return");
                    System.out.println(returnedData);
                    EditText _f3startstop = (EditText)findViewById(R.id.et_f3_inputStart);
                    _f3startstop.setText(returnedData);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    System.out.println(returnData);
                    EditText _f3stopstop = (EditText)findViewById(R.id.et_f3_inputEnd);
                    _f3stopstop.setText(returnData);
                }
            default:
        }
   }
}
