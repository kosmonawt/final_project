package pl.sda.final_project.weather;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainWeatherInfo {

    private double temp;
    private int pressure;
    private double feels_like;
    private int humidity;
    private double tempMin;
    private double tempMax;


    @Override
    public String toString() {
        return "temp= " + temp + " C" +
                ", pressure= " + pressure + " Pa" +
                ", humidity= " + humidity;
    }
}
