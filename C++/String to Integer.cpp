// Implement atoi to convert a string to an integer.
// Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
// Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

// Requirements for atoi:
// The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

// The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

// If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

// If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;

class Solution {
public:
    int myAtoi(string str) {
        long result=0;
        if (str.size() == 0) return 0;
        const char * temp = str.c_str();
        int i=0;
        int sign=1;
        while ((temp[i]<'0' || temp[i]>'9') && i<str.size()){
            if (temp[i] != ' ' && temp[i] != '-' && temp[i] != '+') return 0;
            if (temp[i] == '+' && i<str.size()-1) {
                if (temp[i+1]>='0' && temp[i+1]<='9')
                {
                    ++i;
                    break;
                }
                else
                {
                    return 0;
                }
            }
            if (temp[i] == '-' && i<str.size()-1) {
                if (temp[i+1]>='0' && temp[i+1]<='9')
                {
                    sign = -1;
                    ++i;
                    break;
                }
                else
                {
                    return 0;
                }
            }
            ++i;
        } 

        if (i==str.size()) return 0;
        while (temp[i]>='0' && temp[i]<='9' && i<str.size()){
            result=result*10+temp[i]-'0';
            if (result>INT_MAX or result<INT_MIN){
            if (sign<0) return INT_MIN;
            return INT_MAX;
        }
            ++i;
        }
        if (result>INT_MAX or result<INT_MIN){
            if (sign<0) return INT_MIN;
            return INT_MAX;
        }
        return (int)result*sign;
    }
};

int main(int argc, const char * argv[]) {
    Solution solution;
    std::cout<<solution.myAtoi("18446744073709551617");    
    return 0;
}
