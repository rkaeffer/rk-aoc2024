package fr.rk.aoc.challenge;

import java.util.*;
import java.util.stream.Collectors;

public final class Day2 {

    public static long getNbSafeReport(List<String> input, boolean tolerance) {

        List<Report> reports = new ArrayList<>();
        long nbSafe = 0L;

        input.forEach(s -> {
            reports.add(new Report(s));
        });

        for (Report report : reports) {
            if(report.isReportValid()) {
                nbSafe++;
            } else if(tolerance) {
                List<Long> initial = report.levels;
                for(int i=0; i<initial.size(); i++) {
                    List<Long> newLevels = new ArrayList<>(initial);
                    newLevels.remove(i);
                    Report r = new Report(newLevels);
                    if(r.isReportValid()) {
                        nbSafe++;
                        break;
                    }
                }
            }
        }
        return nbSafe;
    }

    static class Report {

        List<Long> levels;

        public Report(String level) {
            this.levels = Arrays.stream(level.split(" ")).map(Long::parseLong).collect(Collectors.toList());
        }

        public Report(List<Long> levels) {
            this.levels = levels;
        }

        public boolean isReportValid() {

            if (Objects.equals(levels.get(0), levels.get(1))) {
                return false;
            }

            boolean increasing = levels.get(0) < levels.get(1);
            boolean isReportSafe = true;

            for (int i = 0; i < levels.size() - 1; i++) {
                if (Objects.equals(levels.get(i), levels.get(i + 1))) {
                    isReportSafe = false;
                    break;
                }
                if (increasing) {
                    if (levels.get(i) > levels.get(i + 1)) {
                        isReportSafe = false;
                        break;
                    }
                } else {
                    if (levels.get(i) < levels.get(i + 1)) {
                        isReportSafe = false;
                        break;
                    }
                }
                if (Math.abs(levels.get(i) - levels.get(i + 1)) > 3) {
                    isReportSafe = false;
                    break;
                }
            }
            return isReportSafe;
        }
    }
}
