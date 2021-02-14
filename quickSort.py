def partition(arr, low, high):
    i = (low-1) #작은 원소들의 끝
    pivot = arr[high]

    for j in range(low,high):
        if arr[j] <= pivot:
            i+=1
            arr[i], arr[j] = arr[j], arr[i] #swap
    arr[i+1], arr[high] = arr[high], arr[i+1] #중간에 피봇을 넣는다
    return i+1

def quickSort(arr, low, high):
    if len(arr) == 1 :
        return arr
    if low < high:
        pIdx = partition(arr, low, high)
        quickSort(arr, low, pIdx-1)
        quickSort(arr, pIdx+1, high)

arr = [1,2,5,4,-1,-2,3]
quickSort(arr, 0, len(arr)-1)
print(arr)