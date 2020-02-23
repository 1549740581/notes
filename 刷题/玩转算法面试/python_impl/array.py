"""
283 move zeros
给定一个数组nums，将所有0移动到数组的末尾，同时保持非零元素的相对顺序.
Do not return anything, modify nums in-place instead.
"""
class Q01:
    def moveZeroes(self, nums) -> None:
        idx = -1
        for item in nums:
            if item:
                idx += 1
                nums[idx] = item
        idx += 1
        while idx < len(nums):
            nums[idx] = 0
            idx += 1

"""
27  remove element
给定一个数组nums和一个值val，原地移除所有数值等于val的元素，返回移除后数组的新长度
"""
class Q02:
    def removeElement(self, nums, val) -> int:
        idx = -1
        for item in nums:
            if item != val:
                idx += 1
                nums[idx] = item
        return idx + 1

"""
26 remove duplicated from sorted array
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度.
"""
class Q03:
    def removeDuplicates(self, nums) -> int:
        idx = 0 
        for item in nums:
            if item != nums[idx]:
                idx += 1
                nums[idx] = item
        return idx + 1


"""
80 remove duplicated from sorted array II
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度.
"""
class Q04:
    def removeDuplicates(self, nums) -> int:
        idx = 0
        for item in nums:
            if idx < 2 or item > nums[idx - 2]:
                nums[idx] = item
                idx += 1
        return idx

"""
 75 sort color
 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列.
 使用整数 0、 1 和 2 分别表示红色、白色和蓝色.
"""
class Q05:
    def sortColors(self, nums) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # 荷兰国旗问题
        # nums[0, lo-1] == 0
        # nums[lo, hi] == 1
        # nums[hi+1, len(nums)-1] == 2
        i, lo, hi = 0, 0, len(nums) - 1
        while i <= hi:
            if nums[i] == 1:
                i += 1
            elif not nums[i]:
                nums[i], nums[lo] = nums[lo], nums[i]
                i += 1
                lo += 1
            else:
                nums[i], nums[hi] = nums[hi], nums[i]
                hi -= 1

"""
88 merge sorted array
给定两个有序整数数组nums1和nums2，将nums2合并到nums1中，使得num1成为一个有序数组.
注意：
    - 初始化nums1和nums2的元素数量分别为m和n
    - 假设nums1有足够的空间（空间大小大于或等于 m + n）来保存nums2中的元素
"""
class Q06:
    def merge(self, nums1, m: int, nums2, n) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        idx = m + n - 1
        m, n = m - 1, n - 1
        while m >= 0 and n >= 0:
            flag = nums1[m] > nums2[n]
            nums1[idx] = nums1[m] if flag else nums2[n]
            idx -= 1
            # 该写法因人而异
            m, n = m - flag, n - (not flag)
        # 如果nums2中还要还有剩余元素
        while n >= 0:
            nums1[idx] = nums2[n]
            idx -= 1
            n -= 1
"""
215 kth largest element in an array
在未排序的数组中找到第k个最大的元素.
"""
import random

class Q07:
    def findKthLargest(self, nums, k):
        def partition(nums, lo, hi):
            # shuffle is needed
            rnd = random.randint(lo, hi)
            nums[rnd], nums[hi] = nums[hi], nums[rnd]
            idx, pivot = lo, nums[hi]
            for i in range(lo, hi):
                if nums[i] < pivot:
                    nums[idx], nums[i] = nums[i], nums[idx]
                    idx += 1
            # nums[lo:idx] < pivot
            nums[idx], nums[hi] = nums[hi], nums[idx]
            return idx

        def findKthLargest(nums, lo, hi, k):
            idx = partition(nums, lo, hi)
            while idx != len(nums) - k:
                if idx < len(nums) - k:
                    idx = partition(nums, idx + 1, hi)
                else:
                    idx = partition(nums, lo, idx - 1)
            return nums[idx]
        
        return findKthLargest(nums, 0, len(nums) - 1, k)

"""
167 two sum II
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数.
注意：
    - 两个下标index1，index2从1开始，且index1 < index2
    - 答案唯一，且不能使用相同元素
"""
class Q08:
    def twoSum(self, numbers, target) -> list:
        res = []
        lo, hi = 0, len(numbers) - 1
        while lo < hi:
            val = numbers[lo] + numbers[hi]
            if val == target:
                res.append(lo + 1)
                res.append(hi + 1)
                break
            elif val > target:
                hi -= 1
            else:
                lo += 1
        return res


"""
125 valid palindrome
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写.
注意：空字符串定义为有效的回文字符串
"""
class Q09:
    def isPalindrome(self, s:str) -> bool:
        s = [*filter(str.isalnum, s.lower())]
        return s == s[::-1]

    # 虽然是原地的，但是没有上面速度快！
    def isPalindrome01(self, s:str) -> bool:
        lo, hi = 0, len(s) - 1
        while lo < hi:
            while lo < hi and not s[lo].isalnum():
                lo += 1
            while lo < hi and not s[hi].isalnum():
                hi -= 1
            if lo < hi:
                if s[lo].lower() != s[hi].lower():
                    return False
                lo += 1
                hi -= 1
        return True

"""
344 reverse string
原地反转字符串
"""
class Q10:
    def reverseString(self, s) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        s.reverse()
    
    def reverseString01(self, s) -> None:
        lo, hi = 0, len(s) - 1
        while lo < hi: 
            s[lo], s[hi] = s[hi], s[lo]
            lo += 1
            hi -= 1

"""
345 reverse vowels of a string
反转字符串中的元音字母.
"""
class Q11:
    def reverseVowels(self, s: str) -> str:
        def isVowel(ch) -> bool:
            # 不要写成，效率低:
            # vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
            # return ch in vowels
            return ch in 'aeiouAEIOU'

        lo, hi = 0, len(s) - 1
        arr = list(s)
        while lo < hi:
            while lo < hi and not isVowel(arr[lo]):
                lo += 1
            while lo < hi and not isVowel(arr[hi]):
                hi -= 1
            if lo < hi:
                arr[lo], arr[hi] = arr[hi], arr[lo]
                lo += 1
                hi -= 1
        return "".join(arr)

"""
11 container with most water
给定n个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai).
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水.
"""
class Q12:
    def maxArea(self, height: list) -> int:
        area, lo, hi = 0, 0, len(height) - 1
        while lo < hi:
            width, hgt = hi - lo, 0
            if height[lo] < height[hi]: 
                hgt = height[lo]
                lo += 1
            else:
                hgt = height[hi]
                hi -= 1
            area = max(area, hgt * width)
        return area

"""
209 min sub array length
给定一个含有n个正整数的数组和一个正整数s，找出该数组中满足其和≥ s的长度最小的连续子数组.
如果不存在满足条件的连续子数组，则返回0.
"""
class Q13:
    # 思路相同，效率相比Java实现差很多，待优化！
    def minSubArrayLen(self, s: int, nums: list) -> int:
        lo, hi, total, res = 0, 0, 0, len(nums) + 1
        while lo < len(nums):
            if hi < len(nums) and total < s:
                total += nums[hi]
                hi += 1
            else:
                total -= nums[lo]
                lo += 1
            if total >= s:
                res = min(res, hi - lo)
        return 0 if res == len(nums) + 1 else res

"""
 * 3 long substring without repeating characters
 * 给定一个字符串,请你找出其中不含有重复字符的最长子串的长度.
"""
class Q14:
    # 思路相同，效率相比Java实现差很多，待优化！
    def lengthOfLongestSubstring(self, s: str) -> int:
        freq = [0] * 256
        l, r, res = 0, -1, 0
        while l < len(s):
            if r + 1 < len(s) and not freq[ord(s[r + 1])]:
                freq[ord(s[r + 1])] += 1
                r += 1
            else:
                freq[ord(s[l])] -= 1
                l += 1
            res = max(r - l + 1, res)
        return res

"""
438 find all anagrams in a string
给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引.
字符串中只包含小写字母，且s、q长度都不超过20100.
"""
class Q15:
    def findAnagrams(self, s: str, p: str) -> list:
        res = []
        if len(s) < len(p):
            return []
        freqS = [0] * 26
        freqP = [0] * 26
        l, r = 0, -1
        for ch in p:
            freqP[ord(ch) - 97] += 1
            r += 1
            freqS[ord(s[r]) - 97] += 1
        if freqS == freqP:
            res.append(l)
        while r < len(s) - 1:
            r += 1
            freqS[ord(s[r]) - 97] += 1
            freqS[ord(s[l]) - 97] -= 1
            l += 1
            if freqS == freqP:
                res.append(l)
        return res

"""
76 minimum window substring
一个字符串S、一个字符串T，请在字符串S里面找出：包含T所有字母的最小子串.
"""
class Q16:
    def minWindow(self, s: str, t: str) -> str:
        if len(s) < len(t):
            return ""
        LEN = 58
        l, r = 0, -1
        sFreq, tFreq = [0] * LEN, [0] * LEN
        for ch in t:
            tFreq[ord(ch) - 65] += 1
        start, end = -1, len(s) + 1
        while l <= len(s) - len(t):
            # 当前区间长度小于len(t)
            if r - l + 1 < len(t):
                if r + 1 < len(s):
                    r += 1
                    sFreq[ord(s[r])- 65] += 1
                    continue
                else:
                    break
            # 当前区间长度大于等于len(t)
            idx = 0
            while idx < LEN:
                if sFreq[idx] < tFreq[idx]:
                    break
                idx += 1
            # 当前区间没有包含t
            if idx < LEN:
                # 尝试区间s[l:r+1]是否能够包含t
                if r + 1 < len(s):
                    r += 1
                    sFreq[ord(s[r]) - 65] += 1
                else: # 没有下一个元素了
                    break
            # 当前区间包含了t
            else:
                # 且当前区间长度和t长度相等，直接返回
                if r - l + 1 == len(t):
                    return s[l: r + 1]
                else: # 先保存当前区间范围，然后尝试从左边缩小范围
                    if r - l < end - start:
                        start = l
                        end = r
                    sFreq[ord(s[l]) - 65] -= 1
                    l += 1
        return "" if start == -1 else s[start:end + 1]