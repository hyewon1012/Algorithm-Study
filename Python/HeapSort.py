#최대힙정렬
#idx를 루트로하는 서브트리를 heap 자료구조로 재구성
def heapify(lis, idx, heap_size):
    largest = idx
    l = idx*2+1
    r = idx*2+2
    # 왼쪽자식이있고 루트노드보다 클때
    if l < heap_size and lis[idx] < lis[l]:
        largest = l
    # 오른쪽자식이있고 루트노드보다 클때
    if r < heap_size and lis[largest] > lis[r]:
        largest = r
    if largest != idx:
        lis[idx], lis[largest] = lis[largest], lis[idx]

        heapify(lis, largest, heap_size)

def heapSort(lis):
    size = len(lis)
    

lis = [3,5,1,2,7,4,9]

