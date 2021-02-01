s = input()
result = []
for i in range(1,len(s)+1):
    result.append(s[-i:])
result.sort()
for c in result:
    print(c)