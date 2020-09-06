package pl.sda.final_project.weather;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FullWhetherInfo {

    private Coord coord;
    private Weather[] weather;
    private String base;
    private MainWeatherInfo main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private long id;
    private String name;
    private int cod;

    @Override
    public String toString() {
        return "Whether {" + main +
                ", wind=" + wind +
                '}';
    }
}
