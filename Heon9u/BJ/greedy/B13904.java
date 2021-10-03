import java.util.*;
import java.io.*;

// 과제
public class B13904 {

    public static int getMaxScoreInDays(List<Integer>[] works, int now, int maxDay) {
        int score = 0, target = 0;

        for(int day=now; day<maxDay+1; day++) {
            if(works[day] != null && !works[day].isEmpty() && score < works[day].get(0)) {
                target = day;
                score = works[day].get(0);
            }
        }

        if(target != 0) {
            works[target].remove(0);
        }

        return score;
    }

    public static int getTotalScoreInWorks(List<Integer>[] works, int maxDay) {
        int maxScore = 0;

        for(int day=maxDay; day>=1; day--) {
            maxScore += getMaxScoreInDays(works, day, maxDay);
        }

        return maxScore;
    }

    public static void sortWorks(List<Integer>[] works, int maxDay) {
        for(int i=1; i<maxDay+1; i++) {
            if(works[i] != null) {
                works[i].sort(Collections.reverseOrder());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int maxDay = 0;
        List<Integer>[] works = new ArrayList[1001];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, d);
            if(works[d] == null) {
                works[d] = new ArrayList<>();
            }

            works[d].add(w);
        }

        sortWorks(works, maxDay);
        int maxScore = getTotalScoreInWorks(works, maxDay);
        System.out.println(maxScore);
    }
}