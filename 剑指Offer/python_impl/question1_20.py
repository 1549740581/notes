from utils import TreeNode, ListNode

"""
01 二维数组中查找
"""
class Q01:
    def Find(self, target, array):
        rows, cols = len(array), len(array[0])
        row, col = 0, cols - 1
        while row < rows and col >= 0:
            if array[row][col] == target:
                return True
            elif array[row][col] > target:
                col -= 1
            else:
                row += 1
        return False


"""
02 替换空格
"""
class Q02:
    def replaceSpace(self, s):
        return s.replace(' ', '%20')

"""
03 从头到尾打印链表
"""
class Q03:
    def printListFromTailToHead(self, listNode):
        res = []
        while listNode:
            res.append(listNode.val)
            listNode = listNode.next
        return res[::-1]

"""
04 重构二叉树
"""
class Q04:
    def reConstructBinaryTree(self, pre, tin):
        # assert(len(pre) == len(tin))
        if len(pre) == 0:
            return None
        treeNode = TreeNode(pre[0])
        mid = tin.index(pre[0])
        treeNode.left = self.reConstructBinaryTree(pre[1:mid + 1], tin[0:mid])
        treeNode.right = self.reConstructBinaryTree(pre[mid + 1:len(pre)], tin[mid + 1:len(tin)])
        return treeNode

"""
05 两个栈实现一个队列 
"""
class Q05:
    def __init__(self):
        self.data = []    # data stack
        self.helper = []  # helper stack
    # 只向data中append数据
    def push(self, node):
        self.data.append(node)
    # 只从helper中pop数据
    def pop(self):
        if self.helper:
            return self.helper.pop()
        else:
            while self.data:
                self.helper.append(self.data.pop())
        return self.helper.pop()


"""
06 旋转数组的最小值
"""
class Q06:
    def minNumberInRotateArray(self, rotateArray):
        # 注意这个判断是必须的
        if len(rotateArray) == 1:
            return rotateArray[0]
        lo, hi = 0, len(rotateArray) - 1
        while lo <= hi:
            mid = lo + ((hi - lo) >> 1)
            # 有序的
            if rotateArray[lo] < rotateArray[hi]:
                return rotateArray[lo]
            elif rotateArray[mid] < rotateArray[mid - 1]:
                return rotateArray[mid]
            elif rotateArray[mid] > rotateArray[mid + 1]:
                return rotateArray[mid + 1]
            elif rotateArray[mid] > rotateArray[lo]:
                lo = mid + 1
            else:
                hi = mid - 1
        return 0

"""
Q07 斐波那契数列的第n项，第0项为0，第1项为1
"""
class Q07:
    def Fibonacci(self, n):
        f0, f1 = 0, 1
        while n:
            f0, f1 = f1, f0 + f1
            n -= 1
        return f0

"""
Q08 青蛙跳台阶I
"""
class Q08:
    def jumpFloor(self, number):
        f0, f1 = 1, 1
        while number:
            f0, f1 = f1, f0 + f1
            number -= 1
        return f0

"""
Q09 青蛙跳台阶II
f(1) = 1
f(n) = f(n - 1) + f(n - 2) + ... +  f(1) + 1, n >= 2
递推：
f(n) = 2f(n - 1), n >= 2
"""
class Q09:
    def jumpFloorII(self, number):
        return 1 << (number - 1)

"""
Q10 矩形覆盖
"""
class Q10:
    def rectCover(self, number):
        if not number:
            return 0
        f0, f1 = 1, 1
        while number:
            f0, f1 = f1, f0 + f1
            number -= 1
        return f0

"""
Q11 二进制中1的个数
注意：
1、bin(-1) ---> '-0b1';
2、int32位，python中无限精度，因此对于负数相当于32位之前是无数个1，
   需要将负数前面的1变成0;
"""
class Q11:
    def NumberOf1(self, n):
        # attention!
        if n < 0:
            n &= 0xffffffff
        cnt = 0
        while n:
            n &= (n - 1)
            cnt += 1
        return cnt

"""
Q12 数值的整数次方
"""
class Q12:
    # 递归形式
    def Power(self, base, exponent):
        def _power(exp):
            if not exp:
                return 1
            if exp == 1:
                return base
            ans = _power(exp >> 1)
            ans *= ans
            if exp & 1:
                ans *= base
            return ans
        return _power(exponent) if exponent > 0 else 1 / _power(-exponent)
    # 非递归形式
    def Power01(self, base, exponent):
        exp = abs(exponent)
        ans = 1
        while exp:
            if exp & 1:
                ans *= base
            exp >>= 1
            base *= base
        return ans if exponent > 0 else 1 / ans

"""
Q13 调整数组顺序使得奇数在偶数前面
要保证稳定性，不可能在O(n)、原地情况下完成，参见【stable sort】问题
"""
class Q13:
    def reOrderArray(self, array):
        if not array or len(array) <= 1:
            return array
        # 找到第一个偶数的下标
        evenIdx = 0
        while evenIdx < len(array) and array[evenIdx] & 1:
            evenIdx += 1
        if evenIdx == len(array):
            return array
        oddIdx = evenIdx + 1
        while oddIdx < len(array):
            # 在evenIdx基础上找到第一个奇数下标
            while oddIdx < len(array) and not array[oddIdx] & 1:
                oddIdx += 1
            if oddIdx == len(array):
                break
            tmp = array[oddIdx]
            array[evenIdx + 1: oddIdx + 1] = array[evenIdx:oddIdx]
            array[evenIdx] = tmp
            evenIdx += 1
            oddIdx += 1 
        return array

"""
Q14 链表倒数第k个节点，k >= 1，最后一个节点为倒数第一个节点
"""
class Q14:
    def FindKthToTail(self, head, k):
        if not head or k <= 0:
            return None
        slow = fast = head
        while fast and k:
            fast = fast.next
            k -= 1
        if not fast and k > 0:
            return None
        while fast:
            slow = slow.next
            fast = fast.next
        return slow

"""
Q15 反转单向链表
"""
class Q15:
    def ReverseList(self, pHead):
        if not pHead or not pHead.next:
            return pHead
        # 至少有两个节点
        pre, cur = None, pHead
        while cur:
           next = cur.next
           cur.next = pre
           pre, cur = cur, next
        return pre

"""
Q16 合并两个单调递增的链表，合成后的链表满足单调不减规则
"""
class Q16:
    # 非递归形式
    def Merge(self, pHead1, pHead2):
        dummy = cur = ListNode(-1)
        while pHead1 and pHead2:
            if pHead1.val < pHead2.val:
                cur.next, pHead1 = pHead1, pHead1.next
            else:
                cur.next, pHead2 = pHead2, pHead2.next
            cur = cur.next
        cur.next = pHead1 if pHead1 else pHead2
        return dummy.next

    # 递归形式
    def Merge01(self, pHead1, pHead2):
        if not pHead1:
            return pHead2
        if not pHead2:
            return pHead1
        head = None
        if pHead1.val < pHead2.val:
            head = pHead1
            head.next = self.Merge01(pHead1.next, pHead2)
        else:
            head = pHead2
            head.next = self.Merge01(pHead1, pHead2.next)
        return head

"""
Q17 树的子结构
约定：空树不是任意树的子结构
"""
class Q17:
    # 判断pRoot2是否是pRoot1的子结构
    def HasSubtree(self, pRoot1, pRoot2):
        def isContain(root1, root2):
            # 注意以下两个if顺序不能调换
            if not root2:
                return True
            if not root1:
                return False
            if root1.val != root2.val:
                return False
            return isContain(root1.left, root2.left) and isContain(root1.right, root2.right)

        result = False
        if pRoot1 and pRoot2:
            if pRoot1.val == pRoot2.val:
                result = isContain(pRoot1, pRoot2)
            if not result:
                result = self.HasSubtree(pRoot1.left, pRoot2)
            if not result:
                result = self.HasSubtree(pRoot1.right, pRoot2)
        return result

"""
Q18 二叉树的镜像
"""
from collections import deque
class Q18:
    # 递归调用
    # 中序遍历，当访问到当前树的根节点，交换左右子树
    def Mirror(self, root):
        if not root or not root.left and not root.right:
            return None
        root.left, root.right = root.right, root.left
        if root.left:
            self.Mirror(root.left)
        if root.right:
            self.Mirror(root.right)

    # 层序遍历
    def Mirror01(self, root):
        if not root:
            return None
        q = deque()
        q.append(root)
        while q:
            head = q.popleft()
            head.left, head.right = head.right, head.left
            if head.left:
                q.append(head.left)
            if head.right:
                q.append(head.right)

"""
Q19 蛇形打印数组
输入一个矩阵，按照从外向里以顺时针的顺序依次打印。例如
    1  2  3  4
    5  6  7  8
    9  10 11 12
    13 14 15 16
则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
"""
class Q19:
    # matrix类型为二维列表，需要返回列表
    def printMatrix(self, matrix):
        res = []
        rows, cols = len(matrix), len(matrix[0])
        # 注意不要写成：
        # isVisited = [[False] * cols] * rows
        isVisited = [[False] * cols for i in range(rows)]
        res.append(matrix[0][0])
        x, y, cnt, isVisited[0][0], totals = 0, 0, 1, True, rows * cols
        while cnt < totals:
            # 从左往右
            while y + 1 < cols and not isVisited[x][y + 1]:
                y += 1
                res.append(matrix[x][y])
                isVisited[x][y], cnt = True, cnt + 1
            # 从上往下
            while x + 1 < rows and not isVisited[x + 1][y]:
                x += 1
                res.append(matrix[x][y])
                isVisited[x][y], cnt = True, cnt + 1
            # 从右往左
            while y - 1 >= 0 and not isVisited[x][y - 1]:
                y -= 1
                res.append(matrix[x][y])
                isVisited[x][y], cnt = True, cnt + 1
            # 从下往上
            while x - 1 >= 0 and not isVisited[x - 1][y]:
                x -= 1
                res.append(matrix[x][y])
                isVisited[x][y], cnt = True, cnt + 1
        return res

"""
Q20 包含min函数的栈
"""
class Q20:
    def __init__(self):
        self._helper = []
        self._data = []
    def push(self, node):
        self._data.append(node)
        if not self._helper or self._helper[-1] > node:
            self._helper.append(node)
        else:
            self._helper.append(self._helper[-1])

    def pop(self):
        self._helper.pop()
        return self._data.pop()

    def top(self):
        return self._data[-1]

    def min(self):
        return self._helper[-1]