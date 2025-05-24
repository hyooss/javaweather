package weatherresponse;

import java.util.*;

public class ForecastResponse {
    public List<ForecastItem> list;

    public static class ForecastItem{
        public Main main;
        public String dt_txt;
        public List<Weather> weather;

    }

    public static class Main{
        public double temp;
    }

    public static class Weather{
        public String description;
    }
}
