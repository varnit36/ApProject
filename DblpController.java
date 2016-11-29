import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by varnitjain on 11/28/16.
 */
public class DblpController {

    private static GUI dblpView;
    private static parser dblpModel;
    private static int c1;
    private static int c2;
    private static String[][] data1;
    //private static String[][] data2 = new String[5000][4];
    private static String[][] data3 ;
    private static ArrayList<String> kAuthors;
    private static String[][] data120;// = new String[20][8];
    private static String[][] data220;// = new String[20][2];
    private static HashMap<Set<String>,Integer> nopMap;

    public static void main(String args[]){

        dblpView = new GUI();
        //System.out.println("GUI Init Done");

        dblpView.showMessage();
        dblpModel = new parser();
        dblpView.hideMessage();


    }

    public static void queryOne(String searchItem, int option, String startingYear, String customStart, String customEnd, int radioOption){


        data1= new String[500000][10];
        dblpView.showMessage();

        dblpModel.clearAll();

        System.out.println(option);
        System.out.println(radioOption);

        c1=0;
        if(option==1){
            dblpModel.searchByAuthor(searchItem);
        }else if(option==2){
            dblpModel.searchByTitle(searchItem);
        }else{
            dblpModel.searchByAuthor(searchItem);
        }


        if(radioOption==1){
            dblpModel.sortByDate();
        }else if(radioOption==2){
            dblpModel.sortByPriority();
        }else{
            dblpModel.sortByDate();
        }

        System.out.println("q1 done");

        ArrayList<Publications> tempList = new ArrayList<>();

        for(Publications tempPub : dblpModel.publicationList){

            if((!customStart.equals("yyyy")) && (!customEnd.equals("yyyy"))){

                if((Integer.parseInt(tempPub.getYear())>=Integer.parseInt(customStart))&&(Integer.parseInt(tempPub.getYear())>=Integer.parseInt(customStart))){
                    tempList.add(tempPub);
                }

            }else if(!startingYear.equals("yyyy")){

                if(Integer.parseInt(tempPub.getYear())>=Integer.parseInt(startingYear)){
                    tempList.add(tempPub);
                }

            }
            else{
                tempList.add(tempPub);
            }
        }


        dblpView.hideMessage();

        int i=1;

        System.out.println("poopypants");
        //for (Publications p : tempList)
        //    p.printVar();
        //System.out.println(tempList);

        dblpView.setNump(tempList.size());



        for(Publications temp : tempList){   //tempList = list of publications

            String authors = "";

            //temp.printVar();

            ArrayList<String> s = temp.getAuthors();

            int k=0;
            for (String s1 : s)
            {
                authors += s1 + ",";
                k++;
                if(k>=50)
                    break;
            }

            String auth_print="";
            String title_print="";

            System.out.println(temp.getTitle());

            for(int p=0;p<authors.length() && p<50; p++){
                auth_print+=authors.charAt(p);
            }
            for(int p=0;p<temp.getTitle().length() && p<50; p++){
                title_print+=temp.getTitle().charAt(p);
            }


            //System.out.println(authors);


            data1[i-1][0]=Integer.toString(i);
            data1[i-1][1]=auth_print;
            data1[i-1][2]=title_print;
            //data1[i-1][1]=authors;
            //data1[i-1][2]=temp.getTitle();
            data1[i-1][3]=temp.getPages();
            data1[i-1][4]=temp.getYear();
            data1[i-1][5]=temp.getVolume();
            data1[i-1][6]=temp.getJbt();
            data1[i-1][7]=temp.getUrl();

            System.out.println(data1[i-1][0]);

            i++;
        }
        System.out.println("out");

        c1=i-1; //length
        send201(0);
        //dblpView.setData1(data1);

    }


    public static void queryTwo(int k){

        nopMap=new HashMap<>();
        kAuthors=new ArrayList<>();
        //data1= new String[50000][10];
        dblpModel.clearAll();

        c2=0;

        nopMap = dblpModel.numberOfPublications;

        kAuthors = new ArrayList<>();

        for(Set<String> tempSet : nopMap.keySet()){
            if(nopMap.get(tempSet)>k){
                //for(String auth1 : tempSet)
                //    kAuthors.add(auth1);
                kAuthors.add((String) tempSet.toArray()[0]);
            }
        }


        /*for(Set<String> s : nopMap.keySet()){
            if(nopMap.get(s)>1000)
                System.out.println(s + " - " + nopMap.get(s));
        }*/
        System.out.println("q2 done");

        dblpView.setNump(kAuthors.size());

        c2=kAuthors.size();

        send202(0);

        /*for(int j =0; i<=500 && j<kAuthors.size(); j++){

            data2[i-1][0]=Integer.toString(i);
            data2[i-1][1]=kAuthors.get(j);

            i++;
        }

        dblpView.setData2(data2);*/

    }


    public static void queryThree(String auth1, String auth2, String auth3, String auth4, String auth5, String pred){


        data3= new String[6][5];
        dblpModel.clearAll();
        int predInt = Integer.parseInt(pred);


        System.out.println("q3 done");

        dblpModel.predict(auth1,auth2,auth3,auth4,auth5,predInt);

        data3[0][0]=auth1;
        data3[0][1]= Double.toString(dblpModel.predictions.get(0));
        data3[0][2]= Double.toString(dblpModel.predictionsL.get(0));
        data3[0][3]= Double.toString(dblpModel.predictionsA.get(0));
        data3[0][4]= Integer.toString(dblpModel.knextFreq.get(0));

        data3[1][0]=auth2;
        data3[1][1]= Double.toString(dblpModel.predictions.get(1));
        data3[1][2]= Double.toString(dblpModel.predictionsL.get(1));
        data3[1][3]= Double.toString(dblpModel.predictionsA.get(1));
        data3[1][4]= Integer.toString(dblpModel.knextFreq.get(1));

        data3[2][0]=auth3;
        data3[2][1]= Double.toString(dblpModel.predictions.get(2));
        data3[2][2]= Double.toString(dblpModel.predictionsL.get(2));
        data3[2][3]= Double.toString(dblpModel.predictionsA.get(2));
        data3[2][4]= Integer.toString(dblpModel.knextFreq.get(2));

        data3[3][0]=auth4;
        data3[3][1]= Double.toString(dblpModel.predictions.get(3));
        data3[3][2]= Double.toString(dblpModel.predictionsL.get(3));
        data3[3][3]= Double.toString(dblpModel.predictionsA.get(3));
        data3[3][4]= Integer.toString(dblpModel.knextFreq.get(3));

        data3[4][0]=auth5;
        data3[4][1]= Double.toString(dblpModel.predictions.get(4));
        data3[4][2]= Double.toString(dblpModel.predictionsL.get(4));
        data3[4][3]= Double.toString(dblpModel.predictionsA.get(4));
        data3[4][4]= Integer.toString(dblpModel.knextFreq.get(4));

        dblpView.setData3(data3);

    }


    public static void send201(int count){

        System.out.println("send q1");

        data120 = new String[20][8];
        int a=0;
        for(int i=count*20;i<=count*20+19 && i<c1;i++){
            for (int j = 0; j <=7; j++) {
//                        System.out.println(a);
//                        System.out.println(b);
                    data120[a][j] = data1[i][j];
                    if(data120[a][j].length()>=100){
                        data120[a][j]="";
                    }
                    //System.out.println(data120[a][j] + "po");
            }
            a++;
        }

        System.out.println("yo");
        System.out.println(count);

        dblpView.setData1(data120);

    }

    public static void send202(int count){
        data220 = new String[20][2];
        int a=0;
        //int b=0;

        for(int i=count*20;i<=count*20+19 && i<c2;i++){

            data220[a][0]=Integer.toString(i+1);
            data220[a][1]=kAuthors.get(i);
            a++;

        }
        dblpView.setData2(data220);
    }


}
