package com.app.setayesh.englishjokes.Model.Local;

import android.arch.persistence.room.TypeConverter;

import com.app.setayesh.englishjokes.Model.pojo.Value;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JokeConverter {

    @TypeConverter
    public List<Value> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Value>>() {
        }.getType();
        List<Value> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromCountryLangList(List<Value> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Value>>() {
        }.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }
}