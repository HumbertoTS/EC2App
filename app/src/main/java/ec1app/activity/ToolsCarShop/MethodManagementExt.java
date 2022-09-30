/*
 * Copyright 2014 KC Ochibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *  The "‚‗‚" character is not a comma, it is the SINGLE LOW-9 QUOTATION MARK unicode 201A
 *  and unicode 2017 that are used for separating the items in a list.
 */

package ec1app.activity.ToolsCarShop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import ec1app.activity.model.ProductosModel;


public class MethodManagementExt {

    private SharedPreferences preferences;

    public MethodManagementExt(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    // Getters

    /**
     * Get String value from SharedPreferences at 'key'. If key not found, return ""
     * @param key SharedPreferences key
     * @return String value at 'key' or "" (empty String) if key not found
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    /**
     * Get parsed ArrayList of String from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return ArrayList of String
     */
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }

    //almacena la lista de productos
    public ArrayList<ProductosModel> getListObject(String key){
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<ProductosModel> playerList =  new ArrayList<ProductosModel>();

        for(String jObjString : objStrings){
            ProductosModel player  = gson.fromJson(jObjString,  ProductosModel.class);
            playerList.add(player);
        }
        return playerList;
    }

    // Put methods

    /**
     * Put ArrayList of String into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param stringList ArrayList of String to be added
     */
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    public void putListObject(String key, ArrayList<ProductosModel> playerList){
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(ProductosModel player: playerList){
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }




    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param key the pref key to check
     */
    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param value the pref value to check
     */
    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }
}