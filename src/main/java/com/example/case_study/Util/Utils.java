package com.example.case_study.Util;

import java.util.ArrayList;

public class Utils {
    public Utils() {}

    public static ArrayList<String> returnTechnologies(String technologies, String knowledge, String note) {
        ArrayList<String> result = new ArrayList<>();

        String separator = ", ";

        String[] tech_split = technologies.split(separator);
        String[] know_split = knowledge.split(separator);
        String[] note_split = note.split(separator);

        for (int i = 0; i < tech_split.length; i++) {
            result.add(tech_split[i] + separator + know_split[i] + separator + note_split[i]);
        }

        return result;
    }
}
