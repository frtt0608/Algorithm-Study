import java.util.*;
import java.io.*;


// A -> B
public class B16953 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        AtoB atob = new AtoB();
        System.out.println(atob.changeAtoB(A, B));
    }

    static class AtoB {
        int A, B;

        public int changeAtoB(int A, int B) {
            int minCount = 1;

            while(A != B) {

                if(B < A) {
                    minCount = -1;
                    break;
                }

                if(B%2 == 0) {
                    B /= 2;
                } else {
                    String strB = Integer.toString(B);
                    if(strB.charAt(strB.length()-1) == '1') {
                        B = Integer.parseInt(strB.substring(0, strB.length()-1));
                    } else {
                        minCount = -1;
                        break;
                    }
                }
 
                minCount += 1;
            }

            return minCount;
        }
    }
}