import java.util.*;
import java.io.*;

// 암호코드
public class B2011 {
    static int N;

    public static int getTotalCase(String input) {
        final int MODE = 1000000;
        int size = input.length();
        int[] caseCnt = new int[size+1];
        char[] nums = input.toCharArray();
        
        if(nums[0] == '0') return 0;

        caseCnt[0] = 1;
        caseCnt[1] = 1;
        int num, preNum;
        for(int i=2; i<size+1; i++) {
            num = nums[i-1] - '0';
            if(num >= 1 && num <= 9) {
                caseCnt[i] = (caseCnt[i] + caseCnt[i-1])%MODE;
            }

            preNum = num + (nums[i-2]-'0')*10;
            if(preNum >= 10 && preNum <= 26) {
                caseCnt[i] = (caseCnt[i] + caseCnt[i-2])%MODE;
            }
        }

        return caseCnt[size];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        String num = br.readLine();
        System.out.println(getTotalCase(num));

        br.close();
    }
}