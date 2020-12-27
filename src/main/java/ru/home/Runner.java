package ru.home;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    // все кейсы - для вновь разработанного метода spacesWinNew
    public static void main(String[] args) {
        // case1: ' A A ' => 'A   A'
        String input1 = " A A ";
        String result1 = transorm(input1);

        System.out.println(String.format("case1: '%s' => '%s'", input1, result1));
        //----------------------------------------------

        // case2: ' A  A A  A A ' => 'A  A  A  A  A'
        String input2 = " A  A A  A A ";
        String result2 = transorm(input2);

        System.out.println(String.format("case2: '%s' => '%s'", input2, result2));
        //----------------------------------------------

        // case3: ' A  A   A A ' => 'A   A   A  A'
        String input3 =  " A  A   A A ";
        String result3 = transorm(input3);

        System.out.println(String.format("case3: '%s' => '%s'", input3, result3));
        //----------------------------------------------

        // case4: ' A     AAA AAAA  A A ' => 'A A A A  A  A A A A A'
        String input4 =  " A     AAA AAAA  A A ";
        String result4 = transorm(input4);

        System.out.println(String.format("case4: '%s' => '%s'", input4, result4));
        //----------------------------------------------

        // case5: ' A     AAA AAAA   A A A ' => 'A A A A A  A  A  A A A A'
        String input5 =  " A     AAA AAAA   A A A ";
        String result5 = transorm(input5);

        System.out.println(String.format("case5: '%s' => '%s'", input5, result5));
        //----------------------------------------------
    }

    static String transorm(String input) {
        if(StringUtils.isBlank(input)) {
            throw new IllegalStateException("String is empty");
        }

        if(!input.startsWith(" ") || !input.endsWith(" ")) {
            throw new IllegalStateException("String has wrong border symbols");
        }

        if(0 == 0) { // todo дописать проверку о том что должно быть минимум 2 пробела

        }

        // todo проверка на то что строка не содержит ничего кроме пробелов и букв


        // обработка строки

        int spaceCount = 0;
        List<String> lettersList = new ArrayList<>();

        for(int i = 0, n = input.length() ; i < n ; i++) {
            char currentLetter = input.charAt(i);

            if(32 == currentLetter) { // todo если пробел, то делаем инкремент счетчика
                spaceCount++;
            } else {
                lettersList.add(new String(String.valueOf(currentLetter)));
            }
        }

        int compareResult = Integer.compare(lettersList.size(), spaceCount);

        switch (compareResult) {
            case -1: return spacesWinNew(spaceCount, lettersList);
            case 0: return numbersEqual(lettersList);
            case 1: return lettersWin(spaceCount, lettersList);
            default: throw new IllegalStateException("Illegal situation");
        }
    }

    static String numbersEqual(List<String> lettersList) {
        return "";
    }

    /*
    Реализация метода обработки для кейса с преобладающим кол-ом пробелов
     */
    static String spacesWinNew(int spaceCount, List<String> lettersList) {
        StringBuilder builder = new StringBuilder();

        int partitionsCount = lettersList.size() - 1;
        int baseCount = spaceCount / partitionsCount;
        int restOfSpaces = spaceCount - (baseCount * partitionsCount);

        boolean partitionsDevidableBy2 = partitionsCount % 2 == 0;

        int middle = partitionsDevidableBy2
                ? (partitionsCount / 2)
                : (partitionsCount / 2) + 1;

        /*
        - убираем единицу чтобы использовать значение при обходе листа
        - Math.max - чтобы не уйти ниже 0
         */
        middle = Math.max(middle - 1, 0);

        int leftExtraBound = middle - (restOfSpaces / 2 - (partitionsDevidableBy2 ? 1 : 0));
        int rightExtraBound = leftExtraBound + restOfSpaces - 1;

        int counter = 0;

        for(int i = 0; i < lettersList.size() - 1; i++) {
            String letter = lettersList.get(i);

            boolean extra = counter >= leftExtraBound && counter <= rightExtraBound;
            int finalAddCount = extra ? baseCount + 1 : baseCount;

            builder.append(letter);

            while (finalAddCount > 0) {
                builder.append(" ");
                finalAddCount--;
            }

            counter++;
        }

        builder.append(lettersList.get(lettersList.size() - 1));

        return builder.toString();
    }

    static String spacesWinOld(int spaceCount, List<String> lettersList) {
        int partitionsCount = lettersList.size() - 1;

        StringBuilder builder = new StringBuilder();

        int baseCount = spaceCount / partitionsCount; // todo проверить результат
        int restOfSpaces = spaceCount - (baseCount * partitionsCount);
        int restofExtraOnes = 0;


        for(int i = 0, n = lettersList.size() ; i < n - 1 ; i++) {
            String currentLetter = lettersList.get(0);

            int finalCoun;

            if(restofExtraOnes < restOfSpaces) {
                finalCoun = baseCount + 1;
                restofExtraOnes--;
            } else {
                finalCoun = baseCount;
            }


            builder.append(currentLetter);
            // todo решить как аппенгдить пробелы



        }

        String lastLetter = lettersList.get(lettersList.size() - 1);
        builder.append(lastLetter);

        for(String letter : lettersList) {

        }

        return builder.toString();
    }

    static String lettersWin(int spaceCount, List<String> lettersList) {
        StringBuilder builder = new StringBuilder();

        int pairCounter = 0;

        for (String letter : lettersList) {
            pairCounter++;
            builder.append(letter).append(" ");

            if (pairCounter == spaceCount) {
                break;
            }
        }

        List<String> restOfLetters = lettersList.subList(spaceCount, lettersList.size());

        for(String letter : restOfLetters) {
            builder.append(letter);
        }

        return builder.toString();
    }
}
