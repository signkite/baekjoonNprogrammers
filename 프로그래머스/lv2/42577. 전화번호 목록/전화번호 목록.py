def solution(phone_book):
    phone_book.sort()
    
    def prefix_check(num1, num2):
        if num2.find(num1) == 0:
            return True
        else:
            return False
    
    for i in range(len(phone_book) - 1):
        if prefix_check(phone_book[i], phone_book[i + 1]):
            return False
            
    return True