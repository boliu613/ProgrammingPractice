// Determine whether an integer is a palindrome. Do this without extra space.

// click to show spoilers.

// Some hints:
// Could negative integers be palindromes? (ie, -1)

// If you are thinking of converting the integer to string, note the restriction of using extra space.

// You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

// There is a more generic way of solving this problem.


#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;

class Solution {
public:
    bool isPalindrome(int x) {
        if (x<0) return false;
        int len=0;
        int temp=x;
        while(temp>=10){
            temp=temp/10;
            ++len;
        }
        temp=x;
        while(temp>=10){
            int last_digit=temp%10;
            int first_digit=temp/pow(10,len);
            if (last_digit != first_digit) return false;
            temp=temp-first_digit*pow(10,len);
            temp=temp/10;
            len=len-2;
        }
        if (len>0&&temp!=0) return false;
        return true;
    }
}; 

int main(int argc, const char * argv[]) {
    Solution solution;
    std::cout<<solution.isPalindrome(1000030001);    
    return 0;
}
