package csu.edu.ice.coursetable.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import csu.edu.ice.coursetable.CustomCourse;

/**
 * Created by ice on 2017/12/8.
 */

public class CourseView extends AppCompatButton {

    private static final String TAG = "CourseView";

    private CustomCourse course;

    public CourseView(Context context) {
        this(context, null);
    }

    public CourseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_course,this);
//        ButterKnife.bind(view);
    }


    public int getCourseId() {
        return course.getId();
    }

    public int getStartSection() {
        return course.getStartSection();
    }


    public int getEndSection() {
        return course.getEndSection();
    }

    public int getWeek() {
        return course.getWeekday();
    }

    public void setCourse(CustomCourse course) {
        this.course = course;
    }
//
//    public void setTitle(String text) {
//        title.setText(text);
//    }
//
//    public void setAddress(String text){
//        address.setText(text);
//    }
//
//    public void getTitlePos(){
//        Log.d(TAG, "getTitlePos: x:"+title.getLeft());
//        Log.d(TAG, "getTitlePos: y:"+title.getTop());
//
//    }
}
