// Given an integer, convert it to a roman numeral.

// Input is guaranteed to be within the range from 1 to 3999.

// Symbol  I   V   X   L   C   D   M
// Value   1   5   10  50  100 500 1,000

#include <iostream>
#include <vector>
#include <cmath>
#include <climits>
#include <sstream>

using std::string;
using std::vector;

class Solution {
public:
    string intToRoman(int num) {
        int len=0;
        int temp=num;
        std::stringstream ss;
        while(temp>0){
            temp=temp/10;
            ++len;
        }
        temp=num;
        for (int i = len; i > 0; --i)
        {
            if (i==4)
            {
                while(temp>=1000){
                    temp=temp-1000;
                    ss<<'M';
                }
            }
            if (i==3)
            {
                if (temp>=900)
                {
                    ss<<"CM";
                    temp=temp-900;
                }
                else if (temp>=500)
                {
                    ss<<'D';
                    temp=temp-500;
                    while(temp>=100){
                        temp=temp-100;
                        ss<<'C';
                    }
                }
                else if (temp>=400)
                {
                    ss<<"CD";
                    temp=temp-400;
                }
                else if (temp>=100)
                {
                    while(temp>=100){
                        temp=temp-100;
                        ss<<'C';
                    }
                }
            }
            if (i==2)
            {
                if (temp>=90)
                {
                    ss<<"XC";
                    temp=temp-90;
                }
                else if (temp>=50)
                {
                    ss<<'L';
                    temp=temp-50;
                    while(temp>=10){
                        temp=temp-10;
                        ss<<'X';
                    }
                }
                else if (temp>=40)
                {
                    ss<<"XL";
                    temp=temp-40;
                }
                else if (temp>=10)
                {
                    while(temp>=10){
                        temp=temp-10;
                        ss<<'X';
                    }
                }
            }
            if (i==1)
            {
                if (temp>=9)
                {
                    ss<<"IX";
                    temp=temp-9;
                }
                else if (temp>=5)
                {
                    ss<<'V';
                    temp=temp-5;
                    while(temp>=1){
                        temp=temp-1;
                        ss<<'I';
                    }
                }
                else if (temp>=4)
                {
                    ss<<"IV";
                    temp=temp-4;
                }
                else if (temp>=1)
                {
                    while(temp>=1){
                        temp=temp-1;
                        ss<<'I';
                    }
                }
            }
        }
        return ss.str();
    }
};

int main(int argc, const char * argv[]) {
    Solution solution;
    std::cout<<solution.intToRoman(1000);
    return 0;
}

