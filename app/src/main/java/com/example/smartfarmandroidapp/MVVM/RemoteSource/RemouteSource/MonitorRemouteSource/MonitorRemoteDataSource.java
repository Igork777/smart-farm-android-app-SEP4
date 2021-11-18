package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.MonitorRemouteSource;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.EventsBusObject.LastMeasurementsEvent;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.Endpoints.LastMeasurementsAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.IMonitorGenerator;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.MonitorGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonitorRemoteDataSource implements IMonitorRemoteDataSource {

    private IMonitorGenerator monitorGenerator;

    public MonitorRemoteDataSource() {
        monitorGenerator = new MonitorGenerator();
    }


    public void getLastMeasurements(){
        LastMeasurementsAPI lastMeasurementsAPI = monitorGenerator.getLastMeasurementApi();
        Call<List<Measurement>> call = lastMeasurementsAPI.getLastMeasurements();
        call.enqueue(new Callback<List<Measurement>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if(response.isSuccessful())
                {
                    LastMeasurementsEvent lastMeasurementsEvent = new LastMeasurementsEvent();
                    lastMeasurementsEvent.setLastMeasurements(response.body());
                    EventBus.getDefault().post(lastMeasurementsEvent);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }

//    @Override
//    public void getCO2() {
//        CO2API co2API =monitorGenerator.getCo2API();
//        Call<CO2> call = co2API.getCO2(3);
//        call.enqueue(new Callback<CO2>() {
//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<CO2> call, Response<CO2> response) {
//                if (response.isSuccessful()) {
//                    CO2Event event = new CO2Event();
//                    System.out.println(response.body()+"");
//                    event.setCO2(response.body().getValue()+"");
//                    EventBus.getDefault().post(event);
//                }
//            }
//
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<CO2> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    public void getHumidity() {
//        HumidityAPI humidityAPI = monitorGenerator.getHumidityApi();
//        Call<Humidity> call = humidityAPI.getHumidity(2);
//        call.enqueue(new Callback<Humidity>() {
//            @Override
//            public void onResponse(Call<Humidity> call, Response<Humidity> response) {
//                if(response.isSuccessful())
//                {
//                    HumidityEvent event = new HumidityEvent();
//                    event.setHumidity(response.body().getValue()+"");
//                    EventBus.getDefault().post(event);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Humidity> call, Throwable t) {
//
//            }
//        });
//    }
//
//    @Override
//    public void getTemperature() {
//        TemperatureAPI temperatureAPI = monitorGenerator.getTemperatureAPI();
//        Call<Temperature> call = temperatureAPI.getTemperature(1);
//        call.enqueue(new Callback<Temperature>() {
//            @Override
//            public void onResponse(Call<Temperature> call, Response<Temperature> response) {
//                if(response.isSuccessful())
//                {
//                    TemperatureEvent event = new TemperatureEvent();
//                    event.setTemperature(response.body().getValue()+"");
//                    EventBus.getDefault().post(event);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Temperature> call, Throwable t) {
//
//            }
//        });
  //  }
}
