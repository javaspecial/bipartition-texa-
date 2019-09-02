package main;




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TreeReconstruction {
    
    ArrayList<String> texaList = new ArrayList<String>();

    BufferedReader fileReder = null;
    BufferedReader readLine = null;
    public String dumTexa = "a";
    public int dumIndex = 1;
    String sCurrentLine, mainLine;
    int s,v,d;
    ArrayList<String> dummyTexaList = new ArrayList<String>();
    int dummyIndex = 0;
    int initialScore = 0;
    ArrayList<String> cumulativeMaxTexaList = new ArrayList<String>();
    public ArrayList<ArrayList<String>> leafResult = new ArrayList<ArrayList<String>>();
    
    ArrayList<String>unionOneTwo = new ArrayList<String>();
    
    public TreeReconstruction() throws IOException{
        try {
            readLine = new BufferedReader(new FileReader("src/txtfilereader/Test.txt"));

            System.out.println("Texa List: ");
            while ((sCurrentLine = readLine.readLine()) != null) {
                texaList.add(sCurrentLine);
                System.out.println(sCurrentLine);
                sCurrentLine = sCurrentLine.replace("(", "");
                sCurrentLine = sCurrentLine.replace(")", "");
                sCurrentLine = sCurrentLine.replace(";", "");
                sCurrentLine = sCurrentLine.trim();
                String split[] = sCurrentLine.split(",");
                for(int i = 0; i < split.length; i++) {
                    unionOneTwo.add(split[i]);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TreeReconstruction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void load_dummy_texa(){
//        dummyTexaList.add("a");
//        dummyTexaList.add("b");
//        dummyTexaList.add("c");
//        dummyTexaList.add("d");
//        dummyTexaList.add("e");
//        dummyTexaList.add("f");
//        dummyTexaList.add("g");
//        dummyTexaList.add("h");
//        dummyTexaList.add("i");
//        dummyTexaList.add("j");
//        dummyTexaList.add("l");
//        dummyTexaList.add("m");
//        dummyTexaList.add("n");
        dumTexa = "a";
        dumIndex = 1;
    }
    public void reset_dummy_texa(){
        dumTexa = "";
        dumIndex = 1;
    }
    
    public ArrayList<String> get_texa_list(){
        return texaList;
    }
    public ArrayList<ArrayList<String>> calculate(ArrayList<String>qSet, ArrayList<String> partOne, ArrayList<String> partTwo) { // for statisfied, violated, differed

        ArrayList<String> violatedList = new ArrayList<String>();
        ArrayList<String> differedList = new ArrayList<String>();
        ArrayList<String> satifiedList = new ArrayList<String>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        
        
        for(int i = 0; i < qSet.size(); i++) {
            
            mainLine = qSet.get(i);
            sCurrentLine = mainLine;
            
            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");

            if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                satifiedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                satifiedList.add(mainLine);
            }
            else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1])
                    && !partOne.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2])
                    && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1])
                    && !partTwo.contains(split[1]) && partTwo.contains(split[2]) && !partOne.contains(split[2])
                    && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1])
                    && !partOne.contains(split[1]) && partTwo.contains(split[2]) && !partOne.contains(split[2])
                    && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1])
                    && !partTwo.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2])
                    && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                violatedList.add(mainLine);
            }
            else {
                differedList.add(mainLine);
            }
            
        }
        System.out.println("Satified List:");
        System.out.println(satifiedList.toString());
        System.out.println("Violated List:");
        System.out.println(violatedList.toString());
        System.out.println("Differed List:");
        System.out.println(differedList.toString());
        
        result.add(new ArrayList<String>(satifiedList));
        result.add(new ArrayList<String>(violatedList));
        result.add(new ArrayList<String>(differedList));
        
        return result;
    }
    public ArrayList<String> separet(ArrayList<String>qSet,ArrayList<String>part){
        
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < qSet.size(); i++) {

            mainLine = qSet.get(i);
            sCurrentLine = mainLine;

            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            int count = 0;
            for(int j = 0; j < split.length; j++) {
                if(part.contains(split[j])) {
                    count++;
                }
            }
            if(count >=3) {
                result.add(mainLine);
            }

        }
        return result;
    }
    
    public void reconstruct_tree(ArrayList<String>qSet, ArrayList<String> part){

        if (part.size() == 2 && qSet.isEmpty()) {
            //leafResult.add(new ArrayList<String>(part));
            System.out.println("Result...........................: " + part.toString());
            return;
        }
        else {

            System.out.println("Part: " + part.toString());
            ArrayList<String> differedList = new ArrayList<String>();
            ArrayList<String>separet = separet(qSet, part);
            differedList.addAll(separet);
            
            if(!differedList.isEmpty()) {
                ArrayList<String> tempDiffer = new ArrayList<String>();
                tempDiffer.addAll(differedList);
                //differedList = get_differed_with_dummy_texa(differedList, part);
                tempDiffer = get_differed_with_dummy_texa(tempDiffer, part);
                //part.add(dummyTexaList.get(dummyIndex));
                //dummyIndex++;
                part.add(dumTexa + dumIndex++);
                //System.out.println("New Differed List: " + differedList);
                System.out.println("New Differed List: " + tempDiffer);
                //ArrayList<ArrayList<String>> result = initial_partition(differedList);
                ArrayList<ArrayList<String>> result = initial_partition(tempDiffer);
                System.out.println("Construct left: " + result.get(0).toString());
                System.out.println("Construct right: " + result.get(1).toString());

                for (int i = 0; i < part.size(); i++) {
                    if (!result.get(0).contains(part.get(i))) {
                        if (!result.get(1).contains(part.get(i))) {
                            result.get(1).add(part.get(i));
                        }
                    }
                }
                System.out.println(part.toString());
                System.out.println("New Construct left: " + result.get(0).toString());
                System.out.println("New Construct right: " + result.get(1).toString());
                
//                initialScore = initial_score_calculation(differedList, result.get(0), result.get(1));
//                ArrayList<ArrayList<String>> resultBip = bipartation(differedList, result.get(0), result.get(1), initialScore);
                initialScore = initial_score_calculation(tempDiffer, result.get(0), result.get(1));
                ArrayList<ArrayList<String>> resultBip = bipartation(tempDiffer, result.get(0), result.get(1), initialScore);
                
                System.out.println("Result first Part: " + resultBip.get(0).toString());
                System.out.println("Result second Part: " + resultBip.get(1).toString());

                reconstruct_tree(differedList, resultBip.get(0));                
                reconstruct_tree(differedList, resultBip.get(1));
                dummyIndex--;

              
                System.out.println("Set: " + qSet.toString());
                System.out.println("Part: " + part.toString());

            }
            else {

                System.out.println("Differd list is empty.....................");

                //part.add(dummyTexaList.get(dummyIndex));
                part.add(dumTexa + dumIndex);
                leafResult.add(new ArrayList<String>(part));
                
                int len = part.size();
                if(len >= 4 ) {
                    leafResult.remove(part);
                    ArrayList<String>temp = new ArrayList<String>();
                    int in = 0;
                    while(len>2){
                        //dummyIndex++;
                        dumIndex++;
                        temp.add(part.get(in++));
                        temp.add(part.get(in++));

                        //temp.add(dummyTexaList.get(dummyIndex));
                        temp.add(dumTexa + dumIndex);
                        leafResult.add(new ArrayList<String>(temp));
                        temp.clear();
                        
                        len = len - 2;
                    }

                    for(int j = in ; j < part.size(); j++) {
                        temp.add(part.get(j));
                    }
                    //temp.add(dummyTexaList.get(dummyIndex++));
                    temp.add(dumTexa + dumIndex++);
                    leafResult.add(new ArrayList<String>(temp));
                }
                else {
                    

                }
                
                return;
            }
        }
    }
    
    public void final_calculation(ArrayList<ArrayList<String>> result, ArrayList<String>partOne, ArrayList<String>partTwo){


        final_calculate(result);
        //        ArrayList<ArrayList<String>>leftResult = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>>rightResult = new ArrayList<ArrayList<String>>();
//        
//        System.out.println(result.toString());
//        System.out.println(partOne.toString());
//        System.out.println(partTwo.toString());
//        
//        for(int i = 0 ; i < result.size(); i++){
//            int lcount = 0;
//            int rcount = 0;
//            for(int j = 0 ; j < result.get(i).size(); j++) {
//                if(partOne.contains(result.get(i).get(j))) {
//                    lcount++;
//                }
//                else if(partTwo.contains(result.get(i).get(j))) {
//                    rcount++;
//                }
//                
//            }
//            if(lcount > rcount) {
//                System.out.println("left: " + result.get(i));
//                leftResult.add(new ArrayList<String>(result.get(i)));
//            }
//            else {
//                System.out.println("right: " + result.get(i));
//                rightResult.add(new ArrayList<String>(result.get(i)));
//            }
//        }
//
//        final_calculate(leftResult);
//        final_calculate(rightResult);
//        
//        ArrayList<String>tempLeftResult = new ArrayList<String>();
//        ArrayList<String>tempRightResult = new ArrayList<String>();
//        for(int i = 0; i < leftResult.size(); i++) {
//            tempLeftResult.addAll(leftResult.get(i));
//        }
//        for (int i = 0; i < rightResult.size(); i++) {
//            tempRightResult.addAll(rightResult.get(i));
//        }
//
//        System.out.println("Left Union: " + tempLeftResult);
//        System.out.println("Right Union: " + tempRightResult);
//        genaral_calculate(texaList, tempLeftResult, tempRightResult);
        
    } 
    void final_calculate(ArrayList<ArrayList<String>> result){

        ArrayList<String> satisfied = new ArrayList<String>();
        ArrayList<String> violated = new ArrayList<String>();
        ArrayList<String> differed = new ArrayList<String>();
        
        for (int i = 0; i < texaList.size(); i++) {

            mainLine = texaList.get(i);
            sCurrentLine = mainLine;

            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            int fPos = -1;
            int sPos = -1;
            int tPos = -1;
            int foPos = -1;
            int count = 0;
            for(int j = 0; j < result.size(); j++) {
                if(result.get(j).contains(split[0])) {
                    fPos = j;
                    count++;
                }
                if (result.get(j).contains(split[1])) {
                    sPos = j;
                    count++;
                }if (result.get(j).contains(split[2])) {
                    tPos = j;
                    count++;
                }if (result.get(j).contains(split[3])) {
                    foPos = j;
                    count++;
                }
                
                if(count == 4)
                    break;
            }
//            if(fPos < tPos) {
//                if(sPos>=fPos && sPos <tPos) {
//                    if(foPos > sPos) {   
//                        satisfied.add(mainLine);
//                        //texaList.remove(i);
//                    }
//                    else {
//                        violated.add(mainLine);
//                    }
//                }
//                else {
//                    violated.add(mainLine);
//                }
//            }
//            else {
//                violated.add(mainLine);
//            }
            
            /*if (fPos < tPos) {
                if (sPos < tPos) {
//                    if (foPos > sPos) {
//                        satisfied.add(mainLine);
//                        //texaList.remove(i);
//                    } else {
//                        violated.add(mainLine);
//                    }
                    satisfied.add(mainLine);
                } else if(sPos == tPos) {
                    differed.add(mainLine);
                }
                else {
                    violated.add(mainLine);
                }
            } else {
                violated.add(mainLine);
            }
            */
            
            
            if (fPos < tPos) {
                if (sPos < tPos && sPos < foPos) {
                    satisfied.add(mainLine);
                } else if (sPos == tPos) {
                    differed.add(mainLine);
                } else {
                    violated.add(mainLine);
                }
            } 
            
            else {
                if (foPos < fPos && foPos < sPos) {
                    satisfied.add(mainLine);
                } else if (sPos == tPos) {
                    differed.add(mainLine);
                } else {
                    violated.add(mainLine);
                }
            }
            
            

        }
//        
        System.out.println("Satisfied List:");
        System.out.println(satisfied.toString());
        System.out.println("Violated List: ");
        System.out.println(violated.toString());
        System.out.println("Differed List: ");
        System.out.println(differed.toString());
        
//        duplication(result, violated);
        
    }
    
    
    void duplication(ArrayList<ArrayList<String>> result, ArrayList<String>violatedList){
        
        ArrayList<String> satisfied = new ArrayList<String>();
        ArrayList<String> violated = new ArrayList<String>();
        ArrayList<String> differed = new ArrayList<String>();
        
        for (int i = 0; i < violatedList.size(); i++) {

            mainLine = violatedList.get(i);
            sCurrentLine = mainLine;

            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            int fPos = -1;
            int sPos = -1;
            int tPos = -1;
            int foPos = -1;
            int count = 0;

            for(int j = 0; j < result.size(); j++) {
                if(result.get(j).contains(split[0])) {
                    fPos = j;
                    count++;
                }
                if (result.get(j).contains(split[1])) {
                    sPos = j;
                    count++;
                }if (result.get(j).contains(split[2])) {
                    tPos = j;
                    count++;
                }if (result.get(j).contains(split[3])) {
                    foPos = j;
                    count++;
                }
                
                if(count == 4)
                    break;
            }

            if (fPos <= tPos) {
                if (sPos>tPos) {
                    result.get(foPos).add(split[2]);
                } 
            } 
            
            else {
                if (sPos < tPos) {
                    result.get(foPos).add(split[2]);
                } 
            }
        }
//        
        System.out.println("Final Leaf");
        
        System.out.println(result.toString());
        
        ArrayList<String>duplicate = new ArrayList<String>();
        
        for(int i = 0; i < result.size(); i++) {
            duplicate.addAll(result.get(i));
        }
        int flag[] = new int[duplicate.size()];
        for(int i = 0; i < duplicate.size(); i++) {
            flag[i] = 0;
        }
        System.out.println(duplicate.toString());
        for(int i = 0; i < duplicate.size(); i++) {
            int count = 0;
            for(int j = 0; j < duplicate.size(); j++) {
                if ((duplicate.get(i).equals(duplicate.get(j))) && flag[i] != 1) {
                    count++;
                    flag[j] = 1;
                }
            }
            if(count > 1) {
                if(unionOneTwo.contains(duplicate.get(i))) {
                    System.out.println(duplicate.get(i) + ": " + count);
                }
            }
        }

        System.out.println("Satisfied List:");
        System.out.println(satisfied.toString());
        System.out.println("Violated List: ");
        System.out.println(violated.toString());
        System.out.println("Differed List: ");
        System.out.println(differed.toString());
    }
    
    void make_all_violated_satisfied(ArrayList<ArrayList<String>>result, ArrayList<String>violatedList){
        
    
        ArrayList<String> satisfied = new ArrayList<String>();
        ArrayList<String> violated = new ArrayList<String>();
        ArrayList<String> differed = new ArrayList<String>();

        for (int i = 0; i < violatedList.size(); i++) {

            mainLine = violatedList.get(i);
            sCurrentLine = mainLine;

            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            int fPos = -1;
            int sPos = -1;
            int tPos = -1;
            int foPos = -1;
            int count = 0;
            for (int j = 0; j < result.size(); j++) {
                if (result.get(j).contains(split[0])) {
                    fPos = j;
                    count++;
                }
                if (result.get(j).contains(split[1])) {
                    sPos = j;
                    count++;
                }
                if (result.get(j).contains(split[2])) {
                    tPos = j;
                    count++;
                }
                if (result.get(j).contains(split[3])) {
                    foPos = j;
                    count++;
                }

                if (count == 4) {
                    break;
                }
            }
            if (fPos < tPos) {
                if (sPos < tPos && sPos < foPos) {
                    satisfied.add(mainLine);
                } else if (sPos == tPos) {
                    differed.add(mainLine);
                } else {
                    violated.add(mainLine);
                }
            } 
            
            else {
                if (foPos < fPos && foPos < sPos) {
                    satisfied.add(mainLine);
                } else if (sPos == fPos) {
                    differed.add(mainLine);
                } else {
                    violated.add(mainLine);
                }
            }
            
        }
    }
    
    public ArrayList<ArrayList<String>> genaral_calculate(ArrayList<String>qSet, ArrayList<String> partOne, ArrayList<String> partTwo) { // for statisfied, violated, differed

        System.out.println("New texa List:");
        System.out.println(texaList.toString());
        ArrayList<String> violatedList = new ArrayList<String>();
        ArrayList<String> differedList = new ArrayList<String>();
        ArrayList<String> satifiedList = new ArrayList<String>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        
        
        for(int i = 0; i < qSet.size(); i++) {
            
            mainLine = qSet.get(i);
            sCurrentLine = mainLine;
            
            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");

            if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                satifiedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                satifiedList.add(mainLine);
            }
            else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1])
                    && !partOne.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2])
                    && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1])
                    && !partTwo.contains(split[1]) && partTwo.contains(split[2]) && !partOne.contains(split[2])
                    && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1])
                    && !partOne.contains(split[1]) && partTwo.contains(split[2]) && !partOne.contains(split[2])
                    && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                violatedList.add(mainLine);
            } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1])
                    && !partTwo.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2])
                    && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                violatedList.add(mainLine);
            }
            else {
                differedList.add(mainLine);
            }
            
        }
        System.out.println("Satified List:");
        System.out.println(satifiedList.toString());
        System.out.println("Violated List:");
        System.out.println(violatedList.toString());
        System.out.println("Differed List:");
        System.out.println(differedList.toString());
        
        result.add(new ArrayList<String>(satifiedList));
        result.add(new ArrayList<String>(violatedList));
        result.add(new ArrayList<String>(differedList));
        
        return result;
    }
    
    ArrayList<String> get_differed_with_dummy_texa(ArrayList<String>qSet, ArrayList<String>part){

        ArrayList<String> tempResult = new ArrayList<String>();
        System.out.println("Part: " + part.toString());
        for (int i = 0; i < qSet.size(); i++) {
            String line = qSet.get(i);
            sCurrentLine = line;
            System.out.println("Item: " + line);
            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            String temp = "";
            for(int j = 0 ; j < split.length; j++) {
                if(!part.contains(split[j])) {
                    temp = split[j];
                    break;
                }
            }
            if(!temp.equals("")) {
                //line = line.replaceFirst(temp, dummyTexaList.get(dummyIndex));
                line = line.replaceFirst(temp, (dumTexa + dumIndex));
                System.out.println("new texa: " + line);
            }
            tempResult.add(line);
        }
        return tempResult;
    }
    
    ArrayList<String>get_new_differed_list(ArrayList<String>list, ArrayList<String>left, ArrayList<String>right){
        ArrayList<String> tempResult = new ArrayList<String>();
        
        System.out.println("left part: " + left.toString());
        System.out.println("right part: " + right.toString());
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            sCurrentLine = line;
            System.out.println("Item: " + line);
            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");
            int lcount = 0; 
            int rcount = 0;
            String ltemp = "";
            String rtemp = "";
            for(int j = 0 ; j < split.length; j++) {
                if(left.contains(split[j])) {
                    lcount++;
                    ltemp = split[j];
                }
                if(right.contains(split[j])) {
                    rcount++;
                    rtemp = split[j];
                }
            }
            System.out.println("left leng: " + lcount + "right leng: " + rcount);
            if(lcount == 3) {
                //line.replace(rtemp, dummyTexaList.get(dummyIndex));
                //line = line.replaceFirst(rtemp, dummyTexaList.get(dummyIndex));
                line = line.replaceFirst(rtemp, (dumTexa + dumIndex));
                System.out.println("left: " + line);
                //tempResult.add(line);
            }
            else if(rcount == 3) {
                
                //line = line.replaceFirst(ltemp, dummyTexaList.get(dummyIndex));
                line = line.replaceFirst(ltemp, (dumTexa + dumIndex));
                System.out.println("right: " + line);
                tempResult.add(line);
            }
            
        }
        return tempResult;
    }
    
    public ArrayList<ArrayList<String>> make_violated_differed(ArrayList<String> qSet, ArrayList<String>partOne, 
            ArrayList<String>partTwo){
        
        int flag = 0;
        ArrayList<String> tempViolated = new ArrayList<String>();
        while (true) {

            s = 0;
            v = 0;
            
            for (int i = 0; i < qSet.size(); i++) {

                sCurrentLine = qSet.get(i);
                sCurrentLine = sCurrentLine.replace("(", "");
                sCurrentLine = sCurrentLine.replace(")", "");
                sCurrentLine = sCurrentLine.replace(";", "");
                sCurrentLine = sCurrentLine.trim();
                String split[] = sCurrentLine.split(",");

                if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                    s++;
                } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                    s++;
                } else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1]) 
                        && !partOne.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2]) 
                        && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                    v++;
                    tempViolated.add(sCurrentLine);
                } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1]) 
                        && !partTwo.contains(split[1])&& partTwo.contains(split[2]) && !partOne.contains(split[2])
                        && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                    v++;
                    tempViolated.add(sCurrentLine);
                } else if (partOne.contains(split[0]) && !partTwo.contains(split[0])&& partTwo.contains(split[1]) 
                        && !partOne.contains(split[1])&& partTwo.contains(split[2]) && !partOne.contains(split[2])
                        && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                    v++;
                    tempViolated.add(sCurrentLine);
                } else if (partTwo.contains(split[0]) && !partOne.contains(split[0])&& partOne.contains(split[1]) 
                        && !partTwo.contains(split[1])&& partOne.contains(split[2]) && !partTwo.contains(split[2])
                        && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                    v++;
                    tempViolated.add(sCurrentLine);
                }

            }
            System.out.println("Violated: " + tempViolated.toString());
            System.out.println("Part One: " + partOne.toString());
            System.out.println("Part Two: " + partTwo.toString());
            //break;
            if(!tempViolated.isEmpty()) {
                int tempFlag = 0;
                for(int i = 0; i < tempViolated.size(); i++) {
                    sCurrentLine = tempViolated.get(i);
                    sCurrentLine = sCurrentLine.replace("(", "");
                    sCurrentLine = sCurrentLine.replace(")", "");
                    sCurrentLine = sCurrentLine.replace(";", "");
                    sCurrentLine = sCurrentLine.trim();
                    String split[] = sCurrentLine.split(",");

                    for(int j = 0; j < split.length; j++) {
                        if(partOne.contains(split[j]) && !partTwo.contains(split[j])) {
                            partTwo.add(split[j]);
                            tempFlag = 1;
                            break;
                        }
                    }
                    if(tempFlag == 1) {
                        tempViolated.remove(i);
                        break;
                    }
                }
                
            }
            else {
                flag = 1;
            }
            if(flag == 1) {
                break;
            }
            
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(new ArrayList<String>(partOne));
        result.add(new ArrayList<String>(partTwo));
        System.out.println("New part one: " + partOne);
        System.out.println("New part two: " + partTwo);
        
        return result;
    }
    public ArrayList<ArrayList<String>> initial_partition(ArrayList<String> qSet){
        
        ArrayList<String> partOne = new ArrayList<String>();
        ArrayList<String> partTwo = new ArrayList<String>();

        for(int i = 0; i < qSet.size(); i++) {

            sCurrentLine = qSet.get(i);
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
        }


        System.out.println("Part One...");
        System.out.println(partOne.toString());

        System.out.println("Part Two...");
        System.out.println(partTwo.toString());
        
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(new ArrayList<String>(partOne));
        result.add(new ArrayList<String>(partTwo));
        
        return result;
        //initial_score_calculation(qSet, partOne, partTwo);
    }
    public int initial_score_calculation(ArrayList<String>qSet, ArrayList<String>partOne, ArrayList<String>partTwo){

        s = 0;
        v = 0;
        for (int i = 0; i < qSet.size(); i++) {
            
            sCurrentLine = qSet.get(i);
            sCurrentLine = sCurrentLine.replace("(", "");
            sCurrentLine = sCurrentLine.replace(")", "");
            sCurrentLine = sCurrentLine.replace(";", "");
            sCurrentLine = sCurrentLine.trim();
            String split[] = sCurrentLine.split(",");

            if (partOne.contains(split[0]) && partOne.contains(split[1]) && partTwo.contains(split[2]) && partTwo.contains(split[3])) {
                s++;
            } else if (partTwo.contains(split[0]) && partTwo.contains(split[1]) && partOne.contains(split[2]) && partOne.contains(split[3])) {
                s++;
                } else if (partOne.contains(split[0]) && !partTwo.contains(split[0]) && partTwo.contains(split[1]) 
                        && !partOne.contains(split[1]) && partOne.contains(split[2]) && !partTwo.contains(split[2]) 
                        && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                    v++;
                    
                } else if (partTwo.contains(split[0]) && !partOne.contains(split[0]) && partOne.contains(split[1]) 
                        && !partTwo.contains(split[1])&& partTwo.contains(split[2]) && !partOne.contains(split[2])
                        && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                    v++;
                    
                } else if (partOne.contains(split[0]) && !partTwo.contains(split[0])&& partTwo.contains(split[1]) 
                        && !partOne.contains(split[1])&& partTwo.contains(split[2]) && !partOne.contains(split[2])
                        && partOne.contains(split[3]) && !partTwo.contains(split[3])) {
                    v++;
                    
                } else if (partTwo.contains(split[0]) && !partOne.contains(split[0])&& partOne.contains(split[1]) 
                        && !partTwo.contains(split[1])&& partOne.contains(split[2]) && !partTwo.contains(split[2])
                        && partTwo.contains(split[3]) && !partOne.contains(split[3])) {
                    v++;
                    
                }

        }

        System.out.println("InitialScore......................");
        System.out.println("Part One: " + partOne.toString());
        System.out.println("Part Two: " + partTwo.toString());
        System.out.println(qSet.toString());
        System.out.println("Satisfied: " + s);
        System.out.println("Violated: " + v);
        int ini = s - v;
        System.out.println("Score: " + ini);
        
       // bipartation(qSet, partOne, partTwo, ini);
        return ini;
    }
    
    
    public ArrayList<ArrayList<String>> bipartation(ArrayList<String>qSet, ArrayList<String>partOne, 
            ArrayList<String>partTwo, int ini){
        
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
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
                       
                        System.out.println("PartOne: " + destOne.toString());
                        System.out.println("PartTwo: " + destTwo.toString());

                        for(int index = 0; index < qSet.size(); index++) {

                            sCurrentLine = qSet.get(index);
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
                        
                        System.out.println("PartOneOriginal: " + partOne.toString());
                        System.out.println("PartOne: " + destOne.toString());
                        System.out.println("PartTwo: " + destTwo.toString());

                        
                        for(int index = 0; index < qSet.size(); index++) {

                            sCurrentLine = qSet.get(index);
                            sCurrentLine = sCurrentLine.replace("(", "");
                            sCurrentLine = sCurrentLine.replace(")", "");
                            sCurrentLine = sCurrentLine.replace(";", "");
                            sCurrentLine = sCurrentLine.trim();
                            String split[] = sCurrentLine.split(",");

                            if (destOne.contains(split[0]) && destOne.contains(split[1]) && destTwo.contains(split[2]) && destTwo.contains(split[3])) {
                                System.out.println("S One: " + destOne.toString());
                                System.out.println("S Two: " + destTwo.toString());
                                s++;
                            } else if (destTwo.contains(split[0]) && destTwo.contains(split[1]) && destOne.contains(split[2]) && destOne.contains(split[3])) {
                                System.out.println("S One: " + destOne.toString());
                                System.out.println("S Two: " + destTwo.toString());
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
                        System.out.println("Qset: " + qSet.toString());
                        System.out.println("satisifed: " + s);
                        System.out.println("violated: " + v);
                        System.out.println("Score Of " + partTwo.get(i) + " : " + finalScore);
                    }
                }

                if (flag == 1) {
                 
                    ArrayList<String> singletonTexaList = new ArrayList<String>();
                    ArrayList<Integer> singletonGainList = new ArrayList<Integer>();

                    while (true && (!tempItemList.isEmpty())) {
                        
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
                                    for(int i = 0 ; i < qSet.size(); i++) {
                                        
                                        sCurrentLine = qSet.get(i);
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
                                s = 0;
                                v = 0;
                                System.out.println("PartOne: " + partOne.toString());
                                System.out.println("PartTwo: " + partTwo.toString());
                                System.out.println("PartPartOne: " + partOne.toString());
                                texa2DPartOneList.add(new ArrayList<String>(partOne));
                                texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                System.out.println("PartPartTwo: " + partTwo.toString());

                                for(int i = 0; i < qSet.size(); i++) {
                                    
                                    sCurrentLine = qSet.get(i);
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
                                    texa2DPartOneList.add(new ArrayList<String>(partOne));
                                    texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                    exit_flag = 1;
                                    break;
//                                } else if (tempMaxMulItem.size() == tempGainList.size()) {
//                                    for (int k = 0; k < tempGainList.size(); k++) {
//                                        completedItem.add(tempItemList.get(k));
//                                        //gainList.add(tempGainList.get(k));
//                                        gainList.add(0);             
//                                    }
//                                    texa2DPartOneList.add(new ArrayList<String>(partOne));
//                                    texa2DPartTwoList.add(new ArrayList<String>(partTwo));
//                                    exit_flag = 1;
//                                    break;
                                } else {
                                    int checkCount = 0;
                                    System.out.println("Before Max gain: " + maxGain);
                                    System.out.println("Before Temp Gain LIst: " + tempGainList.toString());
                                    ArrayList<Integer> temp = new ArrayList<Integer>();
                                    temp.addAll(tempGainList);
                                    for (int k = 0; k < tempGainList.size(); k++) {
                                        if (maxGain == tempGainList.get(k)) {
                                            System.out.println("B check Gain LIst: " + tempGainList.toString());
                                            //tempGainList.remove(k);
                                            temp.add(k);
                                            //tempItemList.remove(k);
                                            //singletonGainList.add(tempGainList.get(k));
                                            singletonGainList.add(0);
                                            singletonTexaList.add(tempItemList.get(k));
                                            checkCount++;
                                        }

                                    }
                                    
                                    if(!temp.isEmpty()) {
                                        for(int k = 0; k < temp.size(); k++) {
                                            tempGainList.remove(temp.get(k));
                                            tempItemList.remove(temp.get(k));
                                        }
                                    }
                                    
                                    if (tempGainList.isEmpty()) {
                                        for (int k = 0; k < singletonGainList.size(); k++) {
                                            completedItem.add(singletonTexaList.get(k));
                                            gainList.add(0);
                                        }
                                        texa2DPartOneList.add(new ArrayList<String>(partOne));
                                        texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                        exit_flag = 1;
                                        break;
                                    }
                                    System.out.println("After Temp Gain LIst: " + tempGainList.toString());
                                    System.out.println("Max Gain: " + maxGain);
                                    System.out.println("Check Count : " + checkCount);
                                    System.out.println("New Gain List: " + gainList.toString());
                                }
                            }
                            if (exit_flag == 1) {
                                break;
                            }
                        } else {

                            //check for singleton must be written
                            
                            ArrayList<String> tempPartOne = new ArrayList<String>();
                            ArrayList<String> tempPartTwo = new ArrayList<String>();
                            ArrayList<String> TempGainList = new ArrayList<String>();
                            ArrayList<String> TempTexaList = new ArrayList<String>();
                            
                            TempGainList.addAll(TempGainList);
                            TempTexaList.addAll(tempItemList);
                            tempPartOne.addAll(partOne);
                            tempPartTwo.addAll(partTwo);

                            if (tempPartOne.contains(TempTexaList.get(index))) {
                                tempPartTwo.add(TempTexaList.get(index));
                                tempPartOne.remove(TempTexaList.get(index));
                            } else {
                                tempPartOne.add(TempTexaList.get(index));
                                tempPartTwo.remove(TempTexaList.get(index));
                            }
                            
                            if(tempPartOne.size() <= 1 || tempPartTwo.size() <= 1) {
                                //TempTexaList.remove(index);
                                tempItemList.remove(index);
                                tempGainList.remove(Collections.max(tempGainList));
                                System.out.println("Singleton in else part.............................");

                            }
                            else {
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
                                s = 0;
                                v = 0;
                                System.out.println("PartOne: " + partOne.toString());
                                System.out.println("PartTwo: " + partTwo.toString());
                                //System.out.println("PartPartOne: " + finalPartOneList.toString());
                                texa2DPartOneList.add(new ArrayList<String>(partOne));
                                texa2DPartTwoList.add(new ArrayList<String>(partTwo));
                                //System.out.println("PartPartTwo: " + finalPartTwoList.toString());

                                for (int i = 0; i < qSet.size(); i++) {

                                    sCurrentLine = qSet.get(i);
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

                                tempGainList.clear();
                                tempItemList.clear();
                                exit_flag = 1;
                                break;
                            }
                            if(exit_flag == 1)
                                break;
                        }
                        if (exit_flag == 1) {
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
                    cumulativeMaxTexaList.add(completedItem.get(i));
                    System.out.println("Cumulataiv: " + cumulativeGain.toString());
                } else {
                    System.out.println("Cumul: " + cumulativeGain.get(cumulativeGain.size() - 1));
                    cumulativeMaxTexaList.add(completedItem.get(i));
                    cumulativeGain.add((cumulativeGain.get((cumulativeGain.size() - 1)) + gainList.get(i)));
                    System.out.println("Cumulataiv..............");
                }
            }
            System.out.println(cumulativeGain.toString());
            System.out.println(texa2DPartOneList.toString());
            System.out.println(texa2DPartTwoList.toString());
            System.out.println("CumuStep");

            if (Collections.max(cumulativeGain) <= 0 || (!storeCumulativeGain.isEmpty()  && (storeCumulativeGain.contains(cumulativeGain)))) {
                System.out.println("Targert Gain: " + targetGain);
                if(targetGain == 1) {
                    int index = cumulativeGain.indexOf(Collections.max(cumulativeGain));
                    partOne.clear();
                    partTwo.clear();
                    partOne.addAll(texa2DPartOneList.get(index));
                    partTwo.addAll(texa2DPartTwoList.get(index));
                }
                
                System.out.println("Final Out put.....");
                System.out.println("Leaf: " + finalLeaf);
                System.out.println("Final Part One:" + partOne.toString());
                System.out.println("Final Part Two:" + partTwo.toString());

                //break;
                targetGain = 0;
                result.add(new ArrayList<String>(partOne));
                result.add(new ArrayList<String>(partTwo));
                return result;
            } else {
                
                storeCumulativeGain.add(new ArrayList<Integer>(cumulativeGain));
                System.out.println("Cumulative Step : " + (targetGain++ + 1));
                    //break;

                System.out.println("Max cumulative gain: " + Collections.max(cumulativeGain));
                int index = cumulativeGain.indexOf(Collections.max(cumulativeGain));
                for(int j = 0; j < cumulativeGain.size(); j++) {
                    if(Collections.max(cumulativeGain) == cumulativeGain.get(j)) {
                        index = j;
                        break;
                    }
                }
                
                System.out.println("Index of Max: " + index);
                System.out.println(texa2DPartOneList.get(index));
                System.out.println(texa2DPartTwoList.get(index));

                cumulativeGain.clear();
                completedItem.clear();
                gainList.clear();
                partOne.clear();
                partTwo.clear();
                partOne.addAll(texa2DPartOneList.get(index));
                partTwo.addAll(texa2DPartTwoList.get(index));

                s = 0;
                v = 0;
                System.out.println("Final part check:");
                System.out.println("One: " + finalPartOneList.toString());
                System.out.println("Two: " + finalPartTwoList.toString());

                System.out.println("PartOne: " + partOne.toString());
                System.out.println("PartTwo: " + partTwo.toString());

//                if (targetGain == 3) {
//                    targetGain = 0;
//                }
//                targetGain = 0;
                finalPartOneList.clear();
                finalPartTwoList.clear();
                for(int j = 0; j < qSet.size(); j++) {
                    sCurrentLine = qSet.get(j);
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
        return null;
    }
}
