package com.pm.app.utils;

import com.pm.app.utils.number.NumericHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class RegisterManager {

    protected RegisterManager() {
        this.defaultRegister();
    }

    public static String NUMERIC_HELPER = "NumericHelper";

    private static RegisterManager _Instance = null;

    private List<CustomPair<String, CustomUtility>> listOfUtils = new ArrayList<>();

    public static synchronized RegisterManager getInstance() {
        if (_Instance == null){
            _Instance = new RegisterManager();
        }

        return _Instance;
    }

    public boolean register(String key, CustomUtility value){
        return this.listOfUtils.add(new CustomPair<String, CustomUtility>(key, value));
    }

    public CustomUtility getUtility(String key){
        for( CustomPair<String, CustomUtility> pair : listOfUtils ){
            if(pair.getKey().equals(key)){
                return pair.getValue();
            }
        }

        return null;
    }

    public boolean unregister(String key){
        for( CustomPair<String, CustomUtility> pair : listOfUtils ){
            if(pair.getKey().equals(key)){
                listOfUtils.remove(pair);
                return true;
            }
        }

        return false;
    }

    private void defaultRegister(){
        register(NUMERIC_HELPER, new NumericHelper());
    }


}
