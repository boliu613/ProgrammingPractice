// Given a string, find the length of the longest substring without repeating characters.

// Examples:

// Given "abcabcbb", the answer is "abc", which the length is 3.

// Given "bbbbb", the answer is "b", with the length of 1.

// Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

#include <iostream>
#include <string>
using std::string;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int max_len = 0;
        for (int i = 0; i < s.size(); ++i)
        {
            int len = 1;
            string temp = s.substr(i,len);
            while((temp.find(s[i+len]) == string::npos) && (i+len < s.size())){
                ++len;
                temp = s.substr(i,len);
            }
            if (max_len < len)
            {
                max_len = len;
            }

        } 
        return max_len;  
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    std::string const s = "abcd";
    std::cout<<solution.lengthOfLongestSubstring(s);    
    return 0;
}
