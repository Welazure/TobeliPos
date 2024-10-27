package me.welazure.tobelipos.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Reader {
    //    private BufferedReader br;
    private final Scanner sc;
    private boolean hasToReset = false;

    public Reader(InputStream in) {
//        br = new BufferedReader(new InputStreamReader(in));
        sc = new Scanner(in);
    }

    public String readLine() {
        try {
//            return br.readLine();
            if(hasToReset) {
                hasToReset = false;
            }
            return sc.nextLine();
        } catch (Exception e) {
            return "";
        }
    }

    public void reset() {
        try {
            sc.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    public String next(char delim) {
//        return sc.next(delim + "");
//        StringBuilder sb = new StringBuilder();
//        try {
//            int c = 0;
//            boolean done = false;
//            while((c = br.read()) != -1) {
//                if(c == delim) break;
//                sb.append((char)c);
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
    public String next() {
        hasToReset = true;
        return sc.next();
    }

    public double getDouble() {
        hasToReset = true;
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {

            }
        }
//        double toReturn = 0;
//        try {
//            double c = toReturn;
//            boolean neg = false;
//            boolean done = false;
//            while ((c = br.read()) != -1) {
//                if (c == '-') neg = true;
//                if (c < 48 || c > 57) continue;
//                c = c - 48;
//                double d = 0;
//                while ((d = br.read()) != -1) {
//                    if (!(d < 48 || d > 57)) c = c * 10 + (d - 48);
//                    else {
//                        done = true;
//                        break;
//                    }
//                }
//                if (done) break;
//            }
//            if (neg) toReturn = -c;
//            else toReturn = c;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return toReturn;
    }

    public int getInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

            }
        }
    }

    public float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(sc.nextLine());
            } catch (Exception e) {

            }
        }
    }
}
