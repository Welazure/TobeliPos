package me.welazure.tobelipos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {
    private BufferedReader br;
    public Reader(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
    }
    public String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }
    public String next(char delim) {
        StringBuilder sb = new StringBuilder();
        try {
            int c = 0;
            boolean done = false;
            while((c = br.read()) != -1) {
                if(c == delim) break;
                sb.append((char)c);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public String next() {
        return next(' ');
    }

    public double nextDouble() {
        double toReturn = 0;
        try {
            double c = toReturn;
            boolean neg = false;
            boolean done = false;
            while ((c = br.read()) != -1) {
                if (c == '-') neg = true;
                if (c < 48 || c > 57) continue;
                c = c - 48;
                double d = 0;
                while ((d = br.read()) != -1) {
                    if (!(d < 48 || d > 57)) c = c * 10 + (d - 48);
                    else {
                        done = true;
                        break;
                    }
                }
                if (done) break;
            }
            if (neg) toReturn = -c;
            else toReturn = c;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
    public int getInt() {
        return (int) nextDouble();
    }
    public float getFloat() {
        return (float) nextDouble();
    }
}
