package csu.edu.ice.coursetable.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import csu.edu.ice.coursetable.CustomCourse;
import csu.edu.ice.coursetable.R;

public class CourseLayout extends ViewGroup {
    private static final String TAG = "CourseLayout";
    private List<CourseView> courseViewList = new ArrayList<>();
    private List<CustomCourse> courseList = new ArrayList<>();
    private int width;//布局宽度
    private int height;//布局高度
    private int sectionHeight;//每节课高度
    private int sectionWidth;//每节课宽度
    private int sectionNumber = 10;//一天的节数
    private int dayNumber = 7;//一周的天数
    private int pideWidth = 0;//分隔线宽度,dp
    private int pideHeight = 0;//分隔线高度,dp

    public CourseLayout(Context context) {
        this(context, null);
    }

    public CourseLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CourseLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        width = (int) (getScreenWidth() - getResources().getDimension(R.dimen.leftGridWidth));//默认宽度全屏
        height = (int) (getResources().getDimension(R.dimen.gridHeight) * sectionNumber);//默认高度600dp
        pideWidth = dip2px(2);//默认分隔线宽度2dp
        pideHeight = dip2px(2);//默认分隔线高度2dp
        sectionHeight = (int) getResources().getDimension(R.dimen.gridHeight);//计算每节课高度
        sectionWidth = (int) ((getScreenWidth() - getResources().getDimension(R.dimen.leftGridWidth)) / 7);//计算每节课宽度

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        final int weekday = (int) ((x - pideWidth) / sectionWidth + 1);
        final int startSectin = (int) ((y - pideHeight) / sectionHeight + 1);
        CourseView courseView = new CourseView(getContext());
        courseView.setCourse(new CustomCourse(0, startSectin, startSectin, weekday));
        courseView.setBackground(getResources().getDrawable(R.drawable.plus));
        courseView.setOnClickListener(v -> {
            courseView.setBackground(null);
            Toast.makeText(getContext(), "weekday:" + weekday + "  startSection:" + startSectin, Toast.LENGTH_LONG).show();
        });
        clearOtherCourseBackground();
        addView(courseView);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        courseViewList.clear();
        for (int i = 0; i < getChildCount(); i++) {
            CourseView child = (CourseView) getChildAt(i);
            courseViewList.add(child);//增加到list中
        }

        for (CourseView child : courseViewList) {
            int week = child.getWeek();//获得周几
            int startSection = child.getStartSection();//开始节数
            int endSection = child.getEndSection();//结束节数

            int left = sectionWidth * (week - 1) + pideWidth;//计算左边的坐标
            int right = left + sectionWidth - pideWidth;//计算右边坐标
            int top = sectionHeight * (startSection - 1) + pideHeight;//计算顶部坐标
            int bottom = top + (endSection - startSection + 1) * sectionHeight - pideHeight;//计算底部坐标

            child.layout(left, top, right, bottom);
        }

    }

    private void clearOtherCourseBackground() {
        for (CourseView cours : courseViewList) {
            if (cours.getCourseId() == 0) {
                //清楚没有课程的背景
                cours.setBackground(null);
                removeView(cours);
            }
        }
    }

    public int dip2px(float dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public int getScreenWidth() {
        WindowManager manager = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public boolean isFree(CourseView courseView) {
        for (CustomCourse cours : courseList) {
            if (cours.getStartSection() <= courseView.getStartSection() && cours.getEndSection() >= courseView.getEndSection()) {
                return false;
            }
        }
        return true;
    }

    public void addCourse(CustomCourse customCourse, OnClickListener onClickListener) {
        courseList.add(customCourse);
        CourseView courseView = new CourseView(getContext());
        courseView.setText(customCourse.getName() + "\n\n" + customCourse.getAddress());
        courseView.setCourse(customCourse);
        courseView.setBackground(getResources().getDrawable(R.drawable.shape_course_view));
        GradientDrawable shapeDrawable = (GradientDrawable) courseView.getBackground();

        shapeDrawable.setColor(customCourse.getBackgroundColor());//根据课程动态更改背景颜色
        courseView.setPadding(0, 0, 0, 0);
        courseView.setTextSize(13);
        courseView.setTextColor(Color.WHITE);
        courseView.setOnClickListener(v -> {
            onClickListener.onClick(v);
        });

        addView(courseView);//添加到课程表

    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        courseList.clear();
    }


}