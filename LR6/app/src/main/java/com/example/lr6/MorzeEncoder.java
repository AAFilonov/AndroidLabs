package com.example.lr6;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.R)
public class MorzeEncoder {
    private  HashMap<Character, String> mapToMorze = new HashMap<>();

    public MorzeEncoder() {
        mapToMorze.put('a', "*-");
        mapToMorze.put('b', "-***");
        mapToMorze.put('c', "-*-*");
        mapToMorze.put('d', "-**");
        mapToMorze.put('e', "*");
        mapToMorze.put('f', "**-*");
        mapToMorze.put('g', "--*");
        mapToMorze.put('h', "****");
        mapToMorze.put('i', "**");
        mapToMorze.put('j', "*---");
        mapToMorze.put('k', "-*-");
        mapToMorze.put('l', "*-**");
        mapToMorze.put('m', "--");
        mapToMorze.put('n', "-*");
        mapToMorze.put('o', "---");
        mapToMorze.put('p', "*--*");
        mapToMorze.put('q', "--*-");
        mapToMorze.put('r', "*-*");
        mapToMorze.put('s', "***");
        mapToMorze.put('t', "-");
        mapToMorze.put('u', "**-");
        mapToMorze.put('v', "***-");
        mapToMorze.put('w', "*--");
        mapToMorze.put('x', "-**-");
        mapToMorze.put('y', "-*--");
        mapToMorze.put('z', "--**");
    }


    public  String encode(Character character) {
        if (!mapToMorze.containsKey(character))
            throw new RuntimeException("No such letter in coding table");
        return mapToMorze.getOrDefault(character, "");
    }
}
