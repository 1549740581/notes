package fun.sherman.avl;

import fun.sherman.map.BSTImplMap;
import fun.sherman.utils.FileUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * 比较AVL树和BST树差距
 * Created on 2020/5/3
 *
 * @author sherman tang
 */
public class AVLTest {
    public static void main(String[] args) {
        List<String> words = FileUtil.getAllWords("pride-and-prejudice.txt");
        AVL<String, Integer> avl = new AVL<>();
        BSTImplMap<String, Integer> bst = new BSTImplMap<>();

        Instant now = Instant.now();
        for (String word : words) {
            if (bst.contains(word)) {
                bst.put(word, bst.get(word) + 1);
            } else {
                bst.add(word, 1);
            }
        }
        for (String word : words) {
            bst.contains(word);
        }
        System.out.println("bst time: " + Duration.between(now, Instant.now()).toMillis());

        now = Instant.now();
        for (String word : words) {
            if (avl.contains(word)) {
                avl.put(word, avl.get(word) + 1);
            } else {
                avl.add(word, 1);
            }
        }
        for (String word : words) {
            avl.contains(word);
        }
        System.out.println("avl time: " + Duration.between(now, Instant.now()).toMillis());
        System.out.println("AVL is binary search tree: " + avl.isBST());
        System.out.println("AVL is balanced tree: " + avl.isBalanceTree());
        System.out.println(avl.size() == bst.size());

        // 验证是否删除过程中是否保证平衡性
        for(String word: words) {
            avl.remove(word);
            if (!avl.isBST() || !avl.isBalanceTree()) {
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("Done!");
    }
}
