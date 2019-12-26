# -*- coding:utf-8 -*-
"""
二叉树节点
"""
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

"""
链表节点
"""
class ListNode:
    def __init__(self, val):
        self.val = val 
        self.next = None

"""
复杂链表
"""
class RandomListNode:
    def __init__(self, label):
        self.label = label
        self.next = None
        self.random = None
