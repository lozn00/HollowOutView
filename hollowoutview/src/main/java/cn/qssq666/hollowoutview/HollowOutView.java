package cn.qssq666.hollowoutview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qssq on 2018/4/29 qssq666@foxmail.com
 */

public class HollowOutView extends View {

    public Paint getPaint() {
        return paint;
    }

    private Paint paint;

    public HollowOutView(Context context) {
        super(context);
        init(context);
    }

    public HollowOutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {


        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#7f000000"));//百分之50
        paint.setColor(Color.parseColor("#32000000"));//百分之20


        paint.setColor(Color.parseColor("#c8000000"));//百分之80
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        }



    }

    public HollowOutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);//从绘制模式图片得知 这种模式会导致挖空的区域消失
        paint.setXfermode(mXfermode);
        for (int i = 0; i < infos.size(); i++) {

            HollowedOutShapeInfoI guideDrawInfo = infos.get(i);
            guideDrawInfo.executeDraw(canvas, paint);

        }
        paint.setXfermode(null);


    }

    protected List<HollowedOutShapeInfoI> infos = new ArrayList<>();

    public void addGuideDrawInfo(HollowedOutShapeInfoI info) {

        infos.add(info);

    }

    /**
     * 一次性添加多个镂空区域图形
     *
     * @param infos
     */
    public void addGuideDrawInfos(List<HollowedOutShapeInfoI> infos) {
        this.infos.addAll(infos);
        postInvalidate();

    }

    public void clearGuideDrawInfo() {

        infos.clear();

    }

    /**
     * 如果是单个添加则需要执行本方法 如果是调用 @{{@link HollowOutView#addGuideDrawInfos(List)} ()} 那么就不需要执行此方法 }}
     */
    public void applyChanage() {
        postInvalidate();
    }

    /**
     * 镂空形状接口 可用户自定义
     */
    public interface HollowedOutShapeInfoI {
        /**
         * 要挖空的形状
         *
         * @param canvas
         * @param paint
         */
        void executeDraw(Canvas canvas, Paint paint);
    }

    public static class RectHollowedShapeInfo implements HollowedOutShapeInfoI {


        public int getLeft() {
            return left;
        }

        public RectHollowedShapeInfo setLeft(int left) {
            this.left = left;
            return this;
        }

        public int getTop() {
            return top;
        }

        public RectHollowedShapeInfo setTop(int top) {
            this.top = top;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public RectHollowedShapeInfo setWidth(int width) {
            this.width = width;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public RectHollowedShapeInfo setHeight(int height) {
            this.height = height;
            return this;
        }

        int left;
        int top;
        int width;
        int height;

        @Override
        public void executeDraw(Canvas canvas, Paint paint) {
            canvas.drawRect(left, top, width, height, paint);
        }

    }

    //默认实现的镂空圆形图标


    public static class CiceleHollowedShapeInfo implements HollowedOutShapeInfoI {

        public int getCx() {
            return cx;
        }

        /**
         * 要绘制的圆圈的中心点x坐标
         *
         * @param cx
         */
        public CiceleHollowedShapeInfo setCx(int cx) {
            this.cx = cx;
            return this;
        }

        /**
         * 要绘制的中心点y坐标
         *
         * @return
         */
        public int getCy() {
            return cy;
        }

        public CiceleHollowedShapeInfo setCy(int cy) {
            this.cy = cy;
            return this;
        }

        public int getRadius() {
            return radius;
        }

        public CiceleHollowedShapeInfo setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        int cx;
        int cy;
        /**
         * 圆角半径
         */
        int radius;

        @Override
        public void executeDraw(Canvas canvas, Paint paint) {



            canvas.drawCircle(
                    cx,
                    cy,
                    radius, paint);
        }
        /**
         * 要挖空的形状
         *
         * @param canvas
         */
    }


}
