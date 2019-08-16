package com.example.movie.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

// Class to marshal/unmarshal a local date to xml format
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
