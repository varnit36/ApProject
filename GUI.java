//request data in actionlisteners of submitx3 and next
import com.sun.deploy.panel.JavaPanel;
import com.sun.deploy.util.StringUtils;
import java.lang.Object;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    private Container c;
    private JPanel t;
    private JPanel d;
    private int op1;
    private int op2;
    private JTextField lo;
    private int nump;
    public int getOp1() {
        return op1;
    }

    public int getNump() {
        return nump;
    }

    public void setNump(int nump) {
        this.nump = nump;
    }

    private JFrame yolo;
    private String nametitle;

    public String getNametitle() {
        return nametitle;
    }

    private String sy;

    public int getSortby() {
        return sortby;
    }

    private String crst;

    public String getCrst() {
        return crst;
    }

    public String getSy() {
        return sy;
    }

    private String cre;

    public String getCre() {
        return cre;
    }

    private String yrlim;

    public String getYrlim() {
        return yrlim;
    }

    private String[] aunames;

    public String[] getAunames() {
        return aunames;
    }

    private int sortby;

    public String getK() {
        return k;
    }

    private String k;
    private String[][] data1;
    private int twentycount;

    public void setData1(String[][] data1) {
        this.data1 = data1;
    }

    private String[][] data2;//={{"101","popopopopopop1"},{"op",";kllnmlmw"}};

    public void setData2(String[][] data2) {
        this.data2 = data2;
    }

    private String[][] data3;//={{"101kmnknk","opopnknk"},{"op knk","poopknknk"}};

    public void setData3(String[][] data3) {
        this.data3 = data3;
    }

    private String[] column={"S.NO.","AUTHORS","TITLE","PAGES","YEAR","VOLUME","JOURNAL/BOOKTITLE","URL"};

    DblpController useobject;

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public GUI(){
        //initComponents();
        useobject = new DblpController();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(0,0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DBLP Query Engine");
        setSize(1000,1000);
        Topsec tops = new Topsec();
        t = tops;
        add(t,BorderLayout.NORTH);
        Downsec downs = new Downsec();
        d = downs;
        add(d,BorderLayout.CENTER);
        d.setVisible(true);
        t.setVisible(true);
        setVisible(true);
    }

    class Topsec extends JPanel{
        private JLabel heading;
        public Topsec(){
            setPreferredSize(new Dimension(100,100));
            //setBorder(BorderFactory.createEtchedBorder());
            setLayout(new GridLayout(1,1,0,0));
            heading = new JLabel("DBLP QUERY ENGINE",SwingConstants.CENTER);
            Font font = new Font("times new roman", Font.BOLD, 40);
            heading.setFont(font);
            add(heading);
        }
    }

    class Downsec extends JPanel{
        private Leftsec lefts;
        private Rightsec rights;
        public Downsec() {
            setBorder(BorderFactory.createEtchedBorder());
            setLayout(new BorderLayout(5,5));
            lefts = new Leftsec();
            lefts.setVisible(true);

            rights = new Rightsec();
            //r = rights;
            //rights.setVisible(true);
            add(lefts,BorderLayout.WEST);
            add(rights,BorderLayout.CENTER);
        }
        public void setq1(){
            rights.setq1();
        }
        public void setq2(){
            rights.setq2();
        }
        public void setq3(){
            rights.setq3();
        }
        public void setblank(){ rights.setqempty();}
        public void setquery(int n)
        {
            lefts.setquery(n);
        }
        class Leftsec extends JPanel{
            private Left2 l2;
            private Left3 l3;
            private Left4 l4;
            private Left5 l5;
            private JComboBox jComboBox;
            public Leftsec(){
                setPreferredSize(new Dimension(350, 1000));
                aunames = new String[5];
                setBorder(BorderFactory.createTitledBorder(null, "Options: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
                jComboBox = new JComboBox();
                Font f = new Font("times new roman",Font.PLAIN,20);
                JPanel x1 = new JPanel();
                jComboBox.setFont(f);
                jComboBox.addItem("Queries");
                jComboBox.addItem("Query 1");
                jComboBox.addItem("Query 2");
                jComboBox.addItem("Query 3");
                jComboBox.setPrototypeDisplayValue("Search By");
                jComboBox.setVisible(true);
                x1.add(jComboBox);
                add(x1);
                x1.setVisible(true);
                jComboBox.addActionListener(
                        new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                JComboBox combo = (JComboBox)e.getSource();
                                String selected = (String)combo.getSelectedItem();
                                if(selected.equals("Queries")){
                                    l2.setVisible(false);
                                    l3.setVisible(false);
                                    l4.setVisible(false);

                                    op1=0;
                                }
                                else if(selected.equals("Query 1")){
                                    l2.setVisible(true);
                                    l3.setVisible(false);
                                    l4.setVisible(false);
                                    l2.b2.doClick();
                                    setblank();
                                    op1=1;
                                }
                                else if(selected.equals("Query 2")){
                                    l2.setVisible(false);
                                    l3.setVisible(true);
                                    l3.b2.doClick();
                                    l4.setVisible(false);
                                    setblank();
                                    op1=2;
                                }
                                else if(selected.equals("Query 3")){
                                    l2.setVisible(false);
                                    l3.setVisible(false);
                                    l4.setVisible(true);
                                    l4.b2.doClick();
                                    setblank();
                                    op1=3;
                                }
                                System.out.println("op1 "+ op1);
                            }
                        }
                );
                l2 = new Left2();
                add(l2);
                l2.setVisible(false);
                l3 = new Left3();
                add(l3);
                l3.setVisible(false);
                l4 = new Left4();
                add(l4);
                l4.setVisible(false);
                l5 = new Left5();
                add(l5);
                l5.setVisible(false);
            }
            public void setquery(int n){
                l5.setVisible(true);
                jComboBox.setSelectedItem("Queries");
                System.out.println("pop");
            }
            class Left2 extends  JPanel{
                JButton b2;
                public Left2() {
                    //setPreferredSize(new Dimension(300, 1000));
                    Font f = new Font("times new roman",Font.PLAIN,20);
                    setLayout(new GridLayout(8,1,2,2));
                    JPanel x2 = new JPanel();
                    JComboBox jComboBox1 = new JComboBox();
                    jComboBox1.setFont(f);
                    jComboBox1.addItem("Search By");
                    jComboBox1.addItem("Author Name");
                    jComboBox1.addItem("Title Tags");
                    jComboBox1.setPrototypeDisplayValue("Author Name");
                    //twentycount=0;
                    jComboBox1.addActionListener(
                            new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    JComboBox combo = (JComboBox)e.getSource();
                                    String selected = (String)combo.getSelectedItem();
                                    if(selected.equals("Search By")){
                                        op2=0;
                                    }
                                    else if(selected.equals("Author Name")){
                                        op2=1;
                                    }
                                    else if(selected.equals("Title Tags")){
                                        op2=2;
                                    }
                                    System.out.println("op2 "+ op2);
                                }
                            }
                    );
                    x2.add(jComboBox1);
                    add(x2);
                    JPanel tf1 = new JPanel();
                    JPanel tf2 = new JPanel();
                    JPanel tf3 = new JPanel();
                    JLabel l1 = new JLabel("Name/Title Tags :");
                    l1.setFont(f);
                    JTextField t1 = new JTextField("",10);
                    t1.setFont(f);
                    tf1.add(l1);
                    tf1.add(t1);
                    tf1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf1);
                    JLabel l2 = new JLabel("Since Year :");
                    l2.setFont(f);
                    JTextField t2 = new JTextField("yyyy",4);
                    t2.setFont(f);
                    t2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(t2.getText().equals("yyyy"))
                                t2.setText("");
                        }
                    });
                    tf2.add(l2);
                    tf2.add(t2);
                    add(tf2);
                    JLabel l3 = new JLabel("Custom Range :");
                    l3.setFont(f);
                    JLabel l31 = new JLabel(" - ");
                    l31.setFont(f);
                    JTextField t3 = new JTextField("yyyy",4);
                    t3.setFont(f);
                    t3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(t3.getText().equals("yyyy"))
                                t3.setText("");
                        }
                    });
                    JTextField t31 = new JTextField("yyyy",4);
                    t31.setFont(f);
                    t31.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(t31.getText().equals("yyyy"))
                                t31.setText("");
                        }
                    });
                    tf3.add(l3);
                    tf3.add(t3);
                    tf3.add(l31);
                    tf3.add(t31);
                    add(tf3);
                    JPanel tf4 = new JPanel();
                    JRadioButton rb1 = new JRadioButton("Sort by Year");
                    JRadioButton rb2 = new JRadioButton("Sort by Relevance");
                    sortby=0;
                    ButtonGroup group = new ButtonGroup();
                    group.add(rb1);
                    group.add(rb2);
                    group.clearSelection();
                    rb1.setFont(f);
                    rb2.setFont(f);
                    rb1.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if(sortby==1){
                                        group.clearSelection();
                                        sortby=0;
                                        System.out.println("sortby "+ sortby);
                                    }
                                    else
                                    {
                                        sortby=1;
                                        System.out.println("sortby "+ sortby);
                                    }
                                }
                            }
                    );
                    rb2.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if(sortby==2){
                                        group.clearSelection();
                                        sortby=0;
                                        System.out.println("sortby "+ sortby);
                                    }
                                    else
                                    {
                                        sortby=2;
                                        System.out.println("sortby "+ sortby);
                                    }
                                }
                            }
                    );
                    tf4.add(rb1);
                    tf4.add(rb2);
                    add(tf4);
                    JPanel tf5 = new JPanel();
                    JButton b1= new JButton("Search");
                    b2= new JButton("Reset");
                    b1.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    nametitle=t1.getText();
                                    sy=t2.getText();
                                    crst=t3.getText();
                                    cre=t31.getText();
                                    if(((jComboBox1.getSelectedItem().equals("Search By"))||(nametitle.equals(""))) ){
                                        JOptionPane.showMessageDialog (null, "Kindly complete the query.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t1.setText("");
                                        t2.setText("yyyy");
                                        t3.setText("yyyy");
                                        t31.setText("yyyy");
                                        jComboBox.setSelectedItem("Sort By");
                                        group.clearSelection();
                                        sortby=0;
                                        op2=0;
                                        jComboBox1.setSelectedItem("Search By");
                                    }
                                    else if(!(sy.equals("yyyy")||(isNumeric(sy)&&sy.length()==4)) || !(cre.equals("yyyy")||(isNumeric(cre)&&cre.length()==4)) || !(crst.equals("yyyy")||(isNumeric(crst)&&crst.length()==4))  ){
                                        JOptionPane.showMessageDialog (null, "Kindly enter the years in yyyy format.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t2.setText("yyyy");
                                        t3.setText("yyyy");
                                        t31.setText("yyyy");
                                    }
                                    else if((crst.equals("yyyy") && !cre.equals("yyyy")) || (!crst.equals("yyyy") && cre.equals("yyyy")) ){
                                        JOptionPane.showMessageDialog (null, "Kindly enter both, start and end years in yyyy format.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t3.setText("yyyy");
                                        t31.setText("yyyy");
                                    }
                                    else if((isNumeric(cre)&&cre.length()==4) && ((isNumeric(crst)&&crst.length()==4)) && (Integer.parseInt(cre)<Integer.parseInt(crst))  ){
                                        JOptionPane.showMessageDialog (null, "Start year can't be greater than end year.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t3.setText("yyyy");
                                        t31.setText("yyyy");
                                    }
                                    else
                                    {
                                        twentycount=0;
//                                        String[][] dd1={{"101", "Amit", "670000", "101", "Amit", "670000", "101", "Amit"},
//                                                {"101", "Amit", "670000", "101", "Amit", "67000000000000000000000000000", "101", "Amit"},
//                                                {"101", "Amit", "670000", "101", "Amit", "670000", "101", "Amit"},{"101", "Amit", "670000", "101", "Amit", "670000", "101", "Amit"}};
//                                        setData1(dd1);
                                        useobject.queryOne(nametitle,op2,sy,crst,cre,sortby);
                                        setq1();
                                        lo.setText(Integer.toString(nump));
                                        System.out.println("sy "+sy+ " nametite "+nametitle+" crst cre "+crst+cre);
                                    }

                                }
                            }
                    );
                    b2.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    t1.setText("");
                                    t2.setText("yyyy");
                                    t3.setText("yyyy");
                                    t31.setText("yyyy");
                                    //twentycount=0;
                                    jComboBox1.setSelectedItem("Search By");
                                    group.clearSelection();
                                    sortby=0;
                                    op2=0;
                                }
                            }
                    );
                    b1.setBackground(Color.GRAY);
                    b1.setFont(f);
                    tf5.add(b1);
                    b2.setBackground(Color.LIGHT_GRAY);
                    b2.setFont(f);
                    tf5.add(b2);
                    add(tf5);
                }
            }
            class Left5 extends JPanel{
                public Left5(){
                    setLayout(new GridLayout(6,1));
                    Font f = new Font("times new roman",Font.PLAIN,20);
                }
            }
            class Left3 extends JPanel{
                JButton b2;
                public Left3() {
                    setLayout(new GridLayout(3,1,2,2));
                    //twentycount=0;
                    Font f = new Font("times new roman",Font.PLAIN,20);
                    JPanel tf1 = new JPanel();
                    JLabel l1 = new JLabel("Value of <k> :");
                    l1.setFont(f);
                    JTextField t1 = new JTextField("0",5);
                    t1.setFont(f);
                    t1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(t1.getText().equals("0"))
                                t1.setText("");
                        }
                    });
                    tf1.add(l1);
                    tf1.add(t1);
                    tf1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf1);
                    JPanel tf5 = new JPanel();
                    JButton b1= new JButton("Search");
                    b2= new JButton("Reset");
                    b1.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    k=t1.getText();
                                    if(!(isNumeric(k))){
                                        JOptionPane.showMessageDialog (null, "Kindly enter a number <k>.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t1.setText("0");
                                    }
                                    else {
                                        twentycount=0;
                                        useobject.queryTwo(Integer.parseInt(k));
                                        setq2();
                                        lo.setText(Integer.toString(nump));
                                        System.out.println("k "+k);
                                    }
                                }
                            }
                    );
                    b2.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    t1.setText("0");
                                    //twentycount=0;
                                    //jComboBox.setSelectedItem("Sort By");
                                }
                            }
                    );
                    b1.setBackground(Color.GRAY);
                    b1.setFont(f);
                    tf5.add(b1);
                    b2.setBackground(Color.LIGHT_GRAY);
                    b2.setFont(f);
                    tf5.add(b2);
                    add(tf5);
                }
            }
            class Left4 extends JPanel {
                JButton b2;
                public Left4() {
                    //twentycount=0;
                    setLayout(new GridLayout(8, 1, 2, 2));
                    Font f = new Font("times new roman", Font.PLAIN, 20);
                    JPanel tf1 = new JPanel();
                    JLabel l1 = new JLabel("Year limit given :");
                    l1.setFont(f);
                    JTextField t1 = new JTextField("yyyy", 5);
                    t1.setFont(f);
                    t1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(t1.getText().equals("yyyy"))
                                t1.setText("");
                        }
                    });
                    tf1.add(l1);
                    tf1.add(t1);
                    tf1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf1);
                    JPanel tf2 = new JPanel();
                    JLabel l2 = new JLabel("Name 1 :");
                    l2.setFont(f);
                    JTextField t2 = new JTextField("", 10);
                    t2.setFont(f);
                    tf2.add(l2);
                    tf2.add(t2);
                    tf2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf2);
                    JPanel tf3 = new JPanel();
                    JLabel l3 = new JLabel("Name 2 :");
                    l3.setFont(f);
                    JTextField t3 = new JTextField("", 10);
                    t3.setFont(f);
                    tf3.add(l3);
                    tf3.add(t3);
                    tf3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf3);
                    JPanel tf4 = new JPanel();
                    JLabel l4 = new JLabel("Name 3 :");
                    l4.setFont(f);
                    JTextField t4 = new JTextField("", 10);
                    t4.setFont(f);
                    tf4.add(l4);
                    tf4.add(t4);
                    tf4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf4);
                    JPanel tf5 = new JPanel();
                    JLabel l5 = new JLabel("Name 4 :");
                    l5.setFont(f);
                    JTextField t5 = new JTextField("", 10);
                    t5.setFont(f);
                    tf5.add(l5);
                    tf5.add(t5);
                    tf5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf5);
                    JPanel tf6 = new JPanel();
                    JLabel l6 = new JLabel("Name 5 :");
                    l6.setFont(f);
                    JTextField t6 = new JTextField("", 10);
                    t6.setFont(f);
                    tf6.add(l6);
                    tf6.add(t6);
                    tf6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    add(tf6);
                    JPanel tf7 = new JPanel();
                    JButton b1 = new JButton("Search");
                    b2 = new JButton("Reset");
                    b1.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    yrlim = t1.getText();
                                    aunames[0]=t2.getText();
                                    aunames[1]=t3.getText();
                                    aunames[2]=t4.getText();
                                    aunames[3]=t5.getText();
                                    aunames[4]=t6.getText();
                                    if((t1.getText().equals("yyyy"))||(t2.getText().equals(""))||(t3.getText().equals(""))||(t4.getText().equals(""))||(t5.getText().equals(""))||(t6.getText().equals(""))){
                                        JOptionPane.showMessageDialog (null, "Incomplete Query!!", "Message", JOptionPane.ERROR_MESSAGE);
                                        t1.setText("yyyy");
                                        t2.setText("");
                                        t3.setText("");
                                        t4.setText("");
                                        t5.setText("");
                                        t6.setText("");
                                    }
                                    else if(!(isNumeric(yrlim) && yrlim.length()==4)){
                                        JOptionPane.showMessageDialog (null, "Kindly enter the year in yyyy format.", "Message", JOptionPane.ERROR_MESSAGE);
                                        t1.setText("yyyy");
                                    }
                                    else{
                                        twentycount=0;
                                        useobject.queryThree(aunames[0],aunames[1],aunames[2],aunames[3],aunames[4],yrlim);
                                        setq3();
                                        System.out.println(yrlim+aunames[0]+aunames[1]+aunames[2]+aunames[3]+aunames[4]);
                                    }
                                }
                            }
                    );
                    b2.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    t1.setText("yyyy");
                                    t2.setText("");
                                    t3.setText("");
                                    t4.setText("");
                                    //twentycount=0;
                                    t5.setText("");
                                    t6.setText("");
                                    //jComboBox.setSelectedItem("Sort By");
                                }
                            }
                    );
                    b1.setBackground(Color.GRAY);
                    b1.setFont(f);
                    tf7.add(b1);
                    b2.setBackground(Color.LIGHT_GRAY);
                    b2.setFont(f);
                    tf7.add(b2);
                    add(tf7);
                }
            }
        }
        class Rightsec extends JPanel{
            JPanel qpanel;
            JTable jt1;
            JTable jt2;
            JButton nextButton;
            DefaultTableModel tableModel;
            DefaultTableModel tableModel1;
            public Rightsec(){
                setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
                this.setLayout(new BorderLayout());
                tableModel = new DefaultTableModel(20,8);
                tableModel.setColumnIdentifiers(column);
                String column1[] = {"S.NO","NAMES OF REQUESTED AUTHORS"};
                tableModel1 = new DefaultTableModel(20,2);
                tableModel1.setColumnIdentifiers(column1);
//                qpanel = new JPanel();
//                this.add(qpanel,BorderLayout.CENTER);
                //qpanel.setLayout(new GridLayout(1,1));
//                JLabel l1 = new JLabel("r");
//                qpanel.add(l1);
            }
            public void setq1(){
                Font f = new Font("times new roman",Font.PLAIN,20);
//                setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
                this.removeAll();
                JPanel buttonPanel = new rd(1);
                this.add(buttonPanel,BorderLayout.SOUTH);
                JPanel q1panel = new r1();
                this.add(q1panel,BorderLayout.CENTER);
                //CardLayout cl = (CardLayout)(qpanel.getLayout());
                //cl.show(qpanel,"1");
                q1panel.setVisible(true);
                setquery(1);
                //qpanel.setVisible(true);

            }
            public void setq2(){
                Font f = new Font("times new roman",Font.PLAIN,20);
//                setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
                //this.setLayout(new BorderLayout());
                this.removeAll();
                JPanel buttonPanel = new rd(2);
                this.add(buttonPanel,BorderLayout.SOUTH);
//                qpanel = new JPanel(new CardLayout());
//                this.add(qpanel,BorderLayout.CENTER);
//                qpanel.setVisible(true);

                JPanel q2panel = new r2();
                this.add(q2panel,BorderLayout.CENTER);
                q2panel.setVisible(true);
                setquery(2);
                //qpanel.setVisible(true);
                //CardLayout cl = (CardLayout)(qpanel.getLayout());
                //cl.show(qpanel,"2");
            }
            public void setq3(){
                Font f = new Font("times new roman",Font.PLAIN,20);
//                setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
                //this.setLayout(new BorderLayout());
                this.removeAll();
//                JPanel buttonPanel = new rd();
//                this.add(buttonPanel,BorderLayout.SOUTH);
//                qpanel = new JPanel(new CardLayout());
//                this.add(qpanel,BorderLayout.CENTER);
//                qpanel.setVisible(true);
                JPanel q3panel = new r3();
                this.add(q3panel,BorderLayout.CENTER);
                q3panel.setVisible(true);
                setquery(3);
                //qpanel.setVisible(true);
                //CardLayout cl = (CardLayout)(qpanel.getLayout());
                //cl.show(qpanel,"3");
            }
            public void setqempty(){
                Font f = new Font("times new roman",Font.PLAIN,20);
//                setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));
//                qpanel = new JPanel(new CardLayout());
//                this.add(qpanel,BorderLayout.CENTER);
//                qpanel.setVisible(true);
                this.removeAll();
                JPanel q4panel = new rempty();
                this.add(q4panel);
                q4panel.setVisible(true);
                //qpanel.setVisible(true);
               // CardLayout cl = (CardLayout)(qpanel.getLayout());
                //cl.show(qpanel,"4");
            }
//            public void setnext(){
//                CardLayout cl = (CardLayout)(qpanel.getLayout());
//                cl.show(qpanel,"1");
//            }
            class r1 extends JPanel{
                public r1() {
                    this.setLayout(new BorderLayout());
                    jt1=new JTable();
                    //jt1.getTableHeader().setReorderingAllowed(false);
                    jt1.setModel(tableModel);
//                    jt1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//                        @Override
//                        public void valueChanged(ListSelectionEvent lse) {
//                            nextButton.setEnabled(true);
//                        }
//                    });
                    for(int i=0;i<tableModel.getRowCount();i++) {
                        for (int j = 0; j < tableModel.getColumnCount(); j++)
                            tableModel.setValueAt("", i, j);
                    }
                    try{

                        for(int i=0;i<data1.length;i++)
                        {

                            for(int j = 0; j < data1[i].length; j++)
                                jt1.setValueAt(data1[i][j], i, j);
                        }
                    }
                    catch(Exception e){
                        System.out.println("There was some problem. Please try another query.");

                    }
                    JScrollPane sp = new JScrollPane(jt1);
                    add(sp,BorderLayout.CENTER);
                }
            }
            class r2 extends JPanel{
                public r2() {
                    this.setLayout(new BorderLayout());

                    jt2=new JTable();
                    //jt2.getTableHeader().setReorderingAllowed(false);
                    jt2.setModel(tableModel1);
//                    jt2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//                        @Override
//                        public void valueChanged(ListSelectionEvent lse) {
//                            nextButton.setEnabled(true);
//                        }
//                    });
                    for(int i=0;i<data2.length;i++)
                    {
                        for(int j = 0; j < data2[i].length; j++)
                            jt2.setValueAt(data2[i][j], i, j);
                    }
                    JScrollPane sp = new JScrollPane(jt2);
                    add(sp,BorderLayout.CENTER);
                }
            }
            class r3 extends JPanel{
                public r3() {
                    this.setLayout(new BorderLayout());
                    String column2[] = {"NAME OF THE AUTHOR","LINEAR REGRESSION M. ","LAGRANGE INTERPOLATION POLYNOMIAL M.","AVERAGE PREDICTION M.","ACTUAL NO. OF PUBLICATIONS"};
                    JTable jt = new JTable(data3, column2);
                    JTableHeader header = jt.getTableHeader();
                    //jt.setBounds(30, 40, 200, 300);
                    JScrollPane sp = new JScrollPane(jt);
                    add(sp,BorderLayout.CENTER);
                }
            }
            class rempty extends JPanel{
                public rempty(){

                }
            }
            class rd extends JPanel{

                public rd(int n) {
                    Font f = new Font("times new roman",Font.PLAIN,20);
                    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    setLayout(new BorderLayout());
                    nextButton = new JButton("Next");
                    nextButton.setBackground(Color.GRAY);
                    nextButton.setFont(f);

                    JPanel p = new JPanel();
                    add(p, BorderLayout.WEST);
                    JLabel fo = new JLabel("No. of Outputs: ");
                    p.add(fo);
                    lo = new JTextField("", 10);
                    p.add(lo);
                    p.setVisible(true);

                    nextButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            twentycount++;
                            //request data

                            if(n==1 ){
                                //req data1
                                useobject.send201(twentycount);
                                System.out.println("20: "+twentycount);
                                for(int i=0;i<tableModel.getRowCount();i++) {
                                    for (int j = 0; j < tableModel.getColumnCount(); j++)
                                        tableModel.setValueAt("", i, j);
                                }
                                for(int i=0;i<data1.length;i++)
                                {
                                    for(int j = 0; j < data1[i].length; j++)
                                        tableModel.setValueAt(data1[i][j], i, j);
                                }
//                                jt1.revalidate();
                                //jt1.getModel()

                                //System.out.println("ooo");
                                if(data1.length<20){
                                    nextButton.setEnabled(false);
                                }
                                //nextButton.setBackground(Color.black);
                            }
                            else if(n==2 ){
                                //rq data2
                                useobject.send202(twentycount);
                                System.out.println("20: "+twentycount);
                                for(int i=0;i<tableModel1.getRowCount();i++) {
                                for (int j = 0; j < tableModel1.getColumnCount(); j++)
                                    tableModel1.setValueAt("", i, j);
                                }
                                for(int i=0;i<data2.length;i++)
                                {
                                for(int j = 0; j < data2[i].length; j++)
                                    tableModel1.setValueAt(data2[i][j], i, j);
                                }
                                if(data2.length<20){
                                   nextButton.setEnabled(false);
                                }
                            }

                        }
                    });
                    add(nextButton,BorderLayout.EAST);
                }
            }

        }
    }
    public void showMessage(){
        yolo= new JFrame();
        //JOptionPane.showOptionDialog(null, "The data is being parsed. This will take about a minute.","Loading...", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
        yolo.setTitle("Loading..");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        yolo.setLocation(dim.width/2-yolo.getSize().width/2, dim.height/2-yolo.getSize().height/2);
        yolo.setSize(new Dimension(500,90));
        yolo.add(new JLabel("The data is being processed. This will take about a minute.",SwingConstants.CENTER));
        yolo.setVisible(true);
    }
    public void hideMessage(){
        yolo.setVisible(false);
    }


}

