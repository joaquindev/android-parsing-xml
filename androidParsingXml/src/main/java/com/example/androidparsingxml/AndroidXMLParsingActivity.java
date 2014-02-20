package com.example.androidparsingxml;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by userbarna on 20/02/14.
 */
public class AndroidXMLParsingActivity extends ListActivity {

    static final String URL = "http://api.androidhive.info/pizza/?format=xml";
    static final String KEY_ITEM = "item";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromURL(URL);
        Document doc = parser.getDomElement(xml);
        NodeList nl = doc.getElementsByTagName(KEY_ITEM);

        //Looping through all item nodes
        for(int i=0; i<nl.getLength();i++){
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
            map.put(KEY_COST, parser.getValue(e, KEY_COST));
            map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
            menuItems.add(map);
        }

        //Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.list_item,
                new String[]{ KEY_NAME, KEY_DESC, KEY_COST},
                new int[] {R.id.name, R.id.description, R.id.cost});
        setListAdapter(adapter);

        //Selecting single ListView item
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.description)).getText().toString();

                //Starting a new Intent to send the data to the activity
                Intent in = new Intent(getApplicationContext(), AndroidXMLParsingActivity.class);
                in.putExtra(KEY_NAME, name);
                in.putExtra(KEY_COST, cost);
                in.putExtra(KEY_DESC, description);
                startActivity(in);
            }
        });


    }
}
