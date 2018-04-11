import math 

def isInt(x):
    '''checks if x is an integer or not. returns boolean.'''
    return x == int(x)

def equationCheck(a, b, c):
    '''checks whether the quadratic equation has integer solutions.''' 
    solution = (-b + math.sqrt(b ** 2 - 4 * a * c)) / (2 * a)
    return isInt(solution)

def sumOfFactors(x):
    '''return the sum of a number's all factors'''
    sum = 0

    for i in range(1, x):
        if x % i == 0:
            sum = sum + i

    return sum

def isPrime(x):
    '''This function returns whether or not the given number x is prime. '''
    # 1 is neither prime nor composite.
    if x == 1:
        return False
    
    # Check 2 separately.    
    if x == 2:
        return True
    
    # Verify the primality with trial division method.
    for i in range(2, x):
        if x % i == 0:
            return False
    return True

def isComposite(x):
    '''This function returns whether or not the given number x is composite. '''
    # 1 is neither prime nor composite.
    if x == 1:
        return False
    
    # If a number, except 1, is not prime, it is a composite. 
    if isPrime(x):
        return False
    else:
        return True

def isPerfect(x):
    '''This function returns whether or not the given number x is perfect. '''
    sum = sumOfFactors(x)

    if sum == x:
        return True
    else:
        return False

def isAbundant(x):
    '''This function returns whether or not the given number x is abundant. '''
    sum = sumOfFactors(x)

    if sum > x:
        return True
    else:
        return False

def isTriangular(x):
    '''This function returns whether or not the given number x is triangular. '''
    # A number is triangular if n^2 + n - 2 * number = 0 has integer solutions.
    return equationCheck(1, 1, -2 * x)

def isPentagonal(x):
    '''This function returns whether or not the given number x is pentagonal. '''
    # A number is pentagonal if 3n^2 - n - 2 * number = 0 has integer solutions.
    return equationCheck(3, -1, -2 * x)

def isHexagonal(x):
    '''This function returns whether or not the given number x is hexagonal. '''
    # A number is hexagonal if 2n^2 - n - number = 0 has integer solutions.
    return equationCheck(2, -1, -x)

def main():
    '''main function'''
    number = input('Please input a number between 1 and 10000.')

    # If user enters -1, the program is quit.
    while number != -1:
        # The input number should be between 1 and 10000.
        if number >= 1 and number <= 10000:
            print number, 'is',
            if not isPrime(number):
                print 'not',
            print 'prime,',

            print 'is',
            if not isComposite(number):
                print 'not',
            print 'composite,',

            print 'is',
            if not isPerfect(number):
                print 'not',
            print 'perfect,',

            print 'is',
            if not isAbundant(number):
                print 'not',
            print 'abundant,',

            print 'is',
            if not isTriangular(number):
                print 'not',
            print 'triangular,',

            print 'is',
            if not isPentagonal(number):
                print 'not',
            print 'pentagonal,',

            print 'is',
            if not isHexagonal(number):
                print 'not',
            print 'hexagonal.'
        else:
            #Error message.
            print
            print 'Error! The input number is out of the range between 1 and 10000! '

        print
        number = input('Please input a number between 1 and 10000.')



if __name__ == '__main__':
    main()
