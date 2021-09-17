import java.util.*;
import java.io.*;

public class Main {

    public static int checkChangeStoT(String S, String T) {
        boolean isReverse = false;
        char target;

        while(S.length() != T.length()) {
            target = isReverse ? T.charAt(0) : T.charAt(T.length()-1);

            if(target == 'A') {
                T = isReverse ? T.substring(1, T.length()) : T.substring(0, T.length()-1);
            } else {
                T = isReverse ? T.substring(1, T.length()) : T.substring(0, T.length()-1);
                isReverse = !isReverse;
            }
        }

        if(isReverse) {
            StringBuilder sb = new StringBuilder();
            for(int i=T.length()-1; i>=0; i--) {
                sb.append(T.charAt(i));
            }

            T = sb.toString();
        }

        if(S.equals(T))
            return 1;

        return 0;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        String S = br.readLine();
        String T = br.readLine();

        int result = checkChangeStoT(S, T);
        System.out.println(result);
    }
}