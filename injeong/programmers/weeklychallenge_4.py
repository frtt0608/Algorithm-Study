'''
개발자가 사용하는 언어와 언어 선호도를 입력하면,
그에 맞는 직업군을 추천해주는 알고리즘을 개발하려고 합니다.

개발자가 사용하는 언어의 언어 선호도 x 직업군 언어 점수의 총합이 가장 높은 직업군을 return.
총합이 같은 직업군이 여러 개일 경우, 이름이 사전 순으로 가장 빠른 직업군을 return.
'''

def solution(table, languages, preference):
    # table ; "직업군 5점언어 4점언어 3점언어 2점언어 1점언어"
    # language ; "JAVA", "JAVASCRIPT", "C", "C++" ,"C#" , "SQL", "PYTHON", "KOTLIN", "PHP" 중 한 개 이상
    # preference ; 1 ≤ preference의 원소 ≤ 10
    languages_list = ["JAVA", "JAVASCRIPT", "C", "C++" ,"C#" , "SQL", "PYTHON", "KOTLIN", "PHP"]
    languages_dict = {}
    table_list = sorted(["SI", "CONTENTS", "HARDWARE", "PORTAL", "GAME"]) # 사전순
    result_list = [0]*5

    table = [list(x.split(' ')) for x in table]
    for i in range(5):
        job = table[i][0]
        job_idx = table_list.index(job)
        for j in range(1, 6):
            lang = table[i][j]
            score = 6-j
            # 언어별 직군 점수
            if lang not in languages_dict.keys():
                languages_dict[lang] = [0]*5
            languages_dict[lang][job_idx] = score
            # 직군별 해당 언어 점수(선호도 있는 언어만)
            if lang in languages:
                try:
                    idx = languages.index(lang)
                    result_list[job_idx] += score*preference[idx]
                except:
                    continue

    answer = table_list[result_list.index(max(result_list))]
    return answer

print(solution(["SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"],
["PYTHON", "C++", "SQL"], [7, 5, 5]), "HARDWARE")
print(solution(["SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"],
["JAVA", "JAVASCRIPT"], [7, 5]), "PORTAL")