# 금지된 단어를 제외한 가장 흔한 단어 찾기
# 전처리 작업(데이터 클랜징) 필요함 : 구두점, 대소문자 구별 안하기 등

import collections
import re

class strSolution:
    # 1. 리스트 컴프리핸션, counter 객체 사용
    def mostCommonWord(self, paragraph:str, banned:list[str]) -> str:
        words = [word for word in re.sub(r'[^\w]',' ',paragraph).lower().split()
                    if word not in banned]
        # 1) 딕셔녀리 활용 : int값이 자동으로 부여된다. -> 키 존재 유무 확인 필요 없음.
        '''
        counts = collections.defaultdict(int)
        for word in words:
            counts[word] += 1
        return max(counts, key=counts.get)
        '''
        # 2) counter 모듈 사용
        counts = collections.Counter(words) #[('ball', 2)]
        return counts.most_common(1)[0][0]

    
if __name__ == '__main__' :
    s = strSolution()
    print(s.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit", ["hit"]))