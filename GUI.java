import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private Container c;
    private JPanel t;
    private JPanel d;
    public GUI(){
        setLayout(new BorderLayout(0,0));
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
        private JPanel l;
        private JPanel r;
        public Downsec() {
            setBorder(BorderFactory.createEtchedBorder());
            setLayout(new BorderLayout(5,5));
            Leftsec lefts = new Leftsec();
            l = lefts;
            l.setVisible(true);
            Rightsec rights = new Rightsec();
            r = rights;
            r.setVisible(true);
            add(l,BorderLayout.WEST);
            add(r,BorderLayout.CENTER);
        }
    }
    class Leftsec extends JPanel{
        private Left2 l2;
        private Left3 l3;
        private Left4 l4;
        private int op1;
        private int op2;
        private String nametitle;
        private String sy;
        private String crst;
        private String cre;
        private String yrlim;
        private String[] aunames;
        private int sortby;
        private String k;
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
                                op1=1;
                            }
                            else if(selected.equals("Query 2")){
                                l2.setVisible(false);
                                l3.setVisible(true);
                                l4.setVisible(false);
                                op1=2;
                            }
                            else if(selected.equals("Query 3")){
                                l2.setVisible(false);
                                l3.setVisible(false);
                                l4.setVisible(true);
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
        }
        class Left2 extends  JPanel{
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
                tf2.add(l2);
                tf2.add(t2);
                add(tf2);
                JLabel l3 = new JLabel("Custom Range :");
                l3.setFont(f);
                JLabel l31 = new JLabel(" - ");
                l31.setFont(f);
                JTextField t3 = new JTextField("yyyy",4);
                t3.setFont(f);
                JTextField t31 = new JTextField("yyyy",4);
                t31.setFont(f);
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
                                sortby=1;
                                System.out.println("sortby "+ sortby);
                            }
                        }
                );
                rb2.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                sortby=2;
                                System.out.println("sortby "+ sortby);
                            }
                        }
                );
                tf4.add(rb1);
                tf4.add(rb2);
                add(tf4);
                JPanel tf5 = new JPanel();
                JButton b1= new JButton("Search");
                JButton b2= new JButton("Reset");
                b1.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                nametitle=t1.getText();
                                sy=t2.getText();
                                crst=t3.getText();
                                cre=t31.getText();
                                if((jComboBox1.getSelectedItem().equals("Search By"))||(nametitle.equals(""))){
                                    JOptionPane.showMessageDialog (null, "Incomplete Query!!", "Message", JOptionPane.ERROR_MESSAGE);
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
                                else
                                    System.out.println("sy "+sy+ " nametite "+nametitle+" crst cre "+crst+cre);
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
                                jComboBox.setSelectedItem("Sort By");
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
        class Left3 extends JPanel{
            public Left3() {
                setLayout(new GridLayout(3,1,2,2));
                Font f = new Font("times new roman",Font.PLAIN,20);
                JPanel tf1 = new JPanel();
                JLabel l1 = new JLabel("Value of <k> :");
                l1.setFont(f);
                JTextField t1 = new JTextField("0",5);
                t1.setFont(f);
                tf1.add(l1);
                tf1.add(t1);
                tf1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf1);
                JPanel tf5 = new JPanel();
                JButton b1= new JButton("Search");
                JButton b2= new JButton("Reset");
                b1.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                k=t1.getText();
                                System.out.println("k "+k);
                            }
                        }
                );
                b2.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                t1.setText("0");
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
            public Left4() {
                setLayout(new GridLayout(8, 1, 2, 2));
                Font f = new Font("times new roman", Font.PLAIN, 20);
                JPanel tf1 = new JPanel();
                JLabel l1 = new JLabel("Year limit given :");
                l1.setFont(f);
                JTextField t1 = new JTextField("yyyy", 5);
                t1.setFont(f);
                tf1.add(l1);
                tf1.add(t1);
                tf1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf1);
                JPanel tf2 = new JPanel();
                JLabel l2 = new JLabel("Name 1 :");
                l2.setFont(f);
                JTextField t2 = new JTextField("", 5);
                t2.setFont(f);
                tf2.add(l2);
                tf2.add(t2);
                tf2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf2);
                JPanel tf3 = new JPanel();
                JLabel l3 = new JLabel("Name 2 :");
                l3.setFont(f);
                JTextField t3 = new JTextField("", 5);
                t3.setFont(f);
                tf3.add(l3);
                tf3.add(t3);
                tf3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf3);
                JPanel tf4 = new JPanel();
                JLabel l4 = new JLabel("Name 3 :");
                l4.setFont(f);
                JTextField t4 = new JTextField("", 5);
                t4.setFont(f);
                tf4.add(l4);
                tf4.add(t4);
                tf4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf4);
                JPanel tf5 = new JPanel();
                JLabel l5 = new JLabel("Name 4 :");
                l5.setFont(f);
                JTextField t5 = new JTextField("", 5);
                t5.setFont(f);
                tf5.add(l5);
                tf5.add(t5);
                tf5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf5);
                JPanel tf6 = new JPanel();
                JLabel l6 = new JLabel("Name 5 :");
                l6.setFont(f);
                JTextField t6 = new JTextField("", 5);
                t6.setFont(f);
                tf6.add(l6);
                tf6.add(t6);
                tf6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(tf6);
                JPanel tf7 = new JPanel();
                JButton b1 = new JButton("Search");
                JButton b2 = new JButton("Reset");
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
                                else
                                    System.out.println(yrlim+aunames[0]+aunames[1]+aunames[2]+aunames[3]+aunames[4]);
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
        public Rightsec(){
            setBorder(BorderFactory.createTitledBorder(null, "Results: ", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.ITALIC,20), Color.black));

        }
    }
    public static void main(String[] Args){
        new GUI();
    }
}

