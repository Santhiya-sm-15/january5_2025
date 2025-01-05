# january5_2025
The problem that i solved today in leetcode

🚀 Problem: Shifting Letters II (LeetCode Daily Challenge)
Problem Statement
You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [start, end, directioni]. For every i:Shift the characters in s from index starti to endi (inclusive):Forward if directioni = 1 (e.g., 'a' -> 'b', ... 'z' -> 'a').Backward if directioni = 0 (e.g., 'a' -> 'z', .. 'b' -> 'a').The shifting is cyclic, so moving beyond 'z' wraps around to 'a', and vice versa.

Constraints:
1 <= s.length <= 10^5
shifts.length <= 10^5
0 <= start <= end < s.length
direction is either 0 or 1.

💡 Approach
To efficiently handle the potentially large number of shifts, we use a prefix sum technique:

Use an array prefix of size n to store cumulative shift values for each character.
For each range [start, end] in shifts:
Increment prefix[start] if directioni = 1 (forward).
Decrement prefix[start] if directioni = 0 (backward).
Apply the opposite adjustment to prefix[end+1] to limit the range effect.
Compute the final shifts for each character by calculating the prefix sum.
Adjust each character in the string using modular arithmetic for cyclic shifts.

🧑‍💻 Code
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] prefix = new int[n];       
        for (int[] x : shifts) {
            int l = x[0], r = x[1];
            if (x[2] == 1) { 
                prefix[l] += 1;
                if (r + 1 < n) prefix[r + 1] -= 1;
            } else { 
                prefix[l] -= 1;
                if (r + 1 < n) prefix[r + 1] += 1;
            }
        }
        for (int i = 1; i < n; i++) 
            prefix[i] += prefix[i - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int z = s.charAt(i) - 'a';
            if (prefix[i] > 0)
                z = (z + prefix[i]) % 26;
            else if (prefix[i] < 0)
                z = (z + (prefix[i] % 26) + 26) % 26;
            sb.append((char)(z + 'a'));
        }
        return sb.toString();
    }
}

🔍 Explanation
Key Steps:
Prefix Array Initialization:
Use a prefix array to mark where shifts start and stop. This avoids direct iteration over every range in shifts.
Range Updates:
If directioni = 1, increment the start index and decrement one past the end index.
If directioni = 0, decrement the start index and increment one past the end index.
Prefix Sum Calculation:
Transform the prefix array into cumulative shifts using prefix sums. Each index i now represents the total shift for the i-th character.
Character Adjustment:
Use the formula (current_char + total_shifts) % 26 for forward shifts.
Handle backward shifts with (current_char + (total_shifts % 26) + 26) % 26 to ensure non-negative results.

🚀 Complexity Analysis
Time Complexity:O(n)
Space Complexity: O(n) for the prefix array.
