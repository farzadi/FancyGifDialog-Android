package com.shashank.sony.fancygifdialoglib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class FancyGifDialogWithRadioButton {

    private String title, message, positiveBtnText, negativeBtnText, pBtnColor, nBtnColor;
    private Activity activity;
    private FancyGifDialogRadioListener pListener, nListener;
    private boolean cancel;
    int gifImageResource;
    RadioGroup radioButtonGroup;
    int numberOfRadioButton;
    List<String> radioButtonList = new ArrayList<>();

    private FancyGifDialogWithRadioButton(FancyGifDialogWithRadioButton.Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.activity = builder.activity;
        this.pListener = builder.pListener;
        this.nListener = builder.nListener;
        this.pBtnColor = builder.pBtnColor;
        this.nBtnColor = builder.nBtnColor;
        this.positiveBtnText = builder.positiveBtnText;
        this.negativeBtnText = builder.negativeBtnText;
        this.gifImageResource = builder.gifImageResource;
        this.cancel = builder.cancel;
        this.radioButtonGroup = builder.radioButtonGroup;
        this.radioButtonList = builder.radioButtonList;
    }


    public static class Builder {
        private String title, message, positiveBtnText, negativeBtnText, pBtnColor, nBtnColor, btnRadio1, btnRadio2, btnRadio3;
        private Activity activity;
        private FancyGifDialogRadioListener pListener, nListener;
        private boolean cancel;
        int gifImageResource;
        RadioGroup radioButtonGroup;
        List<String> radioButtonList = new ArrayList<>();

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public FancyGifDialogWithRadioButton.Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public FancyGifDialogWithRadioButton.Builder setRadioButtonList(List<String> radioButtonList ) {
            this.radioButtonList = radioButtonList;
            return this;
        }


        public FancyGifDialogWithRadioButton.Builder setRadioText(String btnRadio1) {
            this.btnRadio1 = btnRadio1;
            return this;
        }


        public FancyGifDialogWithRadioButton.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public FancyGifDialogWithRadioButton.Builder setPositiveBtnText(String positiveBtnText) {
            this.positiveBtnText = positiveBtnText;
            return this;
        }

        public FancyGifDialogWithRadioButton.Builder setPositiveBtnBackground(String pBtnColor) {
            this.pBtnColor = pBtnColor;
            return this;
        }


        public FancyGifDialogWithRadioButton.Builder setNegativeBtnText(String negativeBtnText) {
            this.negativeBtnText = negativeBtnText;
            return this;
        }

        public FancyGifDialogWithRadioButton.Builder setNegativeBtnBackground(String nBtnColor) {
            this.nBtnColor = nBtnColor;
            return this;
        }

        //set Positive listener
        public FancyGifDialogWithRadioButton.Builder OnPositiveClicked(FancyGifDialogRadioListener pListener) {
            this.pListener = pListener;
            return this;
        }

        //set Negative listener
        public FancyGifDialogWithRadioButton.Builder OnNegativeClicked(FancyGifDialogRadioListener nListener) {
            this.nListener = nListener;
            return this;
        }

        public FancyGifDialogWithRadioButton.Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        public FancyGifDialogWithRadioButton.Builder setGifResource(int gifImageResource) {
            this.gifImageResource = gifImageResource;
            return this;
        }

        public FancyGifDialogWithRadioButton build() {
            TextView title1;
            ImageView iconImg;
            Button nBtn, pBtn;
            GifImageView gifImageView;
            final Dialog dialog;
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancel);
            dialog.setContentView(R.layout.fancygifdialog_radio_button);

            List<String> radioList = new ArrayList<>();

            for (int i = 0; i < radioButtonList.size(); i++) {

                radioList.add(radioButtonList.get(i));

            }
            radioButtonGroup = (RadioGroup) dialog.findViewById(R.id.radio_group);
            for (int i = 0; i < radioList.size(); i++) {
                RadioButton rb = new RadioButton(activity); // dynamically creating RadioButton and adding to RadioGroup.
                rb.setText(radioList.get(i));
                radioButtonGroup.addView(rb);
            }


            //getting resources
            title1 = (TextView) dialog.findViewById(R.id.title);

            nBtn = (Button) dialog.findViewById(R.id.negativeBtn);
            pBtn = (Button) dialog.findViewById(R.id.positiveBtn);
            gifImageView = dialog.findViewById(R.id.gifImageView);
            gifImageView.setImageResource(gifImageResource);

            title1.setText(title);

            if (positiveBtnText != null)
                pBtn.setText(positiveBtnText);
            if (negativeBtnText != null)
                nBtn.setText(negativeBtnText);
            if (pBtnColor != null) {
                GradientDrawable bgShape = (GradientDrawable) pBtn.getBackground();
                bgShape.setColor(Color.parseColor(pBtnColor));
            }
            if (nBtnColor != null) {
                GradientDrawable bgShape = (GradientDrawable) nBtn.getBackground();
                bgShape.setColor(Color.parseColor(nBtnColor));
            }
            if (pListener != null) {
                pBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pListener.OnSelectedRadioButton(radioButtonGroup);
                        dialog.dismiss();
                    }
                });
            } else {
                pBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }

                });
            }

            if (nListener != null) {
                nBtn.setVisibility(View.VISIBLE);
                nBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nListener.OnSelectedRadioButton(null);

                        dialog.dismiss();
                    }
                });
            }


            dialog.show();

            return new FancyGifDialogWithRadioButton(this);

        }
    }
}
