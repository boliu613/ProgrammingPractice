// Given a roman numeral, convert it to an integer.

// Input is guaranteed to be within the range from 1 to 3999.

// Symbol  I   V   X   L   C   D   M
// Value   1   5   10  50  100 500 1,000

#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;

class Solution {
public:
    int romanToInt(string s) {
        int result = 0;
        for (int i = 0; i < s.size(); ++i)
        {
            int sign=1;
            if (s[i]=='M')
            {
                result=result+1000*sign;
            }
            else if (s[i]=='D')
            {
                if (i<s.size()-1 && s[i+1]=='M') sign=-1;
                result=result+500*sign;
            }
            else if (s[i]=='C')
            {
                if (i<s.size()-1 && (s[i+1]=='D'||s[i+1]=='M')) sign=-1;
                result=result+100*sign;
            }
            else if (s[i]=='L')
            {
                if (i<s.size()-1 && (s[i+1]=='C'||s[i+1]=='D'||s[i+1]=='M')) sign=-1;
                result=result+50*sign;
            }
            else if (s[i]=='X')
            {
                if (i<s.size()-1 && (s[i+1]=='L'||s[i+1]=='C'||s[i+1]=='D'||s[i+1]=='M')) sign=-1;
                result=result+10*sign;
            }
            else if (s[i]=='V')
            {
                if (i<s.size()-1 && (s[i+1]=='X'||s[i+1]=='L'||s[i+1]=='C'||s[i+1]=='D'||s[i+1]=='M')) sign=-1;
                result=result+5*sign;
            }
            else if (s[i]=='I')
            {
                if (i<s.size()-1 && (s[i+1]=='V'||s[i+1]=='X'||s[i+1]=='L'||s[i+1]=='C'||s[i+1]=='D'||s[i+1]=='M')) sign=-1;
                result=result+1*sign;
            }
            else
            {
                result=result+0;
            }
        }
        return result;
    }
};

int main(int argc, const char * argv[]) {
    Solution solution;
    std::cout<<solution.romanToInt("MCM");
    return 0;
}

