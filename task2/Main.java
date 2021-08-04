//package PLab.task2;

import java.nio.file.*;
import java.util.*;
import java.lang.Math;


class Main {
    static private float x, y, rad;
    static private float xn, yn;

    public static void main(String[] args) {
        try {
            List<String> list = Files.readAllLines(Paths.get(args[0]));
            parseFirstLine(list.get(0));
            x = xn;
            y = yn;
            rad = Float.parseFloat(list.get(1));

            list = Files.readAllLines(Paths.get(args[1]));
            for (String str : list) {
                findPoint(str);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void findPoint(String str) {
        parseFirstLine(str);

        if (xn < x - rad || xn > x + rad || yn < y - rad || yn > y + rad) {
            System.out.println(2);
        }
        else {
            float dist = (float)Math.sqrt( (x - xn) * (x - xn) + (y - yn) * (y - yn) );
            if (dist == rad) {
                System.out.println(0);
            }
            else if (dist < rad) {
                System.out.println(1);
            }
            else {
                System.out.println(2);
            }
        }
    }

    private static void parseFirstLine(String str) {
        int i = 0;
        for (; i < str.length() && str.charAt(i) == ' '; i++);
        for (; i < str.length() && str.charAt(i) != ' '; i++);
        xn = Float.parseFloat(str.substring(0, i));
        yn = Float.parseFloat(str.substring(i));
    }
}