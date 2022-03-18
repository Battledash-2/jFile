package main;

import main.util.FileUtil;
import main.util.JSONUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void test() throws IOException {
        FileUtil.FileWrapper file = FileUtil.open("./test.txt");
        file.append("Hello, world!\nWhat's up?");
    }
    public static void main(String[] _arg) throws Exception {
//        test();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Key:\n     ");
        String key = scanner.nextLine();

        System.out.print("Value:\n     ");
        String value = scanner.nextLine();

        FileUtil.FileWrapper file = FileUtil.open("./test.json", "{}");
        String fileContent = file.read();
        if (fileContent == "") return;
        Map obj = JSONUtil.mapParse(fileContent);

        obj.put(key, value);
        file.write(JSONUtil.stringify(obj));

        scanner.close();
    }
}
