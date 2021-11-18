package com.example.smartfarmandroidapp.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.R;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.ViewHolder> {
    private List<Measurement> measurements;

    public MeasurementsAdapter(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public MeasurementsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.measurment_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull MeasurementsAdapter.ViewHolder holder, int position) {
       holder.value.setText("Value: " + measurements.get(position).getValue()+"");

            @SuppressLint("SimpleDateFormat") SimpleDateFormat changeFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        try {
            String strDateOutput = changeFormat.parse(measurements.get(position).getTime().replace("T", " ")).toString();
            holder.created_at.setText("Created at: " + strDateOutput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
@Override
    public int getItemCount() {
        return measurements.size();
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView value, created_at;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value_measurment_item_textfield);
            created_at = itemView.findViewById(R.id.measured_at_measurement_item_textfield);
        }
    }
}
