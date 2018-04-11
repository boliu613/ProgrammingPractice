// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

// Example

// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// Explanation: 342 + 465 = 807.
#include <iostream>

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        if (l1 == NULL && l2 == NULL){
            return NULL;
        }
        
        if (l1 == NULL && l2 != NULL){
            return l1;
        }
        
        if (l1 != NULL && l2 == NULL){
            return l2;
        }
        
        ListNode* result=new ListNode(l1->val);
        ListNode* start = result;
        while(l1->next != NULL){
            result->next = new ListNode(l1->next->val);
            result = result->next;
            l1 = l1->next;
        }
        result = start;
        result->val = result->val + l2->val;
        while(l2->next != NULL){
            if (result->next != NULL){
                result->next->val = result->next->val + l2->next->val;
                result = result->next;
                l2 = l2->next;
            }else{
                result->next = new ListNode(l2->next->val);
                result = result->next;
                l2 = l2->next;
            }

        }
        result = start;
        while(result->next != NULL){
            if (result->val > 9){
                result->val = result->val-10;
                result->next->val = result->next->val+1;
            }
            result = result->next;
        }
        if (result->val > 9){
            result->val = result->val-10;
            result->next = new ListNode(1);
        }
        return start;
    }
};


int main(int argc, const char * argv[]) {
    Solution solution;
    ListNode *l1 = new ListNode(1);
    l1->next = new ListNode(2);
    l1->next->next = new ListNode(4);
    
    ListNode *l2 = new ListNode(9);
    l2->next = new ListNode(2);
    l2->next->next = new ListNode(7);
    
    ListNode *l3 = solution.addTwoNumbers(l1,l2);
    while(l3 != NULL){
        std::cout << l3->val << " ";
        l3 = l3->next;
    }
    
    return 0;
}
