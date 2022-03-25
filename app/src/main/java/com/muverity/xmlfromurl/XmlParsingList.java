package com.muverity.xmlfromurl;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParsingList extends Activity {

    ArrayList<DataModel> list = new ArrayList<>();
    RecyclerView recyclerView;

    DataModel d = new DataModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parsing_list);

        recyclerView = findViewById(R.id.recyclerView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListAdapter adapter = new ListAdapter(list, this);
        RecyclerView.LayoutManager mLayoutManagaer = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManagaer);
        recyclerView.setAdapter(adapter);

        try {

            Intent intent = getIntent();
            String u = intent.getStringExtra("xmlUrl");
            Log.e(TAG, "onCreate: ----->" + u);
//            String u = "http://phorus.vtuner.com/setupapp/phorus/asp/browsexml/navXML.asp?gofile=LocationLevelFourCityUS-North%20America-New%20York-ExtraDir-1-Inner14&bkLvl=9237&mac=a8f58cd9758b710c43a7a63762e755947f83f0ad9194aa294bbaee55e0509e02&dlang=eng&fver=1.4.4.2299%20(20150604)&hw=CAP:%201.4.0.075%20MCU:%201.032%20BT:%200.002";

            URL url = new URL(u);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                Element fstElmnt = (Element) node;
                NodeList id = fstElmnt.getElementsByTagName("StationId");
                Element nameElement = (Element) id.item(0);
                id = nameElement.getChildNodes();
                d.id = Integer.parseInt(((Node) id.item(0)).getNodeValue());


                NodeList title = fstElmnt.getElementsByTagName("StationName");
                Element secElement = (Element) title.item(0);
                title = secElement.getChildNodes();
                d.title = ((Node) title.item(0)).getNodeValue();

                NodeList websiteList = fstElmnt.getElementsByTagName("Logo");
                Element thrElement = (Element) websiteList.item(0);
                websiteList = thrElement.getChildNodes();
                ((Node) websiteList.item(0)).getNodeValue();

            }
        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }

    }
}