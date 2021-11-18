package com.example.smartfarmandroidapp.MVVM.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.MVVM.Viewmodel.HistoryMeasurementViewModel;
import com.example.smartfarmandroidapp.R;
import com.example.smartfarmandroidapp.RecyclerViewAdapter.MeasurementsAdapter;

public class MeasurementHistoryFragment extends Fragment {
    private View measurementHistoryView;
    private Spinner dropdown;
    private SharedPreferences updateValuesPrefs;
    private SharedPreferences.Editor editor;
    private HistoryMeasurementViewModel historyMeasurementViewModel;
    private RecyclerView recyclerMeasurements;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        updateValuesPrefs = requireActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = updateValuesPrefs.edit();
        editor.putString("isNeededToUpdate", "false");
        editor.apply();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        measurementHistoryView = inflater.inflate(R.layout.fragment_measurement_history, container, false);
        initialize();
      //  historyMeasurmentViewModel.getMeasurements(SensorEnum.TEMPERATURE);
        return measurementHistoryView;
    }
    public void initialize(){
        recyclerMeasurements = measurementHistoryView.findViewById(R.id.recycler_view_fragment_measurement_history);
        historyMeasurementViewModel = new ViewModelProvider(this).get(HistoryMeasurementViewModel.class);
        dropdown = measurementHistoryView.findViewById(R.id.spinner_fragment_measurments_history);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.history_data_array, R.layout.support_simple_spinner_dropdown_item);
        setUpObserver();
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Temperature"))
                {
                    historyMeasurementViewModel.getHistoryMeasurements(SensorEnum.TEMPERATURE);
                }
                else if (parent.getItemAtPosition(position).toString().equals("Humidity"))
                {
                    historyMeasurementViewModel.getHistoryMeasurements(SensorEnum.HUMIDITY);
                }
                else{
                    historyMeasurementViewModel.getHistoryMeasurements(SensorEnum.C02);
                }
                System.out.println(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setUpObserver(){
        historyMeasurementViewModel.getHistoryMeasurements().observe(getViewLifecycleOwner(), list -> {
            recyclerMeasurements.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerMeasurements.hasFixedSize();


            MeasurementsAdapter measurementsAdapter = new MeasurementsAdapter(list);
            recyclerMeasurements.setAdapter(measurementsAdapter);
        });
    }

//    private void setUpObserver(){
//        historyMeasurmentViewMode
//    }
}
