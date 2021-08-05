import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("report.json"))) {
            List<String> listTests = Files.readAllLines(Paths.get(args[0]));
            List<String> listValues = Files.readAllLines(Paths.get(args[1]));

            for (int i = 0; i < listTests.size(); i++) {
                String str = listTests.get(i);
                int id = findId(str);

                if (id == -1) {
                    writer.write(str, 0, str.length());
                }
                else {
                    while (str.indexOf("\"value\"") == -1) {
                        writer.write(str, 0, str.length());
                        writer.newLine();
                        str = listTests.get(++i);
                    }
                    String tmp = findValue(listValues, id);
                    str = str.replaceAll("\"\"", tmp);
                    writer.write(str, 0, str.length());
                }
                if (i + 1 < listTests.size())
                    writer.newLine();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String findValue(List<String> list, int id) {
        String str = "\"\"";
        int i;

        for (i = 0; i < list.size(); i++) {
            if (id == findId(list.get(i))) {
                break;
            }
            if (i + 1 == list.size())
                return str;
        }
        str = list.get(i);
        while (str.indexOf("\"value\"") == -1) {
            str = list.get(++i);
        }
        String[] arr = str.split(":");
        arr = arr[1].split(" ");
        str = arr[arr.length - 1];

        return str;
    }

    private static int findId(String str) {
        int index = str.indexOf("\"id\"");
        int len;

        if (index == -1)
            return -1;

        while (index < str.length() && (str.charAt(index) < 48 || str.charAt(index) > 57)) {
            index++;
        }
        len = index;
        while (len < str.length() && str.charAt(len) >= 48 && str.charAt(len) <= 57) {
            len++;
        }

        return Integer.parseInt(str.substring(index, len));
    }
}