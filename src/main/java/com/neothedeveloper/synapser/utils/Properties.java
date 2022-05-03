package com.neothedeveloper.synapser.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Properties {
    private final HashMap<String, String> m_properties;
    public Properties() {
        this.m_properties = new HashMap<>();
    }
    public void ReadProps(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] propItems = data.split("=", 2);
            m_properties.put(propItems[0], propItems.length != 2 ? "" : propItems[1]);
        }
    }

    public String GetProperty(String name) {
        return m_properties.get(name);
    }
    public void SetProperty(String name, String value) { m_properties.put(name, value); }

    public String[] GetAllProperties() {
        return m_properties.values().toArray(new String[0]);
    }
}
