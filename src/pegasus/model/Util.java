package pegasus.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pegasus.model.bean.WeatherTable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Document stringToDom(String xmlSource)
            throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }

    public static String getUrlContent(String urlAdress){
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("City not found");
        }
        return content.toString();
    }

    public static List<WeatherTable> getWeatherList(Document document) {



        NodeList timesNodeList = document.getElementsByTagName("time");

        List<WeatherTable> weatherTableList = new ArrayList<>();

        for (int i=0; i< timesNodeList.getLength(); i++){
            if (timesNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element weatherElerment = (Element) timesNodeList.item(i);

                WeatherTable weatherTable = new WeatherTable();
                weatherTable.setDate(((Element) timesNodeList.item(i)).getAttribute("day"));

                NodeList childNodes = weatherElerment.getChildNodes();

                for (int j=0; j<childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()) {
                            case "sun": {
                                weatherTable.setDown(childElement.getAttribute("set"));
                                weatherTable.setUp(childElement.getAttribute("rise"));
                            } break;
                            case "set": {
                                weatherTable.setUp(childElement.getTextContent());
                            }break;
                            case "temperature": {
                                weatherTable.setDay(childElement.getAttribute("day"));
                                weatherTable.setEvening(childElement.getAttribute("eve"));
                                weatherTable.setMorning(childElement.getAttribute("morn"));
                                weatherTable.setNight(childElement.getAttribute("night"));
                            }break;
                            case "clouds": {
                                weatherTable.setCloud(childElement.getAttribute("value"));
                            }break;
                            case "symbol": {
                                weatherTable.setSymbol(childElement.getAttribute("name"));
                            }break;
                        }
                    }
                }
                weatherTableList.add(weatherTable);
            }
        }
        return weatherTableList;
    }
}
