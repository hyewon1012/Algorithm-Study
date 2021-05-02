def divide(p):
    u,v = '',''
    open, close = 0,0
    for i,c in enumerate(p):
        if c == '(': 
            open += 1
        if c == ')':
            close += 1
        if open == close:
            u = p[:i+1]
            v = p[i+1:]
            break
    return u,v

def reverse(u):
    ret = ''
    for c in u:
        if c =='(' : ret += ')'
        if c == ')' : ret += '('
    return ret

def solve(s):
    if s == '': 
        return ''

    u,v = divide(s)
    if isCorrect(u):
        return u + solve(v)
    else: #4번 과정
        return '(' + solve(v) + ')' + reverse(u[1:-1])

def isCorrect(u):
    stack = []
    for c in u:
        if c == '(':
            stack.append(c)
        elif stack :
            stack.pop()
    return not stack

def solution(p):
    answer = ''
    # 올바른 괄호인지 확인
    if isCorrect(p):
        answer = p
    else:
        answer = solve(p)
    # print(answer)    
    return answer

if __name__ == "__main__":
    p = "(()())()"
    #p = ""
    solution(p)