package pegasus.model.bean;

public class WeatherTable {
    private String date;
    private String up;
    private String down;
    private String morning;
    private String day;
    private String evening;
    private String cloud;
    private String symbol;
    private String night;

    public WeatherTable() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEvening() {
        return evening;
    }

    public void setEvening(String evening) {
        this.evening = evening;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", rise='" + up + '\'' +
                ", down='" + down + '\'' +
                ", morning='" + morning + '\'' +
                ", day='" + day + '\'' +
                ", evening='" + evening + '\'' +
                ", night='" + night + '\'' +
                ", cloud='" + cloud + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
