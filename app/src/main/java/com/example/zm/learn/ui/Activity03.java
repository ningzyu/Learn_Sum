package com.example.zm.learn.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.zm.learn.R;


public class Activity03 extends AppCompatActivity {
    private  String ymd,hm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);
    }


    public void click(View v){


    }

    public void time(){
        final StringBuilder sb = new StringBuilder();
        new DatePickerDialog(Activity03.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ymd=String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth);
                sb.append(ymd);
                new TimePickerDialog(Activity03.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hm=String.format("%d:%d",hourOfDay,minute);
                        sb.append(" "+hm);
                        Log.i("Aaaaaaaaaaaaa",sb.toString());
                    }
                    //0,0指的是时间，true表示是否为24小时，true为24小时制
                },00,00,true).show();
            }
        },2000,10,20).show();
    }



















//
//
//        View view = View.inflate(getApplicationContext(), R.layout.date_time_picker, null);
//        final DatePicker datePicker = (DatePicker)view.findViewById(R.id.new_act_date_picker);
//        final TimePicker timePicker = (TimePicker)view.findViewById(R.id.new_act_time_picker);
//
//        // Init DatePicker
//        int year;
//        int month;
//        int day;
//        if (StringUtils.isEmpty(arriveDateBtn.getText().toString())) {
//            // Use the current date as the default date in the picker
//            final Calendar c = Calendar.getInstance();
//            year = c.get(Calendar.YEAR);
//            month = c.get(Calendar.MONTH);
//            day = c.get(Calendar.DAY_OF_MONTH);
//        } else {
//            year = Activity03.arrive_year;
//            month = Activity03.arrive_month;
//            day = Activity03.arrive_day;
//        }
//        datePicker.init(year, month, day, null);
//
//        // Init TimePicker
//        int hour;
//        int minute;
//        if (StringUtils.isEmpty(arriveTimeBtn.getText().toString())) {
//            // Use the current time as the default values for the picker
//            final Calendar c = Calendar.getInstance();
//            hour = c.get(Calendar.HOUR_OF_DAY);
//            minute = c.get(Calendar.MINUTE);
//        } else {
//            hour = Activity03.arrive_hour;
//            minute = Activity03.arrive_min;
//        }
//        timePicker.setIs24HourView(true);
//        timePicker.setCurrentHour(hour);
//        timePicker.setCurrentMinute(minute);
//
//        // Build DateTimeDialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(Activity03.this);
//        builder.setView(view);
//        builder.setTitle(R.string.new_act_date_time_picker_title);
//        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                arrive_year = datePicker.getYear();
//                arrive_month = datePicker.getMonth();
//                arrive_day = datePicker.getDayOfMonth();
//                String dateStr = DateUtil.formatDate(arrive_year, arrive_month, arrive_day);
//                arriveDateBtn.setText(dateStr);
//
//                arrive_hour = timePicker.getCurrentHour();
//                arrive_min = timePicker.getCurrentMinute();
//                String timeStr = DateUtil.formatTime(arrive_hour, arrive_min);
//                arriveTimeBtn.setText(timeStr);
//            }
//        });
//        builder.show();
//    }


}
