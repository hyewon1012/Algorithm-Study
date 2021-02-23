import sys
INF = sys.maxsize

if __name__ == "__main__" :
    N = int(input())
    M = int(input())
    #그래프 정보를 담는 인접행렬, 초기 거리는 무한대로 설정
    distance = [[INF]*(N+1) for _ in range(N+1)]

    #인접행렬에 u->v 로 가는 거리(cost)를 저장
    for _ in range(M):
        u,v,cost = map(int, input().split())
        distance[u][v] = min(cost, distance[u][v])
    
    # floyd
    # k는 경유지로 모든 정점들을 경유지로 설정
    for k in range(1,N+1):
        for i in range(1,N+1):
            for j in range(1,N+1):
                if i==j:
                    distance[i][j] = 0
                # i 노드 -> j 노드 갈때 기존 거리값과 k 노드를 거쳐간 경유지 거리값중 작은 값으로 업데이트
                distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])
    
    for y in range(1,N+1):
        for x in range(1,N+1):
            if(distance[y][x] == INF):
                print(0, end=' ')
            else:
                print(distance[y][x], end=' ')
        print()
