package dev.cables;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {
    private final String pattern;
    private final Map<Character, Item> patternMap;

    public Recipe(String pattern, Item... items) {
        if (pattern.length() != 9) {
            throw new IllegalArgumentException("Pattern must be exactly 9 characters long");
        }
        if (items.length > 9) {
            throw new IllegalArgumentException("Cannot have more than 9 items for the pattern");
        }

        this.pattern = pattern;
        this.patternMap = new HashMap<>();

        List<Character> patternKeys = new ArrayList<>();
        for (char c : pattern.toCharArray()) {
            if (!patternKeys.contains(c) && c != ' ') {
                patternKeys.add(c);
            }
        }

        if (patternKeys.size() != items.length) {
            throw new IllegalArgumentException("Number of unique pattern keys must match the number of items");
        }

        for (int i = 0; i < patternKeys.size(); i++) {
            patternMap.put(patternKeys.get(i), items[i]);
        }
    }

    public String[] getPattern() {
        return new String[] {
                pattern.substring(0, 3),
                pattern.substring(3, 6),
                pattern.substring(6, 9)
        };
    }

    public Map<Character, Item> getPatternMap() {
        return patternMap;
    }
}
