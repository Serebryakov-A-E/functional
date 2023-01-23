import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        v1(scanner);
    }

    public static void v1(Scanner scanner) {
        List<String> words = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        System.out.print("Введите текст:");
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (words.contains(str)) {
                int index = words.indexOf(str);
                values.set(index, values.get(index) + 1);
            } else {
                words.add(str);
                values.add(1);
            }
        }
        //пузырьковая сортировка
        for (int i = 0; i < values.size() - 1; i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                if (values.get(j) < values.get(j + 1)) {
                    int temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                    //сортировка массива строк параллельно
                    String tempStr = words.get(i);
                    words.set(j, words.get(j + 1));
                    words.set(j + 1, tempStr);
                }
            }
        }
        //вывод результата
        System.out.printf("В тексте %d слов\n", words.size());
        System.out.println("TOP10:");

        try {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%d — %s\n", values.get(i), words.get(i));
            }
        } catch (IndexOutOfBoundsException ignore) {}
    }

    public static void v2(Scanner scanner) {
        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String, Integer> empty = new LinkedHashMap<>();

        list.forEach(stringIntegerEntry -> {
            empty.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        });

        ArrayList<String> wordsArr = new ArrayList<>();
        System.out.println("---------------------------------");
        int uniqueWords = map.keySet().size();
        System.out.printf("В тексте %d слов\n", uniqueWords);
        System.out.println("TOP10:");
        empty.forEach((str, integer) -> {
            wordsArr.add((integer + " - " + str));
        });
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(wordsArr.get(i));
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    public static void v3(Scanner scanner) {
        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
        int uniqueWords = map.keySet().size();
        ArrayList<String> wordsArr1 = new ArrayList<>();
        System.out.println("---------------------------------");
        System.out.printf("В тексте %d слов\n", uniqueWords);
        System.out.println("TOP10:");
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(stringIntegerEntry -> {
                    wordsArr1.add(stringIntegerEntry.getValue() + " - " + stringIntegerEntry.getKey());
                });

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(wordsArr1.get(i));
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
    }
}
