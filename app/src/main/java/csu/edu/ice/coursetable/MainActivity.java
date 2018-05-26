package csu.edu.ice.coursetable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import csu.edu.ice.coursetable.widget.CourseLayout;

public class MainActivity extends AppCompatActivity {

    private int nowWeek = 5;
    private View lastClickedView;
    private LinearLayout layoutWeekWrapper;
    private HorizontalScrollView scrollWeek;
    private ImageView imageArrow;
    private LinearLayout topWeek;
    private LinearLayout leftTime;
    private CourseLayout courseLayout;
    private TextView textWeek;
    private LinearLayout layoutWeek;
    private List<CustomCourse> customCourseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        layoutWeekWrapper = findViewById(R.id.layoutWeekWrapper);
        scrollWeek = findViewById(R.id.scrollWeek);
        imageArrow = findViewById(R.id.imageArrow);
        topWeek = findViewById(R.id.top_week);
        leftTime = findViewById(R.id.left_time);
        courseLayout = findViewById(R.id.courseLayout);
        textWeek = findViewById(R.id.textWeek);
        layoutWeek = findViewById(R.id.layoutWeek);

        layoutWeek.setOnClickListener(v -> {
            if (scrollWeek.getVisibility() == View.VISIBLE) {
                scrollWeek.setVisibility(View.GONE);
                imageArrow.setRotation(0);
                initCourseView(customCourseList,nowWeek);
            } else {
                scrollWeek.setVisibility(View.VISIBLE);
                imageArrow.setRotation(180);
            }
        });

        textWeek.setText("第"+nowWeek+"周");
        initCourseData();
        initCourseView(customCourseList,15);
        initWeek();

    }

    private void initCourseData() {
        int colorPink = getResources().getColor(R.color.colorAccent);
        int colorBlue = getResources().getColor(R.color.colorPrimary);

        customCourseList = new ArrayList<>();
        customCourseList.add(new CustomCourse(1,"中国近代史",1,2,1,colorBlue));
        customCourseList.add(new CustomCourse(2,"中国近代史",3,4,3,colorBlue));
        customCourseList.add(new CustomCourse(3,"编译原理",5,6,1,colorPink));
        customCourseList.add(new CustomCourse(4,"编译原理",7,8,3,colorPink));
        customCourseList.add(new CustomCourse(5,"操作系统",1,2,2,colorBlue));
        customCourseList.add(new CustomCourse(6,"操作系统",3,4,4,colorBlue));
        customCourseList.add(new CustomCourse(7,"计算机图形学",5,6,5,colorPink));
        customCourseList.add(new CustomCourse(8,"计算机图形学",3,4,2,colorPink));
        customCourseList.add(new CustomCourse(9,"计算机网络",3,4,5,colorBlue));
        customCourseList.add(new CustomCourse(10,"计算机网络",7,8,1,colorBlue));


    }


    private void initWeek() {

        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setHeight((int) getResources().getDimension(R.dimen.gridHeight));
            textView.setText((i + 1) + "");
            textView.setGravity(Gravity.CENTER);
            leftTime.addView(textView);
        }

        topWeek.addView(getWeekView(1, "周一"));
        topWeek.addView(getWeekView(2, "周二"));
        topWeek.addView(getWeekView(3, "周三"));
        topWeek.addView(getWeekView(4, "周四"));
        topWeek.addView(getWeekView(5, "周五"));
        topWeek.addView(getWeekView(6, "周六"));
        topWeek.addView(getWeekView(7, "周日"));


        for (int i = 1; i < 20; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_week, null);
            TextView textWeek = view.findViewById(R.id.layoutWeek);
            textWeek.setText(i + "");
            if (i == nowWeek) {
                lastClickedView = view;
                view.setBackgroundColor(Color.WHITE);
                view.findViewById(R.id.textThisWeek).setVisibility(View.VISIBLE);
            }
            //选择了别的周
            int finalI = i;
            view.setOnClickListener(v -> {
                lastClickedView.setBackgroundColor(getResources().getColor(R.color.cyan));
                v.setBackgroundColor(Color.WHITE);
                lastClickedView = v;

                initCourseView(customCourseList, finalI);
            });
            layoutWeekWrapper.addView(view);
        }
    }

    View getWeekView(int day, String week) {
        View view = getLayoutInflater().inflate(R.layout.week_layout, topWeek, false);
        TextView tvTop = view.findViewById(R.id.top);
        tvTop.setText(week);
        TextView tvBootom = view.findViewById(R.id.bottom);
        tvBootom.setText(day + "");
        if (day == 2) {
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvBootom.setTextColor(Color.WHITE);
            tvTop.setTextColor(Color.WHITE);
        }
        return view;
    }

    private void initCourseView(List<CustomCourse> customCourseList, int week) {
        courseLayout.removeAllViews();
        if (customCourseList == null) return;

        for (CustomCourse customCourse : customCourseList) {
                courseLayout.addCourse(customCourse, v -> {
                    Toast.makeText(this, "点击了课程" + customCourse.getName(), Toast.LENGTH_SHORT).show();
                });
        }
        courseLayout.requestLayout();
    }

}
