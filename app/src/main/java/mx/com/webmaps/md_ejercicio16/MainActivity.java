package mx.com.webmaps.md_ejercicio16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    TextView timeText;
    AppCompatButton pickTime;
    Calendar calendar;
    TimePickerDialog timePickerDialog;
    TimePickerDialog finalPicker;
    Boolean time24HourMode= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText =(TextView) findViewById(R.id.timeTextView_id);
        pickTime = (AppCompatButton) findViewById(R.id.timeButton_id);

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMin = calendar.get(Calendar.MINUTE);
                int currentSec = calendar.get(Calendar.SECOND);

                timePickerDialog = TimePickerDialog.newInstance(MainActivity.this,currentHour,currentMin,currentSec,time24HourMode);

                timePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));

                timePickerDialog.setTitle("Timer Picker");  //Siempre se visualizar en mayúsculas

                timePickerDialog.setThemeDark(true);

                timePickerDialog.dismissOnPause(true);  // false -> al minimizar ventana y regresar el datepicker sigue abierto, true-> cierra el datepicker cuando cambia de ventana

                timePickerDialog.setOkText("SET");

                timePickerDialog.setCancelText("DON'T SET");

                timePickerDialog.show(getFragmentManager(),"timePicker");

            }
        });

        finalPicker = (TimePickerDialog) getFragmentManager().findFragmentByTag("timePicker");

        if(finalPicker != null){
            finalPicker.setOnTimeSetListener(this);
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hour = hourOfDay <10 ? "0"+hourOfDay : "" + hourOfDay;
        String min = minute < 10 ? "0" + minute : "" + minute;
        String sec = second < 10 ? "0" + second : "" + second;

        String time = "You picked the following time: " + hour + ":" + min + ":" + sec;

        timeText.setText(time);

    }
}
