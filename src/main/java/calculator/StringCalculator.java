package calculator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String SPLIT_TOKEN = ",|:";
    private static final String CUSTOM_SPLIT_TOKEN = "//(.)\n(.*)";
    private static final String NUMERIC_REG_EXP = "^[0-9]+$";


    public static int splitAndSum(String text) {
        if(nullOrEmpty(text)) {
            return 0;
        }

        return customSeparatorSplit(text);
    }

    private static boolean nullOrEmpty(String text) {
        if(Objects.isNull(text) || text.equals("")) {
            return true;
        }
        return false;
    }

    private static int normalSplit(String text) {
        String[] results = text.split(SPLIT_TOKEN);
        return sum(results);
    }

    private static int customSeparatorSplit(String text) {
        Matcher matcher = Pattern.compile(CUSTOM_SPLIT_TOKEN).matcher(text);

        if(!matcher.find()) {
            return normalSplit(text);
        }
        String customSeparator = matcher.group(1);

        String[] results = matcher.group(2).split(customSeparator);

        return sum(results);
    }

    private static int sum(String[] text) {
        int sum = 0;
        for(int i = 0; i < text.length; i++) {
            sum += isPlusNumber(text[i]);
        }
        return sum;
    }

    private static int isPlusNumber(String text) {
        if(!text.matches(NUMERIC_REG_EXP)) {
            throw new RuntimeException("음수나 문자는 더할수가 없습니다.");
        }
        return Integer.parseInt(text);
    }
}
