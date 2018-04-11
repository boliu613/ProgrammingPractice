// There are two sorted arrays nums1 and nums2 of size m and n respectively.

// Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

// Example 1:
// nums1 = [1, 3]
// nums2 = [2]

// The median is 2.0
// Example 2:
// nums1 = [1, 2]
// nums2 = [3, 4]

// The median is (2 + 3)/2 = 2.5

#include <iostream>
#include <string>
#include <vector>

using std::vector;

class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size();
        int n = nums2.size();
        if (m>n)
        {
            vector<int> temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int size_temp = m;
            m = n;
            n = size_temp;
        }
        
        if (n==0)
        {
            return 0;
        }

        for (int i = 0; i <= m; )
        {
            int j = (m+n+1)/2-i;
            if (i>0 && j<n && nums1[i-1] > nums2[j]) {
                --i;
            }
            else if (j>0 && i<m && nums2[j-1] > nums1[i]) {
                ++i;
            }
            else 
            {
                int max_left, min_right;
                if (i==0) 
                {
                    max_left=nums2[j-1];
                }
                else if (j==0)
                {
                    max_left=nums1[i-1];
                }
                else
                {
                    max_left=(nums1[i-1]>=nums2[j-1]?nums1[i-1]:nums2[j-1]);

                }
                
                if (i==m)
                {
                    min_right=nums2[j];
                }
                else if (j==n)
                {
                    min_right=nums1[i];
                }
                else
                {
                    min_right=(nums1[i]<=nums2[j]?nums1[i]:nums2[j]);
                }

                if ((m+n)%2==0)
                {
                    return ((max_left+min_right )/2.0);
                }
                else
                {
                    return max_left;
                }
            }
            
        }
        return 0.0;
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    int a[]={1,3};
    int b[]={2};
    std::vector<int> nums1(a,a + sizeof(a)/sizeof(a[0]));
    std::vector<int> nums2(b,b + sizeof(b)/sizeof(b[0]));
    std::cout<<solution.findMedianSortedArrays(nums1,nums2);    
    return 0;
}
