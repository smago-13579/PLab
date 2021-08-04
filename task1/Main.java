

class Main {
    public static void main(String[] args) {
        try {
            String result = "";
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);

            if (n <= 0 || m <= 0)
                throw new RuntimeException("Invalid arguments");

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }

            int i = 0;
            while (true) {
                result += arr[i];
                i += (m - 1);
                while (i > n)
                    i -= n;
                if (i == n || i == 0)
                    break;
            }
            System.out.println(result);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}