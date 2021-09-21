import java.util.*;
import java.io.*;

// 강의실 배정
// 비교 대상인 강의 시작시간과 현재 강의에 최소 종료시간을 비교하여 갭을 최소로,
// 입력값을 시작시간으로 오름차순 정렬이 관건.
// 우선순위 큐
public class B11000 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ClassTime[] classTimes = new ClassTime[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classTimes[i] = new ClassTime(start, end);
        }

        Arrays.sort(classTimes, (ClassTime time1, ClassTime time2) -> (time1.start - time2.start));
        
        ClassAssign classAssign = new ClassAssign(N, classTimes);
        System.out.println(classAssign.getClassroomMinCount());
    }

    static class ClassTime {
        int start, end;

        ClassTime(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class ClassAssign {
        int N;
        ClassTime[] classTimes;

        ClassAssign(int N, ClassTime[] classTimes) {
            this.N = N;
            this.classTimes = classTimes;
        }

        public int getClassroomMinCount() {
            PriorityQueue<Integer> timePQ = new PriorityQueue<>();
            timePQ.offer(classTimes[0].end);

            for(int i=1; i<N; i++) {
                ClassTime time = classTimes[i];

                if(timePQ.peek() <= time.start) {
                    timePQ.poll();
                }

                timePQ.offer(time.end);
            }
            
            return timePQ.size();
        }
    }
}