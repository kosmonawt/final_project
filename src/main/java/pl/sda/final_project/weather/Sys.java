package pl.sda.final_project.weather;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sys {

    private int type;
    private long id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;

}
