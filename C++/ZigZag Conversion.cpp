// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"
// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string text, int nRows);
// convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

#include <iostream>
#include <string>
#include <vector>

using std::string;

class Solution {
public:
    string convert(string s, int numRows) {
        string result;

        if (s.size()==0) return "";
        if (numRows==1) return s;
        if (numRows==2)
        {
            for (int i = 0; i < numRows; ++i)
            {
                int index = i;
                while(index < s.size()){
                    result.push_back(s[index]);
                    index = index + 2;                    
                }
            }
            return result; 
        }

        for (int i = 0; i < numRows; ++i)
        {
            int index = i;
            result.push_back(s[i]);
            while(index < s.size()){
                int orginal_index = index;
                index = orginal_index + 2*numRows - 2 - 2*i;
                if (index>0 && index<s.size() && index>orginal_index)
                {
                    result.push_back(s[index]);
                }
                orginal_index = index;
                index = orginal_index + 2*i;
                if (index>0 && index<s.size() && index>orginal_index)
                {
                    result.push_back(s[index]);
                }
            }
        }
        return result;        
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    string s = "abcdef";
    std::cout<<solution.convert(s,3);    
    return 0;
}
