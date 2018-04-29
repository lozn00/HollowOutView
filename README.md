### 导入依赖

```


compile 'cn.qssq666:hollowoutview:0.1'
```

### 修改半透明蒙版颜色
直接拿到view然后getPaint()设置颜色即可。
### 自定义更多透明镂空区域形状
实现下面的接口就可以实现了


```
```java

public interface HollowedOutShapeInfoI
 { void executeDraw(Canvas canvas, Paint paint);}


```
### 使用方法

```java

        HollowOutView hollowOutView = (HollowOutView) findViewById(R.id.hollowoutview);
        //在中心点100的区域绘制一个50px大小的圆圈 透明区域
        HollowOutView.CiceleHollowedShapeInfo circle=new HollowOutView.CiceleHollowedShapeInfo().setCx(100).setCy(100).setRadius(100);
        hollowOutView.addGuideDrawInfo(circle);
        //添加一个矩形 镂空透明区域 所有单位都是px,自己转换
        HollowOutView.RectHollowedShapeInfo rect=new HollowOutView.RectHollowedShapeInfo().setHeight(100).setWidth(90).setLeft(400).setTop(800);
        hollowOutView.addGuideDrawInfo(rect);
        hollowOutView.applyChanage();

```

