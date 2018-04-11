// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

// Note: The solution set must not contain duplicate triplets.

// For example, given array S = [-1, 0, 1, 2, -1, -4],

// A solution set is:
// [
//   [-1, 0, 1],
//   [-1, -1, 2]
// ]

#include <iostream>
#include <vector>
#include <cmath>
#include <climits>
#include <sstream>

using std::string;
using std::vector;

class Solution {
public:
    vector <vector <int> > threeSum(vector<int>& nums) {
        vector <vector <int> > result;
        std::sort(nums.begin(),nums.end());
        for (int i = 0; i < nums.size(); ++i)
        {
            int front=i+1;
            int back=nums.size()-1;
            while (front<back){
                if (nums[front]+nums[back]==-nums[i])
                {
                    vector<int> triplet(3,0);
                    triplet[0]=nums[i];
                    triplet[1]=nums[front];
                    triplet[2]=nums[back];
                    result.push_back(triplet);
                    while(front<back && nums[front]==triplet[1]) ++front;
                    while(front<back && nums[back]==triplet[2]) --back;
                }
                else if (nums[front]+nums[back]<-nums[i])
                {
                    int temp = nums[front];
                    while(front<back && nums[front]==temp) ++front;
                }
                else
                {
                    int temp = nums[back];
                    while(front<back && nums[back]==temp) --back;
                }
            }
            while(i<nums.size()-1 && nums[i]==nums[i+1]) ++i;
        }
        return result;
    }
};

int main(int argc, const char * argv[]) {
    static const int arr[] = {-1, 0, 1, 2, -1, -4};
    vector<int> nums(arr, arr+sizeof(arr)/sizeof(arr[0]));
    Solution solution;
    vector <vector <int> > result = solution.threeSum(nums);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
        {
            std::cout<<result[i][j]<<' ';
        }
        std::cout<<'\n';
    }
    return 0;
}

