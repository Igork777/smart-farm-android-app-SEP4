package com.example.smartfarmandroidapp.MVVM.View.Fragments;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartfarmandroidapp.MVVM.Viewmodel.MonitorViewModel;
import com.example.smartfarmandroidapp.R;

import static com.example.smartfarmandroidapp.NotificationChannel.App.CHANNEL_ID;

public class MonitorFragment extends Fragment {

    private MonitorViewModel monitorViewModel;

    private NotificationManagerCompat notificationManager;

    private TextView humidityValue, humidityPercentage, humidityTextView, temperatureValue, temperatureCelsius, temperatureTextView, co2progress;

    private ProgressBar co2ProgressBar;
    private SharedPreferences updateValuesPrefs;
    private SharedPreferences.Editor editor;

    private int progress = 50;
    private View monitorView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        monitorView = inflater.inflate(R.layout.fragment_monitor, container, false);

        updateValuesPrefs = requireActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = updateValuesPrefs.edit();
        editor.putString("isNeededToUpdate", "true");
        editor.apply();
        initializeFragmentsValues();
        return monitorView;
    }

    private void initializeFragmentsValues() {

        monitorViewModel = new ViewModelProvider(this).get(MonitorViewModel.class);

        humidityValue = monitorView.findViewById(R.id.humValue);
        temperatureValue = monitorView.findViewById(R.id.tempValue);

        humidityTextView = monitorView.findViewById(R.id.humidityTextView);
        temperatureTextView = monitorView.findViewById(R.id.temperatureTextView);

        humidityPercentage = monitorView.findViewById(R.id.humPercentage);
        temperatureCelsius = monitorView.findViewById(R.id.tempCelsius);

        co2progress = monitorView.findViewById(R.id.CO2progress);
        co2ProgressBar = monitorView.findViewById(R.id.progressBar);

        setUpObserver();

        updateProgressBar();

        // For passing notifications
        notificationManager = NotificationManagerCompat.from(requireActivity());
        monitorViewModel.fetchSettingsData();

    }

    // Notification Channel
    public void sendOnChannel(String warning){

        Notification notification = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentTitle("Alert")
                .setContentText(warning)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

    private void updateProgressBar() {
        Thread thread = new Thread(() -> {
            while (updateValuesPrefs.getString("isNeededToUpdate", "false").equals("true")) {
                monitorViewModel.fetchMeasurementData();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void setUpObserver() {
        monitorViewModel.getCO2Level().observe(getViewLifecycleOwner(), CO2Level -> {
            String progress = CO2Level + "";
            int progressDigit = (int) Double.parseDouble(CO2Level);
            co2ProgressBar.setProgress(progressDigit);
            co2progress.setText(progress);
        });
        monitorViewModel.getHumidity().observe(getViewLifecycleOwner(), humidityLevel -> {
            humidityValue.setText(humidityLevel);
        });

        monitorViewModel.getTemperature().observe(getViewLifecycleOwner(), temperatureLevel -> {
            temperatureValue.setText(temperatureLevel);
        });

        monitorViewModel.getWarning().observe(getViewLifecycleOwner(), warning ->{
            System.out.println("Warning: " + warning);
            sendOnChannel(warning);
        });

    }
}
