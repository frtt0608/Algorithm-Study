import java.util.*;
import java.io.*;

// 상자넣기
public class B1965 {
    static int N;

    public static int getProperlyIndex(int[] table, int max, int box) {
        int min = 0;
        int mid = 0;

        while(min < max) {
            mid = (min + max)/2;

            if(table[mid] < box) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    public static int getMaxBoxCnt(int[] boxes) {
        int[] table = new int[N];
        table[0] = boxes[0];
        int idx=1, lowerIdx;

        for(int i=1; i<N; i++) {
            if(table[idx-1] < boxes[i]) {
                table[idx] = boxes[i];
                idx += 1;
            } else {
                lowerIdx = getProperlyIndex(table, idx, boxes[i]);
                table[lowerIdx] = boxes[i];
            }
        }

        return idx;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] boxes = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            boxes[i] = Integer.valueOf(st.nextToken());    
        }

        System.out.println(getMaxBoxCnt(boxes));
    }
}