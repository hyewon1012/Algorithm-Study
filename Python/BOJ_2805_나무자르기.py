def solve(tree, length, height):
    maxHeight = 0
    #적어도 length만큼 나무 가져갈려면 절단기 높이 최대값 몇으로 설정?
    left, right = 0, 1000000000

    while(left <= right):
        remain = 0
        mid = (left + right) // 2 # 절단기 높이 중간값으로 설정
        #잘라본다
        for h in height:
            if h >= mid :
                remain += h-mid
        # print("mid : ", mid, " remain : ",remain)
        # print("left : ", left, " right : ",right)
        if remain >= length:#필요한만큼 자를수있음 높이를 높여본다.
            maxHeight = mid
            left = mid+1
        else:#못자를때 절단기 높이를 낮춘다
            right = mid-1

    return maxHeight

if __name__ == "__main__":
    #나무의수, 가져가야 하는 나무 길이
    tree, length = map(int, input().split())
    height = list(map(int, input().split()))

    print(solve(tree,length,height))
    
