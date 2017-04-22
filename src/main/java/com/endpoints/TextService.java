package com.endpoints;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextService {

    protected static String camelRegex(String snakeCase) {
        String regex = "_([a-zA-Z])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(snakeCase);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String camelize(String snakeCase) {
        return camelRegex(snakeCase);
    }

    public static String camelize(String snakeCase, boolean initialCap) {
        String camelString = camelRegex(snakeCase);
        return initialCap ?
                Character.toUpperCase(camelString.charAt(0)) + camelString.substring(1) :
                camelString;
    }

    public static String redact(String original, String badWord) {
        Character replaceSymbol = '*';
        String regex = badWord;
        String replace = new String(new char[badWord.length()]).replace('\0', replaceSymbol);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(original);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replace);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Map<Character, Character> buildHash(String key) {
        String abcKeys = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Character> myMap = new HashMap<>();
        for (int i = 0; i < key.length(); i++) {
            myMap.put(abcKeys.charAt(i), key.charAt(i));
        }
        return myMap;
    }

    private static String encodeWord(String word, Map<Character, Character> map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<word.length(); i ++)
        {
            sb.append(map.get(word.charAt(i)));
        }
        return sb.toString();
    }

    public static String encodeMessage(String message, String key) {
        Map <Character, Character> myMap = buildHash(key);
        String[] messageWords = message.split("\\s+");
        List<String> messageWordList = Arrays.asList(messageWords);

        StringBuilder sb = new StringBuilder();

        for (Iterator iterator = messageWordList.iterator(); iterator.hasNext();)
        {
            sb.append(encodeWord(
                    iterator.next().toString(),
                    myMap));
            if (iterator.hasNext()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static String replaceAll(String original, String a, String b)  {
        return original.replaceAll(a, b);
    }
}
