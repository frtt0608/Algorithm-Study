import java.util.*;
import java.io.*;

// 배
// 리스트와 정렬, 배열은 이중 반복문x
// 리스트의 제거와 인덱스 관리로 최대한 반복횟수 줄이기
public class B1092 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] cranes = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(cranes);
        boxes.sort(Collections.reverseOrder());
        Ship ship = new Ship(N, M, cranes);
        int movingTime = ship.getMovingTime(boxes);

        System.out.println(movingTime);
    }

    static class Ship {
        int N, M;
        int[] cranes;

        Ship(int N, int M, int[] cranes) {
            this.N = N;
            this.M = M;
            this.cranes = cranes;
        }

        public int getMovingTime(List<Integer> boxes) {
            int movingTime = 0;
            
            if(cranes[N-1] < boxes.get(0))
                return -1;

            while(!boxes.isEmpty()) {
                int idx = N-1;

                for(int i=0; i<boxes.size();) {
                    if(idx == -1) break;

                    if(boxes.get(i) <= cranes[idx]) {
                        boxes.remove(i);
                        idx -= 1;
                    } else {
                        i += 1;
                    }
                }

                movingTime += 1;
            }

            return movingTime;
        }
    }
}