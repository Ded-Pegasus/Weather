package pegasus.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pegasus.model.Util;
import pegasus.model.bean.WeatherTable;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text city_name;

    @FXML
    private ImageView image;

    @FXML
    private Text error;

    @FXML
    private TableView<WeatherTable> tabl;

    @FXML
    private TableColumn<WeatherTable, String> date;

    @FXML
    private TableColumn<WeatherTable, String> rise;

    @FXML
    private TableColumn<WeatherTable, String> set;

    @FXML
    private TableColumn<WeatherTable, String> morning_temp;

    @FXML
    private TableColumn<WeatherTable, String> day_temp;

    @FXML
    private TableColumn<WeatherTable, String> evening_temp;


    @FXML
    private TableColumn<WeatherTable, String> night_temp;

    @FXML
    private TableColumn<WeatherTable, String> clouds;

    @FXML
    private TableColumn<WeatherTable, String> windSpeed;

    ObservableList<WeatherTable> list = FXCollections.observableArrayList(
            new WeatherTable(),
            new WeatherTable(),
            new WeatherTable(),
            new WeatherTable(),
            new WeatherTable(),
            new WeatherTable(),
            new WeatherTable()
    );

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            try {
                getWeatherOnSevenDays(getUserCity);
            } catch (IOException | ParserConfigurationException | SAXException e) {
                error.setText("City not found");
                city_name.setText("Unknown");
            }
        });
    }

    public void getWeatherOnSevenDays(String getUserCity) throws IOException, SAXException, ParserConfigurationException {
        if (!getUserCity.equals("")) {

            String page = Util.getUrlContent("https://api.openweathermap.org/data/2.5/forecast/daily?q=" + getUserCity + "&mode=xml&units=metric&cnt=7&appid=2f8796eefe67558dc205b09dd336d022");
            city_name.setText(getUserCity);
            Document document = Util.stringToDom(page);

            List<WeatherTable> weatherTableList = Util.getWeatherList(document);
            list = FXCollections.observableArrayList(weatherTableList);

            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            rise.setCellValueFactory(new PropertyValueFactory<>("up"));
            set.setCellValueFactory(new PropertyValueFactory<>("down"));
            morning_temp.setCellValueFactory(new PropertyValueFactory<>("morning"));
            day_temp.setCellValueFactory(new PropertyValueFactory<>("day"));
            evening_temp.setCellValueFactory(new PropertyValueFactory<>("evening"));
            night_temp.setCellValueFactory(new PropertyValueFactory<>("night"));
            clouds.setCellValueFactory(new PropertyValueFactory<>("symbol"));
            windSpeed.setCellValueFactory(new PropertyValueFactory<>("cloud"));

            tabl.setItems(list);
        }
    }
}