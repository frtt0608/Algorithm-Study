'''
입장 : Enter [유저 아이디] [닉네임]
퇴장 : Leave [유저 아이디]
변경 : Change [유저 아이디] [닉네임]
Question) 관리자가 마지막에 보는 메시지를 출력해라.
'''

def solution(record):
    # 입장, 퇴장, 변경 기록 + 현재 닉네임(update)
    users = {}
    orders = []
    for col in record:
        x = col.split(' ')
        if len(x) == 2:
            status, user_id = x
        else:
            status, user_id, nickname = x
        if users.get(user_id)==None:
            users[user_id] = {
                'nickname' : nickname,
                'status' : []
            }
        if status == 'Enter':
            orders.append(user_id)
            users[user_id]['nickname'] = nickname
            users[user_id]['status'].append("들어왔습니다.")
        elif status == 'Leave':
            orders.append(user_id)
            users[user_id]['status'].append("나갔습니다.")
        else:
            users[user_id]['nickname'] = nickname

    # print(users)
    # print(orders)

    answer = []
    for user in orders:
        state = users[user]["status"].pop(0)
        answer.append(users[user]['nickname']+"님이 "+state)
    
    return answer

print(solution(["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]),
["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."])