package fr.rk.aoc.challenge;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day3 {

    public static long getSumOfMulInstr(List<String> lines, boolean withFilter) {

        Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern filter = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(do|don't)\\(\\)");

        long sum = 0L;

        if(!withFilter) {
            for(String s : lines) {
                Matcher m = p.matcher(s);
                while(m.find()) {
                    sum+=Long.parseLong(m.group(1))*Long.parseLong(m.group(2));
                }
            }
        } else {
            boolean enabled = true;
            for(String s : lines) {
                Matcher m = filter.matcher(s);
                while(m.find()) {
                    if(m.group(0).startsWith("mul")) {
                        if(enabled) {
                            sum+=Long.parseLong(m.group(1))*Long.parseLong(m.group(2));
                        }
                    } else {
                        enabled = !m.group(0).startsWith("don't");
                    }
                }
            }
        }

        return sum;
    }
}
