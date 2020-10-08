package com.liaojh.towercrane.UI;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaojh.towercrane.Activity.BaseActivity;
import com.liaojh.towercrane.Activity.MainActivity;
import com.liaojh.towercrane.R;

import com.liaojh.towercrane.Data.TowerCraneLiftData;
import com.liaojh.towercrane.Data.TowerCraneRunData;

public class UIUpDownRunInfo implements InterfaceUI {
    private Activity activity;

    private ImageView imageStatus, imageBrakeCheck, imageGeneratrix, imageOutputVoltage, imageOutputElectricity, imageRunFrequency,
            imageTurnSpeed, imageTemperature;

    private TextView textStatus, textBrakeCheck, textGeneratrix, textOutputVoltage, textOutputElectricity, textRunFrequency,
            textTurnSpeed, textTemperature;

    private TextView textUpSlowDown, textDownSlowDown, textUpDestination, textDownDestination, textLoad100, textLoad90, textLoad50,
            textLoad25, textBrakeUnit, textBrake, textRunSlowDown, textBrakeOpen, textLimitSwitch;

    private TextView textUpDownCommunicationStatus;

    private void updateImageWaringInfo(Boolean isWaring, ImageView imageView, TextView textView) {
        if (isWaring) {
            imageView.setImageDrawable(activity.getDrawable(R.drawable.yibiaopan_red));
            textView.setTextColor(activity.getResources().getColor(R.color.color_label_red));
        } else {
            imageView.setImageDrawable(activity.getDrawable(R.drawable.yibiaopan_blue));
            textView.setTextColor(activity.getResources().getColor(R.color.color_label_blue));
        }
    }

    private void updateTextWaringInfo(Boolean isWaring, TextView view) {
        if (isWaring) {
            view.setTextColor(activity.getResources().getColor(R.color.color_label_red));
            view.setText(activity.getString(R.string.text_waring));
        } else {
            view.setTextColor(activity.getResources().getColor(R.color.color_label_blue));
            view.setText(activity.getString(R.string.text_normal));
        }
    }

    @Override
    public void onUICreate(MainActivity activityIn) {
        activity = activityIn;

        imageStatus = activity.findViewById(R.id.image_up_down_status);
        imageBrakeCheck = activity.findViewById(R.id.image_up_down_brake_check);
        imageGeneratrix = activity.findViewById(R.id.image_up_down_generatrix);
        imageOutputVoltage = activity.findViewById(R.id.image_up_down_output_voltage);
        imageOutputElectricity = activity.findViewById(R.id.image_up_down_output_electricity);
        imageRunFrequency = activity.findViewById(R.id.image_up_down_run_frequency);
        imageTurnSpeed = activity.findViewById(R.id.image_up_down_turn_speed);
        imageTemperature = activity.findViewById(R.id.image_up_down_temperature);

        textStatus = activity.findViewById(R.id.text_up_down_status);
        textBrakeCheck = activity.findViewById(R.id.text_up_down_brake_check);
        textGeneratrix = activity.findViewById(R.id.text_up_down_generatrix);
        textOutputVoltage = activity.findViewById(R.id.text_up_down_output_voltage);
        textOutputElectricity = activity.findViewById(R.id.text_up_down_output_electricity);
        textRunFrequency = activity.findViewById(R.id.text_up_down_run_frequency);
        textTurnSpeed = activity.findViewById(R.id.text_up_down_turn_speed);
        textTemperature = activity.findViewById(R.id.text_up_down_temperature);

        textUpSlowDown = activity.findViewById(R.id.text_up_down_up_slow_down);
        textDownSlowDown = activity.findViewById(R.id.text_up_down_down_slow_down);
        textUpDestination = activity.findViewById(R.id.text_up_down_up_destination);
        textDownDestination = activity.findViewById(R.id.text_up_down_down_destination);
        textLoad100 = activity.findViewById(R.id.text_up_down_load_100);
        textLoad90 = activity.findViewById(R.id.text_up_down_load_90);
        textLoad50 = activity.findViewById(R.id.text_up_down_load_50);
        textLoad25 = activity.findViewById(R.id.text_up_down_load_25);
        textBrakeUnit = activity.findViewById(R.id.text_up_down_brake_unit);
        textBrake = activity.findViewById(R.id.text_up_down_brake);
        textRunSlowDown = activity.findViewById(R.id.text_up_down_run_slow_down);
        textBrakeOpen = activity.findViewById(R.id.text_up_down_brake_open);
        textLimitSwitch = activity.findViewById(R.id.text_up_down_limit_switch);

        textUpDownCommunicationStatus = activity.findViewById(R.id.text_up_down_waring);
    }

    @Override
    public void onUIStart() {

    }

    @Override
    public void onUIDestroy() {

    }

    @Override
    public void onTowerCraneRunDateUpdate(TowerCraneRunData towerCraneRunData) {
        TowerCraneLiftData towerCraneLiftData = towerCraneRunData.towerCraneLiftData;

        updateImageWaringInfo(towerCraneLiftData.judgeStatusIsWaring(), imageStatus, textStatus);
        updateImageWaringInfo(towerCraneLiftData.judgeBrakeCheckIsWaring(), imageBrakeCheck, textBrakeCheck);
        updateImageWaringInfo(towerCraneLiftData.judgeGeneratrixVoltageIsWaring(), imageGeneratrix, textGeneratrix);
        updateImageWaringInfo(towerCraneLiftData.judgeOutputVoltageIsWaring(), imageOutputVoltage, textOutputVoltage);
        updateImageWaringInfo(towerCraneLiftData.judgeOutputElectricityIsWaring(), imageOutputElectricity, textOutputElectricity);
        updateImageWaringInfo(towerCraneLiftData.judgeRunFrequencyIsWaring(), imageRunFrequency, textRunFrequency);
        updateImageWaringInfo(towerCraneLiftData.judgeTurnSpeedIsWaring(), imageTurnSpeed, textTurnSpeed);
        updateImageWaringInfo(towerCraneLiftData.judgeTemperatureIsWaring(), imageTemperature, textTemperature);

        textStatus.setText(towerCraneLiftData.getStatusStr());
        textBrakeCheck.setText(towerCraneLiftData.getBrakeCheckStr());
        textGeneratrix.setText(towerCraneLiftData.getGeneratrixVoltageStr());
        textOutputVoltage.setText(towerCraneLiftData.getOutputVoltageStr());
        textOutputElectricity.setText(towerCraneLiftData.getOutputElectricityStr());
        textRunFrequency.setText(towerCraneLiftData.getRunFrequencyStr());
        textTurnSpeed.setText(towerCraneLiftData.getTurnSpeedStr());
        textTemperature.setText(towerCraneLiftData.getTemperatureStr());

        updateTextWaringInfo(towerCraneLiftData.judgeUpSlowDownIsWaring(), textUpSlowDown);
        updateTextWaringInfo(towerCraneLiftData.judgeDownSlowDownIsWaring(), textDownSlowDown);
        updateTextWaringInfo(towerCraneLiftData.judgeUpDestinationIsWaring(), textUpDestination);
        updateTextWaringInfo(towerCraneLiftData.judgeDownDestinationIsWaring(), textDownDestination);
        updateTextWaringInfo(towerCraneLiftData.judgeLoadIsWaring_100(), textLoad100);
        updateTextWaringInfo(towerCraneLiftData.judgeLoadIsWaring_90(), textLoad90);
        updateTextWaringInfo(towerCraneLiftData.judgeLoadIsWaring_50(), textLoad50);
        updateTextWaringInfo(towerCraneLiftData.judgeLoadIsWaring_25(), textLoad25);
        updateTextWaringInfo(towerCraneLiftData.judgeBrakeUnitIsWaring(), textBrakeUnit);
        updateTextWaringInfo(towerCraneLiftData.judgeBrakeIsWaring(), textBrake);
        updateTextWaringInfo(towerCraneLiftData.judgeRunSlowDownIsWaring(), textRunSlowDown);
        updateTextWaringInfo(towerCraneLiftData.judgeBrakeOpenIsWaring(), textBrakeOpen);
        updateTextWaringInfo(towerCraneLiftData.judgeLimitSwitchIsWaring(), textLimitSwitch);

        if (towerCraneLiftData.judgeCommunicationIsWaring()) {
            textUpDownCommunicationStatus.setVisibility(View.VISIBLE);
        } else {
            textUpDownCommunicationStatus.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

    }
}