import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        WordMath wordMath = new WordMath(N, words);
        wordMath.getTotalAlpha();

        System.out.println(wordMath.getMaxTotalSum());
    }

    static class WordMath {
        int N;
        String[] words;
        int[] alphaCount = new int[26];

        WordMath(int N, String[] words) {
            this.N = N;
            this.words = words;
        }

        public void getTotalAlpha() {
            int ten, size, idx;
            for(String word: words) {
                size = word.length();
                ten = (int) Math.pow(10, size-1);
                for(char chr: word.toCharArray()) {
                    idx = chr - 'A';
                    alphaCount[idx] += ten;
                    ten /= 10;
                }
            }
        }

        public long getMaxTotalSum() {
            long sum = 0;
            int num = 9;
            Arrays.sort(alphaCount);

            for(int i=25; i>=0; i--) {
                if(alphaCount[i] == 0) break;
                
                sum += alphaCount[i] * num;
                num -=1;
            }

            return sum;
        }
    }
}