import sys
input = sys.stdin.readline
ans = 1e9

def solve(idx, cnt):
    global ans
    if cnt >= M:
        sum_ = 0
        for hy,hx in home:
            d = 1e9
            for sy,sx in selected:
                d = min(d, abs(hy-sy)+abs(hx-sx))
            sum_ += d
        ans = min(ans, sum_)
        return

    for i in range(idx, len(chiken)):
        if not visited[i]:
            visited[i] = True
            selected.append(chiken[i])
            solve(idx+1, cnt+1)
            visited[i] = False
            selected.pop()

if __name__ == "__main__":
    N , M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    home, chiken, selected = [], [], []

    for y in range(N):
        for x in range(N):
            if board[y][x] == 1:
                home.append((y,x))
            elif board[y][x] == 2:
                chiken.append((y,x))

    visited = [False] * len(chiken)

    solve(0,0)
    print(ans)
