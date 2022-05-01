package com.linepro.modellbahn.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.service.NameGenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractNameGenerator implements NameGenerator {

    private static final Pattern WITH_BRACKETS = Pattern.compile("^.*\\(([^/\\)]+)(?>[^\\)]*)\\).*$");
    private static final String HYPHENATE = "[ „\\(’]";

    private final String permittedChars;

    private final boolean withBrackets;

    private final int maxLength;

    /**
     * generates a name for the description
     * @param description the description
     * @param permittedChars a class REGEX [...] containing the permitted characters, trailing quantifiers are ignored
     * @param maxLength the maximum length
     * @return the description with accents stripped, filtered by permittedChars and truncated to maxLength characters
     */
    @Override
    public String generate(String description) {
        Matcher matcher = WITH_BRACKETS.matcher(description);

        String input = (withBrackets && matcher.matches() ? matcher.group(1) : description).replaceAll(HYPHENATE, "-");

        input = input.replace("--", "-");
        input = input.startsWith("-") ? input.substring(1) : input;
        input = input.endsWith("-") ? input.substring(0, input.length()-1) : input;

        return StringUtils.truncate(
                    StringUtils.stripAccents(input)
                               .toUpperCase()
                               .replaceAll(negate(permittedChars), ""),
                    maxLength
                    );
    }

    /**
     * changes a positive pattern [...] into a negative pattern [^...], removing trailing quantifiers.
     * @param permittedChars a class REGEX [...] containing the permitted characters, trailing quantifiers are ignored
     * @return the negated pattern
     */
    protected String negate(String pattern) {
        int end = pattern.lastIndexOf("]");
        int start = pattern.indexOf("[");

        return "[^" + pattern.substring(start + 1, end - start + 1);
    }
}
