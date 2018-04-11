// Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

// Example:

// Input: "babad"

// Output: "bab"

// Note: "aba" is also a valid answer.


// Example:

// Input: "cbbd"

// Output: "bb"

#include <iostream>
#include <string>
#include <vector>

using std::string;

class Solution {
public:
    string longestPalindrome(string s) {
        int max_len=0;
        string max_s;
        bool p_matrix[s.size()][s.size()];
        for (int i = 0; i < s.size(); ++i)
        {
            p_matrix[i][i]=true;
            if (i < s.size()-1)
            {
                p_matrix[i][i+1]=(s[i]==s[i+1]);  
            }
        }
        for (int j = 2; j < s.size(); ++j)
        {
            for (int i = 0; i < j-1; ++i)
            {
                p_matrix[i][j]=(p_matrix[i+1][j-1] && (s[i]==s[j]));
            }
        }
        for (int i = 0; i < s.size(); ++i)
        {
            for (int j = i; j < s.size(); ++j)
            {
                if (p_matrix[i][j] && max_len<(j-i+1))
                {
                    max_s=s.substr(i,j-i+1);
                    max_len=j-i+1;
                }
            }
        }
        return max_s; 
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    string s = "abb";
    std::cout<<solution.longestPalindrome(s);    
    return 0;
}
