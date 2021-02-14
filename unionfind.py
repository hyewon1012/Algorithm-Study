# v의 루트 노드 반환
def Find(v):
    if(v == parent[v]):
        return v
    else:
        p = Find(parent[v])
        parent[v] = Find(parent[v])
        return p

# u에는 u의 루트노드를 저장
# v에는 v의 루트노드를 저장
# 비교한다음 u에 v를 붙임 -> v의 루트노드를 x로 설정
def Union(u, v):
    u = Find(u)
    v = Find(v)
    if(u!=v):
        parent[v] = u

n,m = map(int, input().split())
parent = [i for i in range(n+1)]

for _ in range(m):
    command, a, b = map(int, input().split())
    if command == 0: #합집합
        Union(a,b)
    elif command == 1: # 같은 집합인지 확인
        a_parent = Find(a)
        b_parent = Find(b)
        if a_parent == b_parent:
            print("YES")
        else:
            print("NO")


