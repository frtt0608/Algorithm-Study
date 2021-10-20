'''
두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 
아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.

예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 
"hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 
최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 
solution 함수를 작성해주세요.
'''



if __name__ == '__main__':
    def solution(begin, target, words):
        # 1. 1글자만 다른 경우의 수를 모두 찾아서
        # 2. DFS

        def find(word):
            result = []
            for w in words:
                cnt = 0
                for i in range(len(w)):
                    if word[i] == w[i]:
                        cnt += 1
                if len(word)-cnt == 1 :
                    result.append(w)
            return result

        result = []
        def dfs(word, cnt):
            visited.append(word)
            one_diff = find(word)
            # print(word, cnt, visited, one_diff)
            if word == target :
                result.append(cnt)
                return
            if not one_diff:
                return
            for w in one_diff:
                if w not in visited:
                    dfs(w, cnt+1)
                    visited.pop()
            return

        if target not in words or len(find(begin))==0:
            return 0
        else:
            visited = []
            dfs(begin, 0)
            return min(result)

print(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"]), 4)
print(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log"]), 0)