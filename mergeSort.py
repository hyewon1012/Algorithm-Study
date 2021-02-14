def merge(leftList, rightList):
    i = 0
    j = 0
    sorted_list = []
    while(i<len(leftList) and j<len(rightList)):
        if leftList[i] < rightList[j]:
            sorted_list.append(leftList[i])
            i+=1
        else:
            sorted_list.append(rightList[j])
            j+=1

    #남은값들 다 넣어주기
    while(i<len(leftList)):
        sorted_list.append(leftList[i])
        i+=1
    while(j<len(rightList)):
        sorted_list.append(rightList[j])
        j+=1
    return sorted_list
        

def mergeSort(origin):
    # base case
    if len(origin) <= 1:
        return origin
    
    mid = len(origin) // 2
    leftList = origin[:mid]
    rightList = origin[mid:]

    leftList = mergeSort(leftList)
    rightList = mergeSort(rightList)
    return merge(leftList, rightList)

print(mergeSort([14, 7, 3, 12, 9, 11, 6, 2]))