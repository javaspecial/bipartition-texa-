package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TxtFileReader {

    public static void main(String[] args) throws ParseException {
        Instant start = Instant.now();

        int s = 0;
        int v = 0;
        BufferedReader br = null;
        BufferedReader readLine = null;
        ArrayList<String> mainData = new ArrayList<String>();

        try {

            String arr[] = {"A", "B"};
            String sCurrentLine, mainLine;
            Set<String> set = new HashSet<String>();
            ArrayList<String> partOne = new ArrayList<String>();
            ArrayList<String> partTwo = new ArrayList<String>();

            br = new BufferedReader(new FileReader("src/txtfilereader/Test.txt"));

            while ((sCurrentLine = br.readLine()) != null) {

                mainLine = sCurrentLine;
                sCurrentLine = sCurrentLine.replace("(", "");
                sCurrentLine = sCurrentLine.replace(")", "");

                sCurrentLine = sCurrentLine.replace(";", "");
                sCurrentLine = sCurrentLine.trim();
                String split[] = sCurrentLine.split(",");

                for (int j = 0; j < split.length; j++) {

                    if (partOne.isEmpty() && partTwo.isEmpty()) {
                        partOne.add(split[0]);
                        partOne.add(split[1]);
                        partTwo.add(split[2]);
                        partTwo.add(split[3]);

                    } else {
                        if (j == 0) {

                            if (partTwo.contains(split[1]) && !partOne.contains(split[0])) {

                                if (!partTwo.contains(split[0])) {
                                    partTwo.add(split[0]);
                                }
                            } else if (partOne.contains(split[1]) && !partTwo.contains(split[0])) {
                                if (!partOne.contains(split[0])) {
                                    partOne.add(split[0]);
                                }
                            } else if (!partTwo.contains(split[1]) && !partOne.contains(split[1])) {
                                if (partTwo.contains(split[2])) {
                                    if (!partOne.contains(split[0])) {
                                        partOne.add(split[0]);
                                    }
                                } else if (partOne.contains(split[2])) {
                                    if (!partTwo.contains(split[0])) {
                                        partTwo.add(split[0]);
                                    }
                                } else if (partTwo.contains(split[3])) {
                                    if (!partOne.contains(split[0])) {
                                        partOne.add(split[0]);
                                    }
                                } else if (partOne.contains(split[3])) {
                                    if (!partTwo.contains(split[0])) {
                                        partTwo.add(split[0]);
                                    }
                                }
                            }
                        } else if (j == 2) {

                            if (partTwo.contains(split[j + 1]) && !partOne.contains(split[j])) {
                                //System.out.append(partOne.toString());
                                if (!partTwo.contains(split[j])) {
                                    partTwo.add(split[j]);
                                }
                            }
                            if (partOne.contains(split[j + 1]) && !partTwo.contains(split[j])) {
                                if (!partOne.contains(split[j])) {
                                    partOne.add(split[j]);
                                }
                            } else if (!partTwo.contains(split[j + 1]) && !partOne.contains(split[j + 1])) {
                                if (partTwo.contains(split[j - 1])) {
                                    if ((!partOne.contains(split[j])) && (!partTwo.contains(split[j]))) {
                                        partOne.add(split[j]);
                                    }
                                } else if (partOne.contains(split[j - 1])) {
                                    if ((!partTwo.contains(split[j])) && !partOne.contains(split[j])) {
                                        partTwo.add(split[j]);
                                    }
                                } else if (partTwo.contains(split[j - 2])) {
                                    if ((!partOne.contains(split[j])) && (!partTwo.contains(split[j]))) {
                                        partOne.add(split[j]);
                                    }
                                } else if (partOne.contains(split[j - 2])) {
                                    if ((!partTwo.contains(split[j])) && (!partOne.contains(split[j]))) {
                                        partTwo.add(split[j]);
                                    }
                                }
                            }
                        } else if ((j == 1) || (j == 3)) {
                            if (partTwo.contains(split[j - 1])) {
                                if ((!partOne.contains(split[j])) && (!partTwo.contains(split[j]))) {
                                    partTwo.add(split[j]);
                                }
                            } else if (partOne.contains(split[j - 1])) {
                                if ((!partTwo.contains(split[j])) && (!partOne.contains(split[j]))) {
                                    partOne.add(split[j]);
                                }
                            }
                        }
                    }
                }
                mainData.add(mainLine);
            }

            set.addAll(partOne);
            set.addAll(partTwo);
            System.out.println(set.toString());
            System.out.println("Part One...");
            System.out.println(partOne.toString());

            System.out.println("Part Two...");
            System.out.println(partTwo.toString());

            // Initial Score calculation...........
            readLine = new BufferedReader(new FileReader("src/txtfilereader/Test.txt"));

            for (int i = 0; i < mainData.size(); i++) {

                sCurrentLine = mainData.get(i);
                sCurrentLine = sCurrentLine.replace("(", "");
                sCurrentLine = sCurrentLine.replace(")", "");
                sCurrentLine = sCurrentLine.replace(";", "");
                sCurrentLine = sCurrentLine.trim();
                String split[] = sCurrentLine.split(",");

                if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                    s++;
                } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                    s++;
                } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                    v++;
                } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                    v++;
                } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                    v++;
                } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                    v++;
                }

            }

            System.out.println("Satisfied: " + s);
            System.out.println("Violated: " + v);
            int ini = s - v;
            System.out.println("Score: " + ini);

            // Gain Calculation
            ArrayList<String> destOne = new ArrayList<String>();
            destOne.addAll(partOne);

            ArrayList<String> destTwo = new ArrayList<String>();
            destTwo.addAll(partTwo);

            int totalItem = 0;

            ArrayList<String> completedItem = new ArrayList<String>();
            ArrayList<String> tempItemList = new ArrayList<String>();
            ArrayList<Integer> tempGainList = new ArrayList<Integer>();

            ArrayList<String> finalPartOneList = new ArrayList<String>();
            ArrayList<String> finalPartTwoList = new ArrayList<String>();

            ArrayList<Integer> cumulativeGain = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> storeCumulativeGain = new ArrayList<ArrayList<Integer>>();

            ArrayList<Integer> gainList = new ArrayList<Integer>();

            ArrayList<ArrayList<String>> texa2DPartOneList = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> texa2DPartTwoList = new ArrayList<ArrayList<String>>();

            int finalScore;
            int flag;
            System.out.println("HI all........" + (partOne.size() + partTwo.size()));

            int targetGain = 1;
            int gainCheck = 0;
            String finalLeaf = "";
            while (targetGain != 0) {

                totalItem = 0;
                while (totalItem < (partOne.size() + partTwo.size())) {

                    System.out.println("Phase : " + (totalItem + 1));
                    flag = 0;
                    for (int i = 0; i < partOne.size(); i++) {

                        if (!completedItem.contains(partOne.get(i))) {
                            flag = 1;
                            s = 0;
                            v = 0;
                            destOne.clear();
                            destOne.addAll(partOne);

                            destTwo.clear();
                            destTwo.addAll(partTwo);
                            destTwo.add(destOne.get(i));
                            destOne.remove(i);
                            readLine = new BufferedReader(new FileReader("src/txtfilereader/Test.txt"));
                            System.out.println("PartOne: " + destOne.toString());
                            System.out.println("PartTwo: " + destTwo.toString());

                            for (int j = 0; j < mainData.size(); j++) {

                                sCurrentLine = mainData.get(j);
                                sCurrentLine = sCurrentLine.replace("(", "");
                                sCurrentLine = sCurrentLine.replace(")", "");
                                sCurrentLine = sCurrentLine.replace(";", "");
                                sCurrentLine = sCurrentLine.trim();
                                String split[] = sCurrentLine.split(",");

                                if (destOne.contains(split[0]) && destOne.contains(split[1]) && destTwo.contains(split[2]) && destTwo.contains(split[3])) {
                                    s++;
                                } else if (destTwo.contains(split[0]) && destTwo.contains(split[1]) && destOne.contains(split[2]) && destOne.contains(split[3])) {
                                    s++;
                                } else if (destOne.contains(split[0]) && destTwo.contains(split[1]) && destOne.contains(split[2]) && destTwo.contains(split[3])) {
                                    v++;
                                } else if (destTwo.contains(split[0]) && destOne.contains(split[1]) && destTwo.contains(split[2]) && destOne.contains(split[3])) {
                                    v++;
                                } else if (destOne.contains(split[0]) && destTwo.contains(split[1]) && destTwo.contains(split[2]) && destOne.contains(split[3])) {
                                    v++;
                                } else if (destTwo.contains(split[0]) && destOne.contains(split[1]) && destOne.contains(split[2]) && destTwo.contains(split[3])) {
                                    v++;
                                }
                            }
                            finalScore = s - v;
                            finalScore = finalScore - ini;
                            tempItemList.add(partOne.get(i));
                            tempGainList.add(finalScore);
                            System.out.println("Sati: " + s);
                            System.out.println("Vio: " + v);
                            System.out.println("Ini: " + ini);
                            System.out.println("Score Of " + partOne.get(i) + " : " + finalScore);
                        }
                    }

                    System.out.println("Second Part Gain Calculation........");

                    for (int i = 0; i < partTwo.size(); i++) {

                        if (!completedItem.contains(partTwo.get(i))) {
                            System.out.println("PartOneOriginalCheck: " + partOne.toString());
                            flag = 1;
                            s = 0;
                            v = 0;
                            destOne.clear();
                            destOne.addAll(partOne);

                            destTwo.clear();
                            destTwo.addAll(partTwo);
                            destOne.add(destTwo.get(i));
                            destTwo.remove(i);
                            readLine = new BufferedReader(new FileReader("src/txtfilereader/Test.txt"));
                            System.out.println("PartOneOriginal: " + partOne.toString());
                            System.out.println("PartOne: " + destOne.toString());
                            System.out.println("PartTwo: " + destTwo.toString());

                            for (int j = 0; j < mainData.size(); j++) {

                                sCurrentLine = mainData.get(j);
                                sCurrentLine = sCurrentLine.replace("(", "");
                                sCurrentLine = sCurrentLine.replace(")", "");
                                sCurrentLine = sCurrentLine.replace(";", "");
                                sCurrentLine = sCurrentLine.trim();
                                String split[] = sCurrentLine.split(",");

                                if (destOne.contains(split[0]) && destOne.contains(split[1]) && destTwo.contains(split[2]) && destTwo.contains(split[3])) {
                                    s++;
                                } else if (destTwo.contains(split[0]) && destTwo.contains(split[1]) && destOne.contains(split[2]) && destOne.contains(split[3])) {
                                    s++;
                                } else if (destOne.contains(split[0]) && destTwo.contains(split[1]) && destOne.contains(split[2]) && destTwo.contains(split[3])) {
                                    v++;
                                } else if (destTwo.contains(split[0]) && destOne.contains(split[1]) && destTwo.contains(split[2]) && destOne.contains(split[3])) {
                                    v++;
                                } else if (destOne.contains(split[0]) && destTwo.contains(split[1]) && destTwo.contains(split[2]) && destOne.contains(split[3])) {
                                    v++;
                                } else if (destTwo.contains(split[0]) && destOne.contains(split[1]) && destOne.contains(split[2]) && destTwo.contains(split[3])) {
                                    v++;
                                }
                            }

                            finalScore = s - v;
                            finalScore = finalScore - ini;
                            tempItemList.add(partTwo.get(i));
                            tempGainList.add(finalScore);
                            System.out.println("Score Of " + partTwo.get(i) + " : " + finalScore);
                        }
                    }

                    if (flag == 1) {

                        ArrayList<String> singletonTexaList = new ArrayList<String>();
                        ArrayList<Integer> singletonGainList = new ArrayList<Integer>();

                        while (true) {
                            int exit_flag = 0;
                            ArrayList<Integer> satisfiedTexaList = new ArrayList<Integer>();
                            ArrayList<String> MaxItemList = new ArrayList<String>();

                            int index = tempGainList.indexOf(Collections.max(tempGainList));
                            int maxGain = Collections.max(tempGainList);
                            int count = 0;
                            ArrayList<String> tempMaxMulItem = new ArrayList<String>();
                            for (int k = 0; k < tempGainList.size(); k++) {
                                if (maxGain == tempGainList.get(k)) {
                                    count++;
                                    tempMaxMulItem.add(tempItemList.get(k));
                                }
                            }
                            if (count > 1) {
                                System.out.println("Multiple Max Gain" + tempMaxMulItem.toString());
                                // Check for multiple and check bipartaion wiht hieghst satisfication
                                ArrayList<String> tempPartOne = new ArrayList<String>();
                                ArrayList<String> tempPartTwo = new ArrayList<String>();

                                int mulValues = 0;
                                for (int m = 0; m < tempMaxMulItem.size(); m++) {

                                    tempPartOne.clear();
                                    tempPartTwo.clear();
                                    tempPartOne.addAll(partOne);
                                    tempPartTwo.addAll(partTwo);

                                    if (tempPartOne.contains(tempMaxMulItem.get(m))) {
                                        tempPartTwo.add(tempMaxMulItem.get(m));
                                        tempPartOne.remove(tempMaxMulItem.get(m));
                                    } else {
                                        tempPartOne.add(tempMaxMulItem.get(m));
                                        tempPartTwo.remove(tempMaxMulItem.get(m));
                                    }

                                    if (tempPartOne.size() <= 1 || tempPartTwo.size() <= 1) {
                                        System.out.println("Multiple Max Gain length less than 1");
                                    } else {
                                        System.out.println("Multiple Values.............");
                                        s = 0;
                                        mulValues = 1;
                                        //readLine = new BufferedReader(new FileReader("Test.txt"));

                                        for (int j = 0; j < mainData.size(); j++) {

                                            sCurrentLine = mainData.get(j);
                                            sCurrentLine = sCurrentLine.replace("(", "");
                                            sCurrentLine = sCurrentLine.replace(")", "");
                                            sCurrentLine = sCurrentLine.replace(";", "");
                                            sCurrentLine = sCurrentLine.trim();
                                            String split[] = sCurrentLine.split(",");

                                            if (tempPartOne.contains(split[0]) && tempPartOne.contains(split[1]) && tempPartTwo.contains(split[2]) && tempPartTwo.contains(split[3])) {
                                                s++;
                                            } else if (tempPartTwo.contains(split[0]) && tempPartTwo.contains(split[1]) && tempPartOne.contains(split[2]) && tempPartOne.contains(split[3])) {
                                                s++;
                                            }

                                        }
                                        satisfiedTexaList.add(s);
                                        MaxItemList.add(tempMaxMulItem.get(m));
                                        System.out.println(s);
                                    }
                                }
                                System.out.println("Check line...............................");
                                System.out.println(satisfiedTexaList.toString());
                                if (mulValues == 1) {
                                    System.out.println("Check line...............................");
                                    int maxVal = Collections.max(satisfiedTexaList);
                                    int freq = 0;
                                    for (int n = 0; n < satisfiedTexaList.size(); n++) {
                                        if (maxVal == satisfiedTexaList.get(n)) {
                                            freq++;
                                        }
                                    }
                                    System.out.println("Check line...............................");
                                    if (freq > 1) {
                                        System.out.println("Check line...............................");
                                        completedItem.add(MaxItemList.get(0));
                                        gainList.add(tempGainList.get(tempItemList.indexOf(MaxItemList.get(0))));
                                        if (partOne.contains(MaxItemList.get(0))) {
                                            partTwo.add(MaxItemList.get(0));
                                            partOne.remove(MaxItemList.get(0));
                                        } else {
                                            partOne.add(MaxItemList.get(0));
                                            partTwo.remove(MaxItemList.get(0));
                                        }
                                        System.out.println("New Highest Score of : " + MaxItemList.get(0)
                                                + " is :" + tempGainList.get(tempItemList.indexOf(MaxItemList.get(0))));

                                    } else {

                                        int inx = satisfiedTexaList.indexOf(maxVal);
                                        completedItem.add(MaxItemList.get(inx));
                                        gainList.add(tempGainList.get(inx));
                                        if (partOne.contains(MaxItemList.get(inx))) {
                                            partTwo.add(MaxItemList.get(inx));
                                            partOne.remove(MaxItemList.get(inx));
                                        } else {
                                            partOne.add(MaxItemList.get(inx));
                                            partTwo.remove(MaxItemList.get(inx));
                                        }
                                        System.out.println("New Highest Score of : " + MaxItemList.get(inx)
                                                + " is :" + tempGainList.get(tempItemList.indexOf(MaxItemList.get(inx))));

                                    }

                                    /**
                                     * *****************Calculation initial
                                     * Score********************
                                     */
                                    //readLine = new BufferedReader(new FileReader("Test.txt"));
                                    s = 0;
                                    v = 0;
                                    System.out.println("PartOne: " + partOne.toString());
                                    System.out.println("PartTwo: " + partTwo.toString());
                                    System.out.println("PartPartOne: " + partOne.toString());
                                    texa2DPartOneList.add(new ArrayList<String>(partOne));
                                    texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                    System.out.println("PartPartTwo: " + partTwo.toString());

                                    for (int j = 0; j < mainData.size(); j++) {

                                        sCurrentLine = mainData.get(j);
                                        sCurrentLine = sCurrentLine.replace("(", "");
                                        sCurrentLine = sCurrentLine.replace(")", "");
                                        sCurrentLine = sCurrentLine.replace(";", "");
                                        sCurrentLine = sCurrentLine.trim();
                                        String split[] = sCurrentLine.split(",");
                                        if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                                            s++;
                                        } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                                            s++;
                                        } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                                            v++;
                                        } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                                            v++;
                                        } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                                            v++;
                                        } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                                            v++;
                                        }

                                    }

                                    System.out.println("Satisfied: " + s);
                                    System.out.println("Violated: " + v);
                                    ini = s - v;
                                    System.out.println("Initial Score: " + ini);
                                    /**
                                     * ******************End*****************************************
                                     */
                                    tempGainList.clear();
                                    tempItemList.clear();
                                    // Check for singleton bipartation
                                    exit_flag = 1;
                                    break;
                                } else {
                                    System.out.println(tempGainList.toString());
                                    System.out.println("All Singleton handling...............");
                                    if (tempGainList.isEmpty()) {
                                        for (int k = 0; k < singletonGainList.size(); k++) {
                                            completedItem.add(singletonTexaList.get(k));
                                            gainList.add(0);
                                        }
//                                        texa2DPartOneList.add(new ArrayList<String>(partOne));
//                                        texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                        exit_flag = 1;
                                        break;
                                    } //                                    }
                                    //                                    else if (tempMaxMulItem.size() == tempGainList.size()) {
                                    //                                        for (int k = 0; k < tempGainList.size(); k++) {
                                    //                                            completedItem.add(tempItemList.get(k));
                                    //                                            //gainList.add(tempGainList.get(k));
                                    //                                            gainList.add(0);
                                    //                                        }
                                    ////                                        texa2DPartOneList.add(new ArrayList<String>(partOne));
                                    ////                                        texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                    //                                        exit_flag = 1;
                                    //                                        break;
                                    //                                    }
                                    else {
                                        for (int k = 0; k < tempGainList.size(); k++) {
                                            if (maxGain == tempGainList.get(k)) {
                                                tempGainList.remove(k);
                                                tempItemList.remove(k);
                                                //singletonGainList.add(tempGainList.get(k));
                                                singletonGainList.add(0);
                                                singletonTexaList.add(tempItemList.get(k));
                                            }
                                        }
                                    }
                                }
                                if (exit_flag == 1) {
                                    break;
                                }
                            } else {

                                completedItem.add(tempItemList.get(index));
                                gainList.add(Collections.max(tempGainList));

                                if (partOne.contains(tempItemList.get(index))) {
                                    partTwo.add(tempItemList.get(index));
                                    partOne.remove(tempItemList.get(index));
                                } else {
                                    partOne.add(tempItemList.get(index));
                                    partTwo.remove(tempItemList.get(index));
                                }
                                if (totalItem == 0) {
                                    finalLeaf = tempItemList.get(index);
                                    gainCheck = Collections.max(tempGainList);
                                    finalPartOneList.addAll(partOne);
                                    finalPartTwoList.addAll(partTwo);
                                } else {
                                    if (gainCheck < Collections.max(tempGainList)) {

                                        finalPartOneList.clear();
                                        finalPartTwoList.clear();
                                        finalPartOneList.addAll(partOne);
                                        finalPartTwoList.addAll(partTwo);
                                    }
                                }
                                System.out.println("Highest Score of : " + tempItemList.get(index)
                                        + " is :" + Collections.max(tempGainList));
                                //readLine = new BufferedReader(new FileReader("Test.txt"));
                                s = 0;
                                v = 0;
                                System.out.println("PartOne: " + partOne.toString());
                                System.out.println("PartTwo: " + partTwo.toString());
                                //System.out.println("PartPartOne: " + finalPartOneList.toString());
                                texa2DPartOneList.add(new ArrayList<String>(partOne));
                                texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                //System.out.println("PartPartTwo: " + finalPartTwoList.toString());

                                for (int j = 0; j < mainData.size(); j++) {

                                    sCurrentLine = mainData.get(j);
                                    sCurrentLine = sCurrentLine.replace("(", "");
                                    sCurrentLine = sCurrentLine.replace(")", "");
                                    sCurrentLine = sCurrentLine.replace(";", "");
                                    sCurrentLine = sCurrentLine.trim();
                                    String split[] = sCurrentLine.split(",");

                                    if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                                        s++;
                                    } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                                        s++;
                                    } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                                        v++;
                                    } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                                        v++;
                                    } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                                        v++;
                                    } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                                        v++;
                                    }

                                }

                                System.out.println("Satisfied: " + s);
                                System.out.println("Violated: " + v);
                                ini = s - v;
                                System.out.println("Initial Score: " + ini);
                                /**
                                 * ******************End*****************************************
                                 */
                                tempGainList.clear();
                                tempItemList.clear();
                                break;
                            }
                        }
                    }

                    totalItem++;
                }

                System.out.println("Gain List: " + gainList.toString());
                //cumulative
                for (int i = 0; i < gainList.size(); i++) {
                    if (cumulativeGain.isEmpty()) {
                        cumulativeGain.add(gainList.get(i));
                        System.out.println("Cumulataiv: " + cumulativeGain.toString());
                    } else {
                        System.out.println("Cumul: " + cumulativeGain.get(cumulativeGain.size() - 1));
                        cumulativeGain.add((cumulativeGain.get((cumulativeGain.size() - 1)) + gainList.get(i)));
                        System.out.println("Cumulataiv..............");
                    }
                }
                System.out.println(cumulativeGain.toString());
                System.out.println(texa2DPartOneList.toString());
                System.out.println(texa2DPartTwoList.toString());
                System.out.println("CumuStep");

                if (Collections.max(cumulativeGain) <= 0 || (!storeCumulativeGain.isEmpty() && (storeCumulativeGain.contains(cumulativeGain)))) {

                    System.out.println("Final Out put.....");
                    System.out.println("Leaf: " + finalLeaf);
                    System.out.println("Final Part One:" + partOne.toString());
                    System.out.println("Final Part Two:" + partTwo.toString());
                    //break;
                    targetGain = 0;
                } else {
                    storeCumulativeGain.add(new ArrayList<Integer>(cumulativeGain));
                    System.out.println("Cumulative Step : " + (targetGain++ + 1));
                    //break;
                    System.out.println("Max cumulative gain: " + Collections.max(cumulativeGain));
                    int index = cumulativeGain.indexOf(Collections.max(cumulativeGain));
                    System.out.println("Index of Max: " + index);
                    System.out.println(texa2DPartOneList.get(index));
                    System.out.println(texa2DPartTwoList.get(index));
                    cumulativeGain.clear();
                    completedItem.clear();
                    gainList.clear();
                    partOne.clear();
                    partTwo.clear();

                    // int index = cumulativeGain.indexOf(Collections.max(cumulativeGain));
                    // finalPartOneList.add(texa2DList.get(index));
                    //System.out.println("FPartOne: " + Collections.max(cumulativeGain));
//                    partOne.addAll(finalPartOneList);
//                    partTwo.addAll(finalPartTwoList);
                    partOne.addAll(texa2DPartOneList.get(index));
                    partTwo.addAll(texa2DPartTwoList.get(index));

                    //readLine = new BufferedReader(new FileReader("Test.txt"));
                    s = 0;
                    v = 0;
                    System.out.println("One: " + finalPartOneList.toString());
                    System.out.println("Two: " + finalPartTwoList.toString());

                    System.out.println("PartOne: " + partOne.toString());
                    System.out.println("PartTwo: " + partTwo.toString());

//                    if(targetGain == 3) {
//                        targetGain = 0;
//                    }
                    finalPartOneList.clear();
                    finalPartTwoList.clear();

                    for (int j = 0; j < mainData.size(); j++) {

                        sCurrentLine = mainData.get(j);
                        sCurrentLine = sCurrentLine.replace("(", "");
                        sCurrentLine = sCurrentLine.replace(")", "");
                        sCurrentLine = sCurrentLine.replace(";", "");
                        sCurrentLine = sCurrentLine.trim();
                        String split[] = sCurrentLine.split(",");

                        if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                            s++;
                        } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                            s++;
                        } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                            v++;
                        } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                            v++;
                        } else if (partOne.contains(split[0]) && partTwo.contains(split[1]) && partTwo.contains(split[2]) && partOne.contains(split[3])) {
                            v++;
                        } else if (partTwo.contains(split[0]) && partOne.contains(split[1]) && partOne.contains(split[2]) && partTwo.contains(split[3])) {
                            v++;
                        }

                    }

                    System.out.println("Satisfied: " + s);
                    System.out.println("Violated: " + v);
                    ini = s - v;
                    System.out.println("Initial Score: " + ini);

                }

            }

            System.out.println("Set..............................");
            for (int i = 0; i < set.size(); i++) {
                if (!partOne.contains(set.toArray()[i].toString())) {
                    if (!partTwo.contains(set.toArray()[i].toString())) {
                        partTwo.add(set.toArray()[i].toString());
                    }
                }
            }

            System.out.println("Part One: " + partOne.toString());
            System.out.println("Part Two: " + partTwo.toString());

            //Tree Reconstruction part..............................
            System.out.println("New partition..................................................");
            TreeReconstruction treeObj;
            treeObj = new TreeReconstruction();
            treeObj.calculate(treeObj.get_texa_list(), partOne, partTwo);

            treeObj.load_dummy_texa();
            treeObj.reconstruct_tree(treeObj.get_texa_list(), partTwo);
            treeObj.reset_dummy_texa();
            treeObj.load_dummy_texa();
            treeObj.reconstruct_tree(treeObj.get_texa_list(), partOne);
            System.out.println("Final Leaf List....................... ");
            System.out.println(treeObj.leafResult.toString());

            treeObj.final_calculation(treeObj.leafResult, partTwo, partOne);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        final long milliseconds = timeElapsed.toMillis();
        System.out.println("Time taken: " + milliseconds + " milliseconds");

        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        System.out.println("Required time in minute: " + minutes + " minutes and " + seconds + " seconds ");

    }

}
