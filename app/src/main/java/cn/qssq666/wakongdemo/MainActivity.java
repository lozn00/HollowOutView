package cn.qssq666.wakongdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.qssq666.hollowoutview.HollowOutView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HollowOutView hollowOutView = (HollowOutView) findViewById(R.id.hollowoutview);
        //在中心点100的区域绘制一个50px大小的圆圈 透明区域
        HollowOutView.CiceleHollowedShapeInfo circle=new HollowOutView.CiceleHollowedShapeInfo().setCx(100).setCy(100).setRadius(100);
        hollowOutView.addGuideDrawInfo(circle);
        //添加一个矩形 镂空透明区域 所有单位都是px,自己转换
        HollowOutView.RectHollowedShapeInfo rect=new HollowOutView.RectHollowedShapeInfo().setHeight(100).setWidth(90).setLeft(400).setTop(800);
        hollowOutView.addGuideDrawInfo(rect);
        hollowOutView.applyChanage();

    }
}
