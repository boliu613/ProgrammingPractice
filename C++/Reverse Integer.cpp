// Given a 32-bit signed integer, reverse digits of an integer.

// Example 1:

// Input: 123
// Output:  321
// Example 2:

// Input: -123
// Output: -321
// Example 3:

// Input: 120
// Output: 21
// Note:
// Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;

class Solution {
public:
    int reverse(int x) {
        long result=0;
        while(x>=1 || x<=-1){
            result=result*10;
            result=result+x%10;
            x=x/10;
        }
        if (result>INT_MAX or result<INT_MIN)  return 0;
        return (int)result;
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    std::cout<<solution.reverse(1563847412);    
    return 0;
}
