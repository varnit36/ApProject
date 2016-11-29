import java.util.ArrayList;

public class Publications{

    private ArrayList<String> authors;
    private String title;
    private String pages;
    private String year;
    private String volume;
    private String jbt;
    private String url;
    private double priority;


    public Publications(){
        authors =  new ArrayList<>();
    }

    public Publications(Publications temp){
        this.authors=new ArrayList<>(temp.getAuthors());
        //this.authors=temp.getAuthors());
        this.title=temp.getTitle();
        this.pages=temp.getPages();
        this.year=temp.getYear();
        this.volume=temp.getVolume();
        this.jbt=temp.getJbt();
        this.url=temp.getUrl();
        this.priority=temp.getPriority();

    }


    public void empty(){
        authors.clear();
        title = "";
        pages = "";
        year = "";
        volume = "";
        jbt = "";
        url = "";
        priority=0;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void addAuthors(String author) {
        this.authors.add(author);
    }

    public void setJbt(String jbt) {
        this.jbt = jbt;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getJbt() {
        return jbt;
    }

    public String getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getVolume() {
        return volume;
    }

    public String getYear() {
        return year;
    }



    public void printVar(){
        System.out.println(this.authors);
        System.out.println(this.title);
        System.out.println(this.pages);
        System.out.println(this.year);
        System.out.println(this.volume);
        System.out.println(this.jbt);
        System.out.println(this.url);
        System.out.println(this.priority);
    }




}
