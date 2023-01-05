package com.example.case_study.Util;

import java.util.ArrayList;

public class Utils {
    public Utils() {}

    public static ArrayList<String> returnTechnologies(String technologies, String knowledge, String note) {
        ArrayList<String> result = new ArrayList<>();
        String separator = ", ";

        System.out.println(technologies + separator + knowledge + separator + note);

        String[] tech_split = new String[0], know_split = new String[0], note_split = new String[0];

        if (technologies.contains(separator)) {
            tech_split = technologies.split(separator);
        } if (knowledge != null) {
            know_split = knowledge.split(separator);
        } if (note != null) {
            note_split = note.split(separator);
        }

        for (int i = 0; i < tech_split.length; i++) {
            String res = tech_split[i];

            if (know_split.length > i) {
                res = res + separator + know_split[i];
            } if (note_split.length > i) {
                res = res + separator + note_split[i];
            }

            result.add(res);
        }

        return result;
    }
}
