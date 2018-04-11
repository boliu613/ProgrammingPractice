// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.


#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <climits>

using std::string;
using std::vector;

class Solution {
public:
    int maxArea(vector<int>& height) {
        if (height.size()<2) return 0;
        int max=0,i=0,j=height.size()-1;
        while(j-i>0) {
            int temp = (j-i)*(height[i]>height[j]?height[j]:height[i]);
            if (temp>max) max=temp;
            if (height[i]>height[j])
            {
                --j;
            }
            else
            {
                ++i;
            }
        }
        return max;
    }
};

int main(int argc, const char * argv[]) {
    Solution solution;
    static const int arr[] = {1,1};
    std::vector<int> v (arr, arr+sizeof(arr)/sizeof(arr[0]));
    std::cout<<solution.maxArea(v);
    return 0;
}

