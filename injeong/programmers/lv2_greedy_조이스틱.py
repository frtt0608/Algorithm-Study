def solution(name):
    # print(ord('N')-ord('A')+1, ord('Z')-ord('N')+1) #65, 90 : 26개 - mid:N(14)
    base = 'A'*len(name)
    make_name_idx = [x for x in range(len(name)) if base[x] != name[x]]
    print(make_name_idx)
    n = len(name)
    def ordering(data):
        result = []
        now, nxt, last = 0, 1, len(data)-1 
        while data:
            result.append(data[now])
            if data[nxt]-data[now] > (data[now]-data[last])%len(data):
                result.append(data[last])
                data.remove(data[now])
                data.remove(data[last-1])
                now = last-1
                last = nxt-1
                nxt = now-1
            else:
                result.append(data[nxt])
                data.remove(data[now])
                data.remove(data[nxt-1])
                now = 0
                nxt = 1
                last = last-2
        return result
    # print(ordering(make_name_idx))
    
    answer = 0
    for i in range(len(make_name_idx)):
        idx = make_name_idx[i]
        word = name[idx]
        if i==0:
            if idx!=0:
                answer += idx
        else:
            b_idx = make_name_idx[i-1]
            # print(idx-b_idx,1+(n-1-idx))
            answer += min(idx-b_idx, 1+(n-1-idx))
        print('a', answer)
        answer += min(ord(word)-ord('A'), ord('Z')-ord(word)+1)
        print('b', answer)
        
    return answer