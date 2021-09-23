import java.util.*;
import java.io.*;

public class B13904_ver2 {

    public static int getTotalScoreInWorks(List<Work> works) {
        int[] scores = new int[1001];
        int now;

        for(int i=0; i<works.size(); i++) {
            now = works.get(i).day;

            for(int day=now; day>0; day--) {
                if(scores[day] == 0) {
                    scores[day] = works.get(i).score;
                    break;
                }
            }
        }

        return Arrays.stream(scores).sum();
    }

    public static void objectSortWorks(List<Work> works) {
        Collections.sort(works, new Comparator<Work>() {
            @Override
            public int compare(Work work1, Work work2) {
                return work2.score - work1.score;
            }
        });
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Work> works = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            works.add(new Work(d, w));
        }

        objectSortWorks(works);

        int maxScore = getTotalScoreInWorks(works);
        System.out.println(maxScore);
    }

    static class Work {
        int day, score;

        Work(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }
}