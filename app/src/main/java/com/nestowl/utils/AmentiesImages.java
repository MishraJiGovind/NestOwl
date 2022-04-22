package com.nestowl.utils;

import java.util.HashMap;

public class AmentiesImages {
    public static final String BULB = "light-bulb.svg";
    public static final String FAN = "fan.svg";
    public static final String WATER_FILTER = "water-filter.svg";
    public static final String FRIDGE = "fridge.svg";
    public static final String MICROWAVES = "microwave.svg";
    public static final String VENTILATION = "ventilation.svg";
    public static final String TV = "television.svg";
    public static final String AC = "air-conditioner.svg";
    public static final String BED = "bed.svg";
    public static final String CLOSET = "closet.svg";
    public static final String GEYSER = "geyser.svg";
    public static final String SOFA = "sofa.svg";
    public static final String WASHING_MACHINE = "washing-machine.svg";
    public static final String STOVE = "stove.svg";
    public static final String CHIMNEY = "chimney.svg";
    public static final String DINING_TABLE = "dinning-table.svg";
    public static final String CURTAINS = "curtains.svg";
    public static final String KITCHEN = "kitchen.svg";
    public static final String ELEVATOR = "elevator.svg";
    public static final String BATTERY = "battery.svg";
    public static final String WIFI = "wifi.svg";
    public static final String WEIGHT_LIFTING = "weightlifting.svg";

    public static HashMap<String,String> getLogos(){
        HashMap<String,String>logos=new HashMap<>();
        logos.put("light",AmentiesImages.BULB);
        logos.put("exhaustfan",AmentiesImages.VENTILATION);
        logos.put("sofa",AmentiesImages.SOFA);
        logos.put("washingmachine",AmentiesImages.WASHING_MACHINE);
        logos.put("stove",AmentiesImages.STOVE);
        logos.put("chimeny",AmentiesImages.CHIMNEY);
        logos.put("dinningtable",AmentiesImages.DINING_TABLE);
        logos.put("curtains",AmentiesImages.CURTAINS);
        logos.put("modularkitchen",AmentiesImages.KITCHEN);
        logos.put("lift",AmentiesImages.ELEVATOR);
        logos.put("powerbackup",AmentiesImages.BATTERY);
        logos.put("wi-fi",AmentiesImages.WIFI);
        logos.put("gymnasium",AmentiesImages.WEIGHT_LIFTING);
        logos.put("microwave",AmentiesImages.MICROWAVES);
        logos.put("tv",AmentiesImages.TV);
        logos.put("ac",AmentiesImages.AC);
        logos.put("beds",AmentiesImages.BED);
        logos.put("gyser",AmentiesImages.GEYSER);
//        logos.put("ventilation",AmentiesImages.VENTILATION);
        logos.put("wardrobe",AmentiesImages.CLOSET);
        logos.put("waterpurifier",AmentiesImages.WATER_FILTER);
        logos.put("fridge",AmentiesImages.FRIDGE);
        logos.put("fan",AmentiesImages.FAN);

        return logos;
    }

}
