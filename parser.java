/**
 * Created by varnitjain on 11/26/16.
 */

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.*;


public class parser{

    public HashMap<String,Set<String>> all_authors= new HashMap<>();
    public ArrayList<Set<String>> big = new ArrayList<>();
    public HashSet<String> selected_authors= new HashSet<>();
    public ArrayList<Publications> publicationList = new ArrayList<>();
    public HashMap<Set<String>,Integer> numberOfPublications = new HashMap<>();
    public HashMap<Set<String>,ArrayList<String>> author_years= new HashMap<>();
    public ArrayList<Double> predictions = new ArrayList<>();
    public ArrayList<Double> predictionsL = new ArrayList<>();
    public ArrayList<Double> predictionsA = new ArrayList<>();
    public ArrayList<Integer> knextFreq = new ArrayList<>();

    public int limitYear;

    //public


    public parser(){

        //System.out.println("poop");
        //jdk.xml.entityExpansionLimit=0;

        createAllAuthors();
        System.out.println("All author map done");

        addNop();
        System.out.println("Nop Done");


        //System.out.println(imainObject.author_years);

        //predict();


//        System.out.println(imainObject.all_authors.keySet().size());
//        System.out.println(imainObject.big.size());


//        for(Set<String> i : imainObject.numberOfPublications.keySet()){
//            System.out.println(i);
//            System.out.println(imainObject.numberOfPublications.get(i));
//            System.out.println("\n");
//        }


        //imainObject.searchByAuthor();
        //imainObject.searchByTitle();
        //imainObject.sortByDate();
        //imainObject.sortByPriority();



        /*for(Publications pub1 : imainObject.publicationList){
            pub1.printVar();
            System.out.println("\n");
        }*/



    }

    public void clearAll() {
        this.selected_authors.clear();
        this.publicationList.clear();
        this.predictions.clear();
        this.predictionsL.clear();
        this.predictionsA.clear();
        this.knextFreq.clear();
    }

    public void createAllAuthors(){



        try{


            SAXParserFactory fac1 = SAXParserFactory.newInstance();
            SAXParser parser1 = fac1.newSAXParser();

            DefaultHandler handler1 = new DefaultHandler(){


                Set<String> tempSet = new HashSet<>();
                String temp1 = "";
                boolean iswww=false;


                //different boolean values
                boolean bauthname = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

                    //System.out.println("Start : " + qName);


                    if(qName.equals("www")){
                        tempSet.clear();
                        iswww=true;
                    }

                    if(qName.equalsIgnoreCase("author")){
                        bauthname = true;
                        temp1="";
                    }



                    //
                    //
                    //

                }

                public void endElement(String uri, String localName, String qName) throws SAXException{

                    if(qName.equalsIgnoreCase("www")){
                        big.add(new HashSet<>(tempSet));
                        iswww=false;
                    }

                    if(qName.equalsIgnoreCase("author")){
                        if(iswww){
                            //if(temp1.length()>5)
                            tempSet.add(temp1);
                        }
                        bauthname = false;
                    }

                    //System.out.println("End : " + qName);


                }

                public void characters(char ch[], int start, int length) throws SAXException{

                    if(bauthname){

//                      System.out.println("Author name: " + temp1);

                        //bauthname=false;
                        temp1+= new String(ch,start,length);


                    }

                    //
                    //
                    //
                    //
                    //


                }


            };

            File file = new File("mainfile.xml");
            InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            parser1.parse(is, handler1);
            //parser1.parse("mainfile.xml",handler1);

        }
        catch(Exception e){
            e.printStackTrace();
        }


        /*for(Set<String> i : big ){
            for(String j : i )
                if(j.contains("Chen Li"))
                    System.out.println(i);

        }*/
        //System.out.println(big);


        for(Set<String> i : big ) {
            for (String j : i){
                all_authors.put(j,i);
            }
        }

    }

    public void addNop(){

        System.out.println("nop beg");

        for(Set<String> i : all_authors.values()){
            //System.out.println(i);
            numberOfPublications.put(i,0);
            author_years.put(i,new ArrayList<>());
        }


//        for(Set<String> i : numberOfPublications.keySet()){
//            System.out.println(i);
//        }


        System.out.println("nop before parse");

        try{

            SAXParserFactory fac1 = SAXParserFactory.newInstance();
            SAXParser parser1 = fac1.newSAXParser();



            DefaultHandler handler1 = new DefaultHandler(){

                Set<String> tempSet = new HashSet<>();
                String temp_author = "";
                String temp_year = "";

                boolean bauthor=false;
                boolean byear=false;
                String key;
                int kflag=0;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

                    //System.out.println("Start : " + qName);

                    if(qName.equals("www")||qName.equals("article")||qName.equals("inproceedings")||qName.equals("proceedings")||qName.equals("book")||qName.equals("incollection")||qName.equals("phdthesis")||qName.equals("mastersthesis")){

                        tempSet.clear();
                    }

                    if(qName.equals("www")){
                        key = attributes.getValue("key");
                        if(key.startsWith("homepages")){
                            kflag=1;
                        }else{
                            kflag=0;
                        }
                    }

                    if(qName.equals("author")){
                        temp_author="";
                        bauthor=true;
                    }
                    if(qName.equals("year")){
                        temp_year="";
                        byear=true;
                    }


                }

                public void endElement(String uri, String localName, String qName) throws SAXException{


                    if(kflag==0){
                        if(qName.equalsIgnoreCase("author")){

                            tempSet.add(temp_author);

                            if(all_authors.keySet().contains(temp_author)) {
                                //System.out.println("wow");


                                Set<String> tempSet = all_authors.get(temp_author);

                                //numberOfPublications.get(tempSet)=;
                                numberOfPublications.put(tempSet, numberOfPublications.get(tempSet) + 1);

                            }
                            bauthor=false;
                            //tempAuthList.add(temp_author);
                        }


                        if(qName.equalsIgnoreCase("year")){

                            //add year
                            for(String iauth : tempSet){
                                if(all_authors.keySet().contains(iauth)) {
                                    Set<String> tempAuthSet = all_authors.get(iauth);
                                    ArrayList<String> tempYearList = author_years.get(tempAuthSet);
                                    tempYearList.add(temp_year);
                                    author_years.put(tempAuthSet, tempYearList);
                                }
                            }

                            byear=false;

                        }
                    }


                }



                public void characters(char ch[], int start, int length) throws SAXException{

                    if(bauthor){
//                      System.out.println("Author name: " + temp1);
                        temp_author+= new String(ch,start,length);
                    }

                    if(byear){
//                      System.out.println("Author name: " + temp1);
                        temp_year+= new String(ch,start,length);
                    }


                }


            };

            File file = new File("mainfile.xml");
            InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            parser1.parse(is, handler1);
            //parser1.parse("mainfile.xml",handler1);

        }
        catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("nop after");



    }

    public void searchByAuthor(String testAuthor){

        //Scanner s1 =  new Scanner(System.in);
        //String testAuthor = s1.nextLine();


        for(String iter1 : all_authors.keySet()){
            if(iter1.contains(testAuthor)){
                selected_authors.add(iter1);
            }
        }

        //System.out.println(selected_authors);



        try{

            SAXParserFactory fac1 = SAXParserFactory.newInstance();
            SAXParser parser1 = fac1.newSAXParser();



            DefaultHandler handler1 = new DefaultHandler(){


                Publications temp_pubc = new Publications();
                ArrayList<String> tempAuthList = new ArrayList<>();
                String temp_author = "";
                String temp_title = "";
                String temp_pages = "";
                String temp_year = "";
                String temp_volume = "";
                String temp_jbt = "";
                String temp_url = "";

                boolean author_flag = false;

                boolean bauthor=false;
                boolean btitle=false;
                boolean bpages=false;
                boolean byear=false;
                boolean bvolume=false;
                boolean bjbt=false;
                boolean burl=false;
                String key;


                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

                    //System.out.println("Start : " + qName);

                    if(qName.equals("article")||qName.equals("inproceedings")||qName.equals("proceedings")||qName.equals("book")||qName.equals("incollection")||qName.equals("phdthesis")||qName.equals("mastersthesis")){
                        temp_pubc.empty();
                        author_flag=false;
                    }
                    /*else if(qName.equals("www")){
                        key = attributes.getValue("key");
                        if(!key.startsWith("homepages")){
                            temp_pubc.empty();
                            author_flag=false;
                        }
                    }*/
                    else if(qName.equals("author")){
                        temp_author="";
                        bauthor=true;
                    }else if(author_flag){
                        if(qName.equals("title")){
                            temp_title="";
                            btitle=true;
                        }else if(qName.equals("pages")){
                            temp_pages="";
                            bpages=true;
                        }else if(qName.equals("year")){
                            temp_year="";
                            byear=true;
                        }else if(qName.equals("volume")){
                            temp_volume="";
                            bvolume=true;
                        }else if(qName.equals("journal") || qName.equals("booktitle")){
                            temp_jbt="";
                            bjbt=true;
                        }else if(qName.equals("url")){
                            temp_url="";
                            burl=true;
                        }
                    }



                }

                public void endElement(String uri, String localName, String qName) throws SAXException{


                    if(author_flag){
                        //System.out.println("poopy");
                        if(qName.equals("article")||qName.equals("inproceedings")||qName.equals("proceedings")||qName.equals("book")||qName.equals("incollection")||qName.equals("phdthesis")||qName.equals("mastersthesis")){
                            //System.out.println("poop");
                            temp_pubc.setAuthors(tempAuthList);
                            //temp_pubc.printVar();
                            publicationList.add(new Publications(temp_pubc));
                            //System.out.println(temp_pubc.getAuthors());
                            //System.out.println("wow");
                            author_flag=false;
                        }
                        /*else if(qName.equals("www")){
                            if(!key.startsWith("homepages")){
                                temp_pubc.setAuthors(tempAuthList);
                                //temp_pubc.printVar();
                                publicationList.add(new Publications(temp_pubc));
                                author_flag=false;
                            }
                        }*/
                    }

                    if(qName.equalsIgnoreCase("author")){

                        tempAuthList.add(temp_author);

                        if(selected_authors.contains(temp_author) && temp_author.length()>2){
                            author_flag=true;
                            //System.out.println();
                            double tpr = ((double)testAuthor.length())/((double)temp_author.length());
                            //System.out.println(tpr);
                            temp_pubc.setPriority(tpr);

                        }
                        //search for author
                        bauthor=false;

                    }

                    if(author_flag){

                        if(qName.equalsIgnoreCase("title")){
                            btitle=false;
                            temp_pubc.setTitle(temp_title);
                        }
                        else if(qName.equalsIgnoreCase("pages")){
                            bpages=false;
                            temp_pubc.setPages(temp_pages);
                        }
                        else if(qName.equalsIgnoreCase("year")){
                            byear=false;
                            temp_pubc.setYear(temp_year);
                        }
                        else if(qName.equalsIgnoreCase("volume")){
                            bvolume=false;
                            temp_pubc.setVolume(temp_volume);
                        }
                        else if(qName.equalsIgnoreCase("journal")){
                            bjbt=false;
                            temp_pubc.setJbt(temp_jbt);
                        }
                        else if(qName.equalsIgnoreCase("booktitle")){
                            bjbt=false;
                            temp_pubc.setJbt(temp_jbt);
                        }
                        else if(qName.equalsIgnoreCase("url")){
                            burl=false;
                            temp_pubc.setUrl(temp_url);
                        }



                    }

                    //if(qName.equalsIgnoreCase("author")){
                    //    if(iswww){
                    //        tempSet.add(temp1);
                    //    }
                    //    bauthname = true;
                    //}

                    //System.out.println("End : " + qName);


                }



                public void characters(char ch[], int start, int length) throws SAXException{

                    if(bauthor){
//                      System.out.println("Author name: " + temp1);

                        temp_author+= new String(ch,start,length);


                    }
                    if(btitle){
//                      System.out.println("Author name: " + temp1);

                        temp_title+= new String(ch,start,length);


                    }
                    if(bpages){
//                      System.out.println("Author name: " + temp1);

                        temp_pages+= new String(ch,start,length);


                    }
                    if(byear){
//                      System.out.println("Author name: " + temp1);

                        temp_year+= new String(ch,start,length);


                    }
                    if(bvolume){
//                      System.out.println("Author name: " + temp1);

                        temp_volume+= new String(ch,start,length);


                    }
                    if(bjbt){
//                      System.out.println("Author name: " + temp1);

                        temp_jbt+= new String(ch,start,length);


                    }
                    if(burl){
//                      System.out.println("Author name: " + temp1);

                        temp_url+= new String(ch,start,length);


                    }

                }


            };

            File file = new File("mainfile.xml");
            InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            parser1.parse(is, handler1);
            //parser1.parse("mainfile.xml",handler1);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("mofo");

        /*for(Publications pub1 : publicationList){
            pub1.printVar();
            System.out.println("\n");
        }*/

    }

    public void searchByTitle(String testTitle){

        //Scanner s1 =  new Scanner(System.in);
        //String testTitle = s1.nextLine();


        try{

            SAXParserFactory fac1 = SAXParserFactory.newInstance();
            SAXParser parser1 = fac1.newSAXParser();



            DefaultHandler handler1 = new DefaultHandler(){


                Publications temp_pubc = new Publications();
                ArrayList<String> tempAuthList = new ArrayList<>();
                String temp_author = "";
                String temp_title = "";
                String temp_pages = "";
                String temp_year = "";
                String temp_volume = "";
                String temp_jbt = "";
                String temp_url = "";

                boolean title_flag = false;

                boolean bauthor=false;
                boolean btitle=false;
                boolean bpages=false;
                boolean byear=false;
                boolean bvolume=false;
                boolean bjbt=false;
                boolean burl=false;
                String key;


                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

                    //System.out.println("Start : " + qName);

                    if(qName.equals("article")||qName.equals("inproceedings")||qName.equals("proceedings")||qName.equals("book")||qName.equals("incollection")||qName.equals("phdthesis")||qName.equals("mastersthesis")){
                        temp_pubc.empty();
                        title_flag=false;
                    }/*else if(qName.equals("www")){
                        key = attributes.getValue("key");
                        if(!key.startsWith("homepages")){
                            temp_pubc.empty();
                            title_flag=false;
                        }
                    }*/
                    else if(qName.equals("author")){
                        temp_author="";
                        bauthor=true;
                    }else if(qName.equals("title")){
                        temp_title="";
                        btitle=true;
                    }else if(qName.equals("pages")){
                        temp_pages="";
                        bpages=true;
                    }else if(qName.equals("year")){
                        temp_year="";
                        byear=true;
                    }else if(qName.equals("volume")){
                        temp_volume="";
                        bvolume=true;
                    }else if(qName.equals("journal") || qName.equals("booktitle")){
                        temp_jbt="";
                        bjbt=true;
                    }else if(qName.equals("url")){
                        temp_url="";
                        burl=true;
                    }


                }

                public void endElement(String uri, String localName, String qName) throws SAXException{


                    if(title_flag){
                        //System.out.println("poopy");
                        if(qName.equals("article")||qName.equals("inproceedings")||qName.equals("proceedings")||qName.equals("book")||qName.equals("incollection")||qName.equals("phdthesis")||qName.equals("mastersthesis")){
                            //System.out.println("poop");
                            temp_pubc.setAuthors(tempAuthList);
                            //temp_pubc.printVar();
                            publicationList.add(new Publications(temp_pubc));
                            title_flag=false;
                        }/*else if(qName.equals("www")){
                            if(!key.startsWith("homepages")){
                                temp_pubc.setAuthors(tempAuthList);
                                //temp_pubc.printVar();
                                publicationList.add(new Publications(temp_pubc));
                                title_flag=false;
                            }
                        }*/
                    }

                    if(qName.equalsIgnoreCase("author")){
                        tempAuthList.add(temp_author);
                        bauthor=false;
                    }
                    else if(qName.equalsIgnoreCase("title")){

                        temp_pubc.setTitle(temp_title);

                        if(temp_title.contains(testTitle)){
                            title_flag=true;
                            double tpr=((double)testTitle.length())/((double)temp_title.length());
                            //System.out.println(tpr);
                            temp_pubc.setPriority(tpr);
                        }
                        btitle=false;
                    }
                    else if(qName.equalsIgnoreCase("pages")){
                        bpages=false;
                        temp_pubc.setPages(temp_pages);
                    }
                    else if(qName.equalsIgnoreCase("year")){
                        byear=false;
                        temp_pubc.setYear(temp_year);
                    }
                    else if(qName.equalsIgnoreCase("volume")){
                        bvolume=false;
                        temp_pubc.setVolume(temp_volume);
                    }
                    else if(qName.equalsIgnoreCase("journal")){
                        bjbt=false;
                        temp_pubc.setJbt(temp_jbt);
                    }
                    else if(qName.equalsIgnoreCase("booktitle")){
                        bjbt=false;
                        temp_pubc.setJbt(temp_jbt);
                    }
                    else if(qName.equalsIgnoreCase("url")){
                        burl=false;
                        temp_pubc.setUrl(temp_url);
                    }




                    //if(qName.equalsIgnoreCase("author")){
                    //    if(iswww){
                    //        tempSet.add(temp1);
                    //    }
                    //    bauthname = true;
                    //}

                    //System.out.println("End : " + qName);


                }



                public void characters(char ch[], int start, int length) throws SAXException{

                    if(bauthor){
//                      System.out.println("Author name: " + temp1);

                        temp_author+= new String(ch,start,length);


                    }
                    if(btitle){
//                      System.out.println("Author name: " + temp1);

                        temp_title+= new String(ch,start,length);


                    }
                    if(bpages){
//                      System.out.println("Author name: " + temp1);

                        temp_pages+= new String(ch,start,length);


                    }
                    if(byear){
//                      System.out.println("Author name: " + temp1);

                        temp_year+= new String(ch,start,length);


                    }
                    if(bvolume){
//                      System.out.println("Author name: " + temp1);

                        temp_volume+= new String(ch,start,length);


                    }
                    if(bjbt){
//                      System.out.println("Author name: " + temp1);

                        temp_jbt+= new String(ch,start,length);


                    }
                    if(burl){
//                      System.out.println("Author name: " + temp1);

                        temp_url+= new String(ch,start,length);


                    }

                }


            };

            File file = new File("mainfile.xml");
            InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            parser1.parse(is, handler1);

            //parser1.parse("mainfile.xml",handler1);

        }
        catch(Exception e){
            e.printStackTrace();
        }


        /*for(Publications pub1 : publicationList){
            pub1.printVar();
            System.out.println("\n");
        }*/

    }

    public void sortByDate(){
        Collections.sort(publicationList, new CustomComparatorDate());
    }

    public void sortByPriority(){
        Collections.sort(publicationList, new CustomComparatorPriority());
    }

    public void predict(String auth1, String auth2, String auth3, String auth4, String auth5,int limitYear){

        predictions.clear();
        knextFreq.clear();
        this.limitYear=limitYear;

        ArrayList<String> auth1List = null;
        ArrayList<String> auth2List = null;
        ArrayList<String> auth3List = null;
        ArrayList<String> auth4List = null;
        ArrayList<String> auth5List = null;


        if(all_authors.keySet().contains(auth1)){
            auth1List=author_years.get(all_authors.get(auth1));  //list of years of publications for auth1
        }
        if(all_authors.keySet().contains(auth2)){
            auth2List=author_years.get(all_authors.get(auth2));  //list of years of publications for auth2
        }
        if(all_authors.keySet().contains(auth3)){
            auth3List=author_years.get(all_authors.get(auth3));  //list of years of publications for auth3
        }
        if(all_authors.keySet().contains(auth4)){
            auth4List=author_years.get(all_authors.get(auth4));
        }
        if(all_authors.keySet().contains(auth5)){
            auth5List=author_years.get(all_authors.get(auth5));
        }



        /*System.out.println(auth1List);
        System.out.println(auth2List);
        System.out.println(auth3List);
        System.out.println(auth4List);
        System.out.println(auth5List);*/

/*
        double meanX;
        double meanY;
        double squareSumDevX;
        double squareSumProdDevXY;
        int count;
        double b1;  // y = b0 + b1(x)
        double b0;
        double predForYearX = 0;
*/

        int count=0;
        int sum=0;

        HashMap<Integer,Integer> predMap = new HashMap<>();

        count=0;
        sum=0;

        if(all_authors.keySet().contains(auth1)) {

            for (String i : auth1List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            predictByLagrange(predMap);
            predictByLR(predMap);

            count=predMap.keySet().size();
            for(Integer p : predMap.keySet()){
                if(limitYear>p){
                    count++;
                    sum+=predMap.get(p);
                }
            }
            predictionsA.add((double)sum/count);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
            predictionsL.add(0.0);
            predictionsA.add(0.0);
        }


        if(all_authors.keySet().contains(auth2)) {

            count=0;
            sum=0;

            for (String i : auth2List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            predictByLagrange(predMap);
            predictByLR(predMap);


            count=predMap.keySet().size();
            for(Integer p : predMap.keySet()){
                if(limitYear>p){
                    count++;
                    sum+=predMap.get(p);
                }
            }
            predictionsA.add((double)sum/count);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
            predictionsL.add(0.0);
            predictionsA.add(0.0);
        }

        if(all_authors.keySet().contains(auth3)) {

            count=0;
            sum=0;

            for (String i : auth3List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            predictByLagrange(predMap);
            predictByLR(predMap);


            count=predMap.keySet().size();
            for(Integer p : predMap.keySet()){
                if(limitYear>p){
                    count++;
                    sum+=predMap.get(p);
                }
            }
            predictionsA.add((double)sum/count);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
            predictionsL.add(0.0);
            predictionsA.add(0.0);
        }

        if(all_authors.keySet().contains(auth4)) {

            count=0;
            sum=0;

            for (String i : auth4List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            predictByLagrange(predMap);
            predictByLR(predMap);


            count=predMap.keySet().size();
            for(Integer p : predMap.keySet()){
                if(limitYear>p){
                    count++;
                    sum+=predMap.get(p);
                }
            }
            predictionsA.add((double)sum/count);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
            predictionsL.add(0.0);
            predictionsA.add(0.0);
        }

        if(all_authors.keySet().contains(auth5)) {

            count=0;
            sum=0;

            for (String i : auth5List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            predictByLagrange(predMap);
            predictByLR(predMap);


            count=predMap.keySet().size();
            for(Integer p : predMap.keySet()){
                if(limitYear>p){
                    count++;
                    sum+=predMap.get(p);
                }
            }
            predictionsA.add((double)sum/count);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
            predictionsL.add(0.0);
            predictionsA.add(0.0);
        }



        /*predictions.add(0.0);
        predictions.add(0.0);
        predictions.add(0.0);
        predictions.add(0.0);
        knextFreq.add(0);
        knextFreq.add(0);
        knextFreq.add(0);
        knextFreq.add(0);

/*
        if(all_authors.keySet().contains(auth2)) {

            kflag=0;

            for (String i : auth2List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            count = 0;
            meanX = 0;
            meanY = 0;
            squareSumDevX = 0;
            squareSumProdDevXY = 0;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    meanX += i;
                    count++;
                    meanY += predMap.get(i);
                }
            }
            meanX = meanX / count;
            meanY = meanY / count;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    squareSumDevX += (i - meanX) * (i - meanX);
                    squareSumProdDevXY += (i - meanX) * (predMap.get(i) - meanY);
                }
                if (i == limitYear + 1){
                    knextFreq.add(predMap.get(i));
                    kflag=1;
                }
            }

            if(kflag==0){
                knextFreq.add(0);
            }

            b1 = squareSumProdDevXY / squareSumDevX;
            b0 = meanY - (meanX) * b1;

            predForYearX = b0 + (b1) * (limitYear + 1);
            if (squareSumDevX == 0) {
                predForYearX = 0;
            }


            //System.out.println(predMap);
            //System.out.println(predForYearX);
            predictions.add(predForYearX);

            predMap.clear();
        }else{
            knextFreq.add(0);
            predictions.add(0.0);
        }

        if(all_authors.keySet().contains(auth3)){

            kflag=0;

            for (String i : auth3List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            count = 0;
            meanX = 0;
            meanY = 0;
            squareSumDevX = 0;
            squareSumProdDevXY = 0;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    meanX += i;
                    count++;
                    meanY += predMap.get(i);
                }
            }
            meanX = meanX / count;
            meanY = meanY / count;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    squareSumDevX += (i - meanX) * (i - meanX);
                    squareSumProdDevXY += (i - meanX) * (predMap.get(i) - meanY);
                }
                if (i == limitYear + 1){
                    knextFreq.add(predMap.get(i));
                    kflag=1;
                }
            }

            if(kflag==0){
                knextFreq.add(0);
            }

            b1 = squareSumProdDevXY / squareSumDevX;
            b0 = meanY - (meanX) * b1;

            predForYearX = b0 + (b1) * (limitYear + 1);
            if (squareSumDevX == 0) {
                predForYearX = 0;
            }

            //System.out.println(predMap);
            //System.out.println(predForYearX);
            predictions.add(predForYearX);

            predMap.clear();

        }else{
            knextFreq.add(0);
            predictions.add(0.0);
        }

        if(all_authors.keySet().contains(auth4)) {

            kflag=0;

            for (String i : auth4List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            count = 0;
            meanX = 0;
            meanY = 0;
            squareSumDevX = 0;
            squareSumProdDevXY = 0;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    meanX += i;
                    count++;
                    meanY += predMap.get(i);
                }
            }
            meanX = meanX / count;
            meanY = meanY / count;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    squareSumDevX += (i - meanX) * (i - meanX);
                    squareSumProdDevXY += (i - meanX) * (predMap.get(i) - meanY);
                }
                if (i == limitYear + 1){
                    knextFreq.add(predMap.get(i));
                    kflag=1;
                }
            }

            if(kflag==0){
                knextFreq.add(0);
            }

            b1 = squareSumProdDevXY / squareSumDevX;
            b0 = meanY - (meanX) * b1;

            predForYearX = b0 + (b1) * (limitYear + 1);
            if (squareSumDevX == 0) {
                predForYearX = 0;
            }

            //System.out.println(predMap);
            //System.out.println(predForYearX);
            predictions.add(predForYearX);


            predMap.clear();
        }else{
            knextFreq.add(0);
            predictions.add(0.0);
        }

        if(all_authors.keySet().contains(auth5)) {

            kflag=0;

            for (String i : auth5List) {

                int tempYear = Integer.parseInt(i);

                if (predMap.keySet().contains(tempYear)) {
                    predMap.put(tempYear, predMap.get(tempYear) + 1);
                } else {
                    predMap.put(tempYear, 1);
                }

            }

            count = 0;
            meanX = 0;
            meanY = 0;
            squareSumDevX = 0;
            squareSumProdDevXY = 0;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    meanX += i;
                    count++;
                    meanY += predMap.get(i);
                }
            }
            meanX = meanX / count;
            meanY = meanY / count;

            for (int i : predMap.keySet()) {
                if (i <= limitYear) {
                    squareSumDevX += (i - meanX) * (i - meanX);
                    squareSumProdDevXY += (i - meanX) * (predMap.get(i) - meanY);
                }
                if (i == limitYear + 1){
                    knextFreq.add(predMap.get(i));
                    kflag=1;
                }
            }

            if(kflag==0){
                knextFreq.add(0);
            }

            b1 = squareSumProdDevXY / squareSumDevX;
            b0 = meanY - (meanX) * b1;

            predForYearX = b0 + (b1) * (limitYear + 1);
            if (squareSumDevX == 0) {
                predForYearX = 0;
            }

            //System.out.println(predMap);
            //System.out.println(predForYearX);
            predictions.add(predForYearX);

            predMap.clear();


        }else{
            knextFreq.add(0);
            predictions.add(0.0);
        }


        */
    }

    public void predictByLR(HashMap<Integer,Integer> predMap){

        double meanX=0;
        double meanY=0;
        double squareSumDevX=0;
        double squareSumProdDevXY=0;
        int count=0;
        double b1=0;  // y = b0 + b1(x)
        double b0=0;
        double predForYearX = 0;

        count = 0;
        meanX = 0;
        meanY = 0;
        squareSumDevX = 0;
        squareSumProdDevXY = 0;

        for (int i : predMap.keySet()) {
            if (i <= limitYear) {
                meanX += i;
                count++;
                meanY += predMap.get(i);
            }
        }
        meanX = meanX / count;
        meanY = meanY / count;


        //System.out.println("mX "+meanX);
        //System.out.println("mY "+meanY);

        for (int i : predMap.keySet()) {
            if (i <= limitYear) {
                squareSumDevX += (i - meanX) * (i - meanX);
                squareSumProdDevXY += (i - meanX) * (predMap.get(i) - meanY);
            }
            if (i == limitYear + 1){
                knextFreq.add(predMap.get(i));
            }
        }



        b1 = squareSumProdDevXY / squareSumDevX;
        b0 = meanY - (meanX) * b1;

        predForYearX = b0 + (b1) * (limitYear + 1);
        if (squareSumDevX == 0) {
            predForYearX = 0;
        }

        predictions.add(predForYearX);

        if(predMap.keySet().contains(limitYear+1)){
            knextFreq.add(predMap.get(limitYear+1));
        }else{
            knextFreq.add(0);
        }


    }

    public void predictByLagrange(HashMap<Integer,Integer> predMap){

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        for(Integer i: predMap.keySet()){
            if(i<=limitYear){
                x.add(i);
                y.add(predMap.get(i));
            }
        }

        //System.out.println(predMap);

        double num=1;
        double den=1;
        int x_main = limitYear+1;
        double sum=0;

        for(int j=0;j<y.size();j++){
            for(int i=0;i<x.size();i++){
                if(i!=j) {
                    num *= (x_main - x.get(i));
                    den *= (x.get(j) - x.get(i));
                }
            }
            sum+=(num/den)*y.get(j);
        }

        if(sum<0)
            sum*=-1;

        //System.out.println(num + " " + den + " " +  sum);
        predictionsL.add(sum);

        if(predMap.keySet().contains(limitYear+1)){
            knextFreq.add(predMap.get(limitYear+1));
        }else{
            knextFreq.add(0);
        }


    }

}
