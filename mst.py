import sys
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

V, E = map(int, input().split())
graph = []
parent = [i for i in range(V+1)]

for _ in range(E):
    u,v,cost = map(int, input().split())
    graph.append([cost, u, v])

graph.sort(key=lambda x:x[0]) #간선(비용) 기준 오름차순 정렬
cnt = 0 # 모든 정점 연결되었는지 카운트
mst_weight = 0 # MST 가중치

for i in range(E):
    weight = graph[i][0]
    start = graph[i][1]
    end = graph[i][2]
    if Find(start) != Find(end):
        Union(start,end)
        mst_weight += weight
        cnt += 1
    if cnt == V-1:
        break
print(mst_weight)