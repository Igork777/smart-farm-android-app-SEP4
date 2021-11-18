package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.MonitorRemouteSource;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.Endpoints.LastMeasurementsAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.MonitorGenerator;

import junit.framework.TestCase;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonitorRemoteDataSourceTest extends TestCase {
    private MonitorGenerator monitorGenerator = new MonitorGenerator();

    public void testGetLastMeasurements() {
        LastMeasurementsAPI lastMeasurementsAPI = monitorGenerator.getLastMeasurementApi();
        Call<List<Measurement>> call = lastMeasurementsAPI.getLastMeasurements();
        call.enqueue(new Callback<List<Measurement>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if(response.isSuccessful()) {
                    assertEquals(false, response.body().isEmpty());
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }
}