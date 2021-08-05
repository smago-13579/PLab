import java.nio.file.*;
import java.util.*;

public class Main {
    static private ArrayList<Integer> nums = new ArrayList<>();

    public static void main(String[] args) {
        try {
            int iter = Integer.MAX_VALUE;

            List<String> list = Files.readAllLines(Paths.get(args[0]));

            for (String str : list) {
                if (str.length() != 0)
                    nums.add(parseFirstLine(str));
            }

            for (int i = 0; i < nums.size(); i++) {
                int tmp = countIterations(nums.get(i));

                if (iter > tmp)
                    iter = tmp;

            }

            System.out.println(iter);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int countIterations(int num) {
        int count = 0;

        for (int i = 0; i < nums.size(); i++) {
            int tmp = nums.get(i);

            if (tmp == num) {
                continue;
            }
            else if (tmp < num) {
                count += num - tmp;
            }
            else if (tmp > num) {
                count += tmp - num;
            }
        }

        return count;
    }

    private static Integer parseFirstLine(String str) {
        int i = 0;
        for (; i < str.length() && str.charAt(i) == ' '; i++);

        return Integer.parseInt(str.substring(i));
    }
}
