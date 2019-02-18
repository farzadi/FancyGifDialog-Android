package com.shashank.sony.fancygifdialoglibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogRadioListener;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogWithRadioButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    List<String> radioButtonList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        b1.setOnClickListener(this);

        radioButtonList.add("hello");
        radioButtonList.add("how are you");
    }
    @Override
    public void onClick(View view) {
        new FancyGifDialogWithRadioButton.Builder(this)
                .setTitle("Granny eating chocolate dialog box")
                .setMessage("This is a granny eating chocolate dialog box. This library is used to help you easily create fancy gify dialog.")
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackground("#FF4081")
                .setRadioButtonList(radioButtonList)
                .setPositiveBtnText("Ok")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.gif1)
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogRadioListener() {
                    @Override
                    public void OnSelectedRadioButton(RadioGroup radioButtonGroup) {
                        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
                        View radioButton = radioButtonGroup.findViewById(radioButtonID);
                        int idx = radioButtonGroup.indexOfChild(radioButton);
                        RadioButton r = (RadioButton) radioButtonGroup.getChildAt(idx);
                        if(r != null){
                            String selectedtext = r.getText().toString();
                            Toast.makeText(MainActivity.this, selectedtext, Toast.LENGTH_SHORT).show();

                        }

                    }
                })
                .OnNegativeClicked(new FancyGifDialogRadioListener() {
                    @Override
                    public void OnSelectedRadioButton(RadioGroup radioButtonGroup) {

                    }
                })
                .build();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
