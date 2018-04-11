// Write a function to find the longest common prefix string amongst an array of strings.


#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;
using std::vector;

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        string shortest;
        int len=INT_MAX;
        for (int i = 0; i < strs.size(); ++i)
        {
            if (strs[i].size()<len)
            {
                shortest=strs[i];
                len=strs[i].size();
            }
        }
        for (int i = len; i > 0; --i)
        {
            int j=0;
            for (j = 0; j < strs.size(); ++j)
            {
               if (strs[j].compare(0,i,shortest.substr(0,i))!=0) break;
            }
            if (j==strs.size()) return shortest.substr(0,i);
        }
        return "";
    }
};

int main(int argc, const char * argv[]) {
    Solution solution;
    std::vector<string> v;
    v.push_back("b");
    v.push_back("aMa");
    v.push_back("MMa");
    std::cout<<solution.longestCommonPrefix(v);
    return 0;
}

