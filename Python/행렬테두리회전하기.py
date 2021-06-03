import copy

def solution(rows, columns, queries):
    answer = []
    board = [[0]*columns for _ in range(rows)]
    num = 1
    for y in range(rows):
        for x in range(columns):
            board[y][x] = num
            num+=1
    
    for q in queries:
        #copymap = copy.deepcopy(board)
        top, left, bottom, right = q[0]-1, q[1]-1, q[2]-1 ,q[3]-1
        
        temp = board[top][left]
        minValue = 987654321

        minValue = min(minValue, temp)
        for y in range(top+1, bottom+1): #위쪽
            board[y-1][left] = board[y][left]
            minValue = min(minValue, board[y][left])

        for x in range(left+1, right+1): #왼쪽
            board[bottom][x-1] = board[bottom][x]
            minValue = min(minValue, board[bottom][x])  
        
        for y in range(bottom-1, top-1, -1): #아래쪽
            board[y+1][right] = board[y][right]
            minValue = min(minValue, board[y][right])
        
        for x in range(right-1, left-1, -1): # 오른쪽
            board[top][x+1] = board[top][x]
            minValue = min(minValue, board[top][x])

        board[top][left+1] = temp

             
        answer.append(minValue)
    return answer

if __name__ == "__main__":
    r = 6
    c = 6
    #queries = [[2,2,5,4]]
    queries = [[2,2,5,4],[3,3,6,6],[5,1,6,3]]
    print(solution(r,c,queries))