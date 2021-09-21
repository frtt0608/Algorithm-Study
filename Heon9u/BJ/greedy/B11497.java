import java.util.*;
import java.io.*;

// 통나무 건너뛰기
// 정렬과 투 포인터
public class B11497 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] heights = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            JumpLog jumpLog = new JumpLog(N, heights);
            jumpLog.setLogs();
            sb.append(jumpLog.getMinGrade()+"\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static class JumpLog {
        int N;
        int[] heights, logs;

        JumpLog(int N, int[] heights) {
            this.N = N;
            this.heights = heights;
        }

        public void setLogs() {
            int left = 0;
            int right = N-1;
            logs = new int[N];

            Arrays.sort(heights);
            for(int i=0; i<N; i++) {
                if(i%2 == 0) logs[left++] = heights[i];
                else logs[right--] = heights[i];
            }
        }

        public int getMinGrade() {
            int grade = Math.abs(logs[N-1] - logs[0]);
            for(int i=0; i<N-1; i++) {
                grade = Math.max(grade, Math.abs(logs[i] - logs[i+1]));
            }

            return grade;
        }
    }
}