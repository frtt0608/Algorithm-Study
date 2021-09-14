import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Time[] times = new Time[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[i] = new Time(start, end);
        }

        Arrays.sort(times, (Time t1, Time t2) -> (t1.start - t2.start));
        AssignClass assignClass = new AssignClass(N, times);

        int minCount = assignClass.getMinClassroomCount();
        System.out.println(minCount);
    }

    static class Time implements Comparable<Time> {
        int start, end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time time) {
            return this.end - time.end;
        }
    }

    static class AssignClass {
        int N;
        Time[] times;

        AssignClass(int N, Time[] times) {
            this.N = N;
            this.times = times;
        }

        public int getMinClassroomCount() {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.offer(times[0].end);

            for(int i=1; i<N; i++) {
                Time time = times[i];

                if(pq.peek() <= time.start) {
                    pq.poll();
                }

                pq.offer(time.end);
            }

            return pq.size();
        }
    }
}