// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

#include <iostream>
#include <string>
#include <stack>
using namespace std;

class Solution {
public:
    bool isValid(string s) {
    	for (int i = 0; i < s.size(); ++i)
    	{
    		if (s[i] == '(' || s[i] == '{' || s[i] == '['){
    			b.push(s[i]);
    		}
    		if (s[i] == ')') {
                if (b.empty()) return false;
                char temp = b.top();
                if (temp != '(' || b.empty()) {
                    return false;
                }
                else {
                    b.pop();
                }
            }
            if (s[i] == '}') {
                if (b.empty()) return false;
                char temp = b.top();
                if (temp != '{' || b.empty()) {
                    return false;
                }
                else {
                    b.pop();
                }
            }
            if (s[i] == ']') {
                if (b.empty()) return false;
                char temp = b.top();
                if (temp != '[' || b.empty()) {
                    return false;
                }
                else {
                    b.pop();
                }
            }
        }
        if (!b.empty()) return false;
        return true;
    }

private:
	stack<char> b;
};

int main(int argc, const char * argv[]) {
    std::cout << (3-1)/2;
    // Solution solution;
    // std::cout << solution.isValid("]");
    
    return 0;
}