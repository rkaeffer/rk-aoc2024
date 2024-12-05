package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Day5 {

    public static long getSumOfMiddlePage(List<String> input, boolean pageWithUpdate) {
        PrintInstr printInstr = new PrintInstr(input);
        printInstr.validUpdates();
        long sumMiddle = 0L;
        for(NewInstr newInstr : printInstr.validUpdates) {
            if(newInstr.hasUpdate == pageWithUpdate) {
                sumMiddle  += newInstr.newInstr.get(newInstr.newInstr.size() / 2);
            }
        }
        return sumMiddle;
    }

    static class PrintInstr {
        List<OrderRule> orderRules;

        List<List<Integer>> updates;

        List<NewInstr> validUpdates;

        public PrintInstr(List<String> input) {
            this.orderRules = new ArrayList<>();
            this.updates = new ArrayList<>();

            int i=0;
            while(!input.get(i).equals("")) {
                String[] rule = input.get(i).split("\\|");
                orderRules.add(new OrderRule(Integer.parseInt(rule[0]),Integer.parseInt(rule[1])));
                i++;
            }

            i++;

            while(i<input.size()) {
                String[] instr = input.get(i).split(",");
                updates.add(new ArrayList<>(Arrays.stream(input.get(i).split(",")).map(Integer::parseInt).collect(Collectors.toList())));
                i++;
            }

            this.validUpdates = new ArrayList<>();
        }

        public void validUpdates() {
            for(List<Integer> update : updates) {
                this.validUpdates.add(validatePrintInstr(update));
            }
            fixInstr();
        }

        public NewInstr validatePrintInstr(List<Integer> update) {
            List<Integer> newInstr = new ArrayList<>();
            List<Integer> removes = new ArrayList<>();

            boolean hasUpdate = false;
            for(int pageNumber : update) {
                List<Integer> mustBePrintAfter = mustBePrintAfter(pageNumber);
                boolean valid = true;
                for(int page : mustBePrintAfter) {
                    if(newInstr.contains(page)) {
                        valid = false;
                        hasUpdate = true;
                        removes.add(pageNumber);
                        break;
                    }
                }
                if(valid) {
                    newInstr.add(pageNumber);
                }
            }

            return new NewInstr(newInstr, hasUpdate, removes);
        }

        public void fixInstr() {
            for(NewInstr newInstr : this.validUpdates) {
                if(newInstr.hasUpdate) {
                    for(Integer remove : newInstr.removes) {
                        List<Integer> mustBePrintAfter = mustBePrintAfter(remove);
                        List<Integer> mustBePrintBefore = mustBePrintBefore(remove);

                        for(int i=0; i<newInstr.newInstr.size(); i++) {
                            boolean beforeOk = true;
                            boolean afterOk = true;
                            //CheckBefore
                            for(int j=0; j<i; j++) {
                                if(mustBePrintAfter.contains(newInstr.newInstr.get(j))) {
                                    beforeOk = false;
                                    break;
                                }
                            }
                            //CheckAfter
                            for(int j=i; j<newInstr.newInstr.size(); j++) {
                                if(mustBePrintBefore.contains(newInstr.newInstr.get(j))) {
                                    afterOk = false;
                                    break;
                                }
                            }
                            if(beforeOk && afterOk) {
                                newInstr.newInstr.add(i, remove);
                                break;
                            }
                        }
                    }
                }
            }
        }

        public List<Integer> mustBePrintAfter(Integer pageNumber) {
            List<Integer> mustBePrintAfter = new ArrayList<>();
            for(OrderRule rule : orderRules) {
                if(rule.firstPage == pageNumber) {
                    mustBePrintAfter.add(rule.secondPage);
                }
            }
            return mustBePrintAfter;
        }

        public List<Integer> mustBePrintBefore(Integer pageNumber) {
            List<Integer> mustBePrintBefore = new ArrayList<>();
            for(OrderRule rule : orderRules) {
                if(rule.secondPage == pageNumber) {
                    mustBePrintBefore.add(rule.firstPage);
                }
            }
            return mustBePrintBefore;
        }
    }

    static class NewInstr {
        List<Integer> newInstr;
        boolean hasUpdate;

        List<Integer> removes;

        public NewInstr(List<Integer> newInstr, boolean hasUpdate, List<Integer> removes) {
            this.newInstr = newInstr;
            this.hasUpdate = hasUpdate;
            this.removes = removes;
        }

    }

    static class OrderRule {

        int firstPage;
        int secondPage;

        public OrderRule(int firstPage, int secondPage) {
            this.firstPage = firstPage;
            this.secondPage = secondPage;
        }
    }
}
