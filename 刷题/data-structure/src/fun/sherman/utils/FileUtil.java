package fun.sherman.utils;

import fun.sherman.set.Set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 文件工具类
 * Created on 2020/4/27
 *
 * @author sherman tang
 */
public class FileUtil {

    public static int countWordFromFile(String fileName, Set<String> words) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        Scanner scanner = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            scanner = new Scanner(bis, "UTF-8");
            while (scanner.hasNext()) {
                String line = scanner.useDelimiter("\\A").next(); // \\A --> \\^
                Arrays.stream(line.split(" ")).forEach(words::add);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return words.size();
    }

    public static List<String> getAllWords(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        Scanner scanner = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            scanner = new Scanner(bis, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.useDelimiter("\\A").nextLine().trim(); // \\A --> \\^
                if (!"".equals(line)) {
                    words.addAll(Arrays.asList(line.split(" ")));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return words;
    }

}
