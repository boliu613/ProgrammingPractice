// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

// Example:

// Input: 1->2->4, 1->3->4
// Output: 1->1->2->3->4->4


// Definition for singly-linked list.
#include<cstddef>
#include <iostream>
#include <string>
struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
	ListNode * mergeTwoLists(ListNode* l1, ListNode* l2) {
		if (l1 == NULL && l2 != NULL) return l2;
		if (l1 != NULL && l2 == NULL) return l1;
		if (l1 == NULL && l2 == NULL) return NULL;
		ListNode * result;
		ListNode * current;
		if (l1->val <= l2->val)
		{
			result = l1;
			current = l1;
			l1 = l1->next;
		}
		else{
			result = l2;
			current = l2;
			l2 = l2->next;
		}

		while (l1 != NULL && l2 != NULL) {
			if (l1->val <= l2->val)
			{
				current->next = l1;
				current = current->next;
				l1 = l1->next;
			}
			else{
				current->next = l2;
				current = current->next;
				l2 = l2->next;
			}
		}
		if (l1 == NULL && l2 != NULL) current->next = l2;
		if (l1 != NULL && l2 == NULL) current->next = l1;
		return result;
	}
};

int main(int argc, const char * argv[]) {
	Solution solution;
	// std::cout << solution.mergeTwoLists("]");

	return 0;
}