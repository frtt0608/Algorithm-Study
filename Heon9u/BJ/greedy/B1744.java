import java.util.*;
import java.io.*;

// 양수: 두 숫자 중 1이 있으면 바로 더하기
// 남은 양수는 바로 더하기
// 음수는 작은 것부터 묶기
// 0이 나오면 남는 음수랑 묶어서 처리
public class B1744 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> plusNum = new ArrayList<>();
        List<Integer> minusNum = new ArrayList<>();
        int zeroCnt = 0;
        int num;

        for(int i=0; i<N; i++) {
            num = Integer.parseInt(br.readLine());
            if(num > 0) plusNum.add(num);
            else if(num < 0) minusNum.add(num);
            else zeroCnt += 1;
        }

        plusNum.sort(Collections.reverseOrder());
        Collections.sort(minusNum);

        BindNumber bindNumber = new BindNumber(plusNum, minusNum, zeroCnt);
        System.out.println(bindNumber.getMaxSummarizeNumber()); 
    }

    static class BindNumber {
        int zeroCnt;
        List<Integer> plusNum, minusNum;

        BindNumber(List<Integer> plusNum, List<Integer> minusNum, int zeroCnt) {
            this.plusNum = plusNum;
            this.minusNum = minusNum;
            this.zeroCnt = zeroCnt;
        }

        public int getMaxSummarizeNumber() {
            int total = summarizebindNumber(plusNum);
            total += summarizebindNumber(minusNum);

            return total;
        }

        public int summarizebindNumber(List<Integer> nums) {
            int total = 0;
            int size = nums.size();
            int num, nextNum;

            for(int i=0; i<size; i+=2) {
                num = nums.get(i);

                if(i < size && i+1 < size) {
                    nextNum = nums.get(i+1);

                    if(num == 1 || nextNum == 1)
                        total += num + nextNum;
                    else 
                        total += num * nextNum;

                } else {
                    if(num < 0 && zeroCnt > 0) continue;
                    
                    total += num;
                }
            }

            return total;
        }
    }
}