import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        StringBuilder sb = new StringBuilder();
        Camping camping;
        int L=-1, P=-1, V=-1, num=1;

        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            if(L==0 && P==0 && V==0)
                break;

            camping = new Camping(L, P, V);
            sb.append(camping.getPossibleCampingDay(num)).append("\n");
            
            num += 1;
        }

        System.out.println(sb.toString());
    }

    static class Camping {
        int L, P, V;

        Camping(int L, int P, int V) { 
            this.L = L;
            this.P = P;
            this.V = V;
        }

        public String getPossibleCampingDay(int num) {
            int day = V/P * L;
            day += (V%P < L ? V%P:L);

            return "Case "+num+": "+day;
        }
    }
}