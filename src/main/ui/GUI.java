package ui;

import model.Candidates;
import model.Event;
import model.EventLog;
import model.Provinces;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Graphical user interface of voting app
public class GUI extends JFrame implements ActionListener {

    private static final String JSON_STORE_C = "./data/candidatesGUI.json";
    private static final String JSON_STORE_P = "./data/provincesGUI.json";

    private JsonWriter writerP;
    private JsonReader readerP;
    private JsonWriter writerC;
    private JsonReader readerC;

    private JPanel mainPanel;
    private JPanel provincePanel;
    private JPanel candidatePanel;
    private JPanel votePanel;

    private JPanel addProvincePanel;
    private JPanel addCandidatePanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;

    private JLabel voteImage;
    private JLabel provincesLabel;
    private JLabel candidatesLabel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;

    private Provinces provinces;
    private Candidates candidates;

    private ImageIcon imageIcon;

    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> fields;
    private ArrayList<String> winners;
    private ArrayList<Integer> votes;

    // EFFECTS: runs the GUI Voting App
    public GUI() {
        super("Voting App");

        initMainPanel();
        initProvincePanel();
        initCandidatePanel();
        initVotePanel();

        initAddProvincePanel();
        initAddCandidatePanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: Initializes mainPanel
    private void initMainPanel() {
        provinces = new Provinces();
        candidates = new Candidates();
        writerP = new JsonWriter(JSON_STORE_P);
        readerP = new JsonReader(JSON_STORE_P);
        writerC = new JsonWriter(JSON_STORE_C);
        readerC = new JsonReader(JSON_STORE_C);
        button9 = new JButton("Last page");
        button9.setActionCommand("Last page1");
        button9.addActionListener(this);
        mainPanel = new JPanel();
        assignVoteImage();
        button1 = new JButton("Add/remove province");
        button1.setActionCommand("Add/remove province");
        button1.addActionListener(this);

        mainPanel.setBorder(new EmptyBorder(50, 13, 13, 13));
        setPreferredSize(new Dimension(800, 500));
        mainPanel.setLayout(new FlowLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addFeaturesToMain();
        add(mainPanel);
    }


    // MODIFIES: this
    // EFFECTS: initializes provincePanel
    private void initProvincePanel() {
        provincePanel = new JPanel(new GridLayout(0,1));
        provincePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        provincesLabel = new JLabel(provincesToString());

        labels = new ArrayList<>();
        fields = new ArrayList<>();

        button4 = new JButton("Add province");
        button4.setActionCommand("Add province");
        button4.addActionListener(this);

        button5 = new JButton("Remove province");
        button5.setActionCommand("Remove province");
        button5.addActionListener(this);

        button6 = new JButton("Last page");
        button6.setActionCommand("Last page1");
        button6.addActionListener(this);

        provincePanel.add(provincesLabel);
        provincePanel.add(button4);
        provincePanel.add(button5);
        provincePanel.add(button6);
    }

    // MODIFIES: this
    // EFFECTS: initializes candidatePanel
    private void initCandidatePanel() {

        candidatePanel = new JPanel(new GridLayout(0, 1));
        candidatePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        candidatesLabel = new JLabel(candidatesToString());

        button7 = new JButton("Add candidate");
        button7.setActionCommand("Add candidate");
        button7.addActionListener(this);

        button8 = new JButton("Remove candidate");
        button8.setActionCommand("Remove candidate");
        button8.addActionListener(this);

        button9 = new JButton("Last page");
        button9.setActionCommand("Last page2");
        button9.addActionListener(this);

        candidatePanel.add(candidatesLabel);
        candidatePanel.add(button7);
        candidatePanel.add(button8);
        candidatePanel.add(button9);
    }

    // MODIFIES: this
    // EFFECTS: initialize provincePanel
    private void initAddProvincePanel() {

        addProvincePanel = new JPanel();

        label1 = new JLabel("Province name");
        field1 = new JTextField(5);

        label2 = new JLabel("Province population");
        field2 = new JTextField(5);

        button10 = new JButton("Generate province");
        button10.setActionCommand("Generate province");
        button10.addActionListener(this);

        button11 = new JButton("Last page");
        button11.setActionCommand("Last page3");
        button11.addActionListener(this);

        addProvincePanel.add(label1);
        addProvincePanel.add(field1);
        addProvincePanel.add(label2);
        addProvincePanel.add(field2);
        addProvincePanel.add(button10);
        addProvincePanel.add(button11);

        addProvincePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
    }

    // MODIFIES: this
    // EFFECTS: initialize candidatePanel
    private void initAddCandidatePanel() {

        addCandidatePanel = new JPanel();

        label3 = new JLabel("Candidate name");
        field3 = new JTextField(5);

        label4 = new JLabel("Candidate party");
        field4 = new JTextField(5);

        button12 = new JButton("Generate candidate");
        button12.setActionCommand("Generate candidate");
        button12.addActionListener(this);

        button13 = new JButton("Last page");
        button13.setActionCommand("Last page4");
        button13.addActionListener(this);

        addCandidatePanel.add(label3);
        addCandidatePanel.add(field3);
        addCandidatePanel.add(label4);
        addCandidatePanel.add(field4);
        addCandidatePanel.add(button12);
        addCandidatePanel.add(button13);

        addCandidatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
    }

    // MODIFIES: this
    // EFFECTS: initialize votePanel
    private void initVotePanel() {
        fields = new ArrayList<>();
        votePanel = new JPanel(new GridLayout(0, 2));
        label5 = new JLabel("Ballots remaining: " + provinces.getTotalPopulation());
        label6 = new JLabel();
        votePanel.add(label5);
        votePanel.add(label6);
        winners = new ArrayList<>();
        for (int i = 0; i < candidates.size(); i++) {
            JLabel name = new JLabel("Votes for " + candidates.get(i).getName());
            JTextField vote = new JTextField(5);
            winners.add(candidates.get(i).getName() + " (" + candidates.get(i).getParty() + ") ");
            labels.add(name);
            fields.add(vote);
            votePanel.add(name);
            votePanel.add(vote);
        }
        addFeaturesToVotePanel();
        votePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
    }

    // MODIFIES: this
    // EFFECTS: Adds features (labels and buttons) to votePanel
    private void addFeaturesToVotePanel() {
        label7 = new JLabel("Winner: ");
        label8 = new JLabel();

        button16 = new JButton("Update ballots");
        button16.setActionCommand("Update ballots");
        button16.addActionListener(this);

        button17 = new JButton("Vote");
        button17.setActionCommand("Vote");
        button17.addActionListener(this);

        button18 = new JButton("Last page");
        button18.setActionCommand("Last page5");
        button18.addActionListener(this);

        votePanel.add(label7);
        votePanel.add(label8);
        votePanel.add(button16);
        votePanel.add(button17);
        votePanel.add(button18);
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: when button is clicked, perform its corresponding (assigned) action
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add/remove province")) {
            showProvincePanel();
        } else if (e.getActionCommand().equals("Add/remove candidate")) {
            showCandidatePanel();
        } else if (e.getActionCommand().equals("Last page1")) {
            showMainPanel();
        } else if (e.getActionCommand().equals("Last page2")) {
            showMainPanel();
        } else if (e.getActionCommand().equals("Add province")) {
            showAddProvincePanel();
        } else if (e.getActionCommand().equals("Generate province")) {
            doGenerateProvince();
        } else if (e.getActionCommand().equals("Last page3")) {
            showProvincePanel();
        } else if (e.getActionCommand().equals("Remove province")) {
            doRemoveProvince();
        } else if (e.getActionCommand().equals("Add candidate")) {
            showAddCandidatePanel();
        } else if (e.getActionCommand().equals("Generate candidate")) {
            doGenerateCandidate();
        } else if (e.getActionCommand().equals("Remove candidate")) {
            doRemoveCandidate();
        } else if (e.getActionCommand().equals("Last page4")) {
            showCandidatePanel();
        } else if (e.getActionCommand().equals("vote")) {
            initVotePanel();
            showVotePanel();
        } else if (e.getActionCommand().equals("save")) {
            doSave();
        } else if (e.getActionCommand().equals("load")) {
            doLoad();
        } else if (e.getActionCommand().equals("Update ballots")) {
            doUpdateBallots();
        } else if (e.getActionCommand().equals("Vote")) {
            doVote();
        } else if (e.getActionCommand().equals("Last page5")) {
            showMainPanel();
        } else if (e.getActionCommand().equals("exit")) {
            exitFrame();

        }
    }

    // EFFECTS: prints out evenLogs and exit from the frame
    private static void exitFrame() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString() + "\n");
        }
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: does vote and announces the winner
    private void doVote() {
        int maxAt = 0;
        int tempVote = 0;
        votes = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            tempVote = getValidText(i);
            votes.add(tempVote);
        }

        for (int i = 0; i < votes.size(); i++) {
            maxAt = votes.get(i) > votes.get(maxAt) ? i : maxAt;
        }

        label8.setText(winners.get(maxAt));
    }

    // MODIFIES: this
    // EFFECTS: shows current remaining ballots
    private void doUpdateBallots() {
        int totalVote = provinces.getTotalPopulation();
        int voted = 0;
        for (int i = 0; i < fields.size(); i++) {
            int tempVote = getValidText(i);
            voted = voted + tempVote;
        }
        label5.setText("Ballots remaining: " + Integer.toString(totalVote - voted));
    }

    // EFFECTS: helper method for doUpdateBallots and doVote.
    //          If JTextField has empty string returns 0, returns proper string of ith fields elsewhere.
    private int getValidText(int i) {
        if (fields.get(i).getText().equals("")) {
            return 0;
        } else {
            return Integer.parseInt(fields.get(i).getText());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads provinces and candidates from separate files
    private void doLoad() {
        try {
            provinces = readerP.read();
            candidates = readerC.readCandidate();

            System.out.println("Loaded");

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_C);
        }

        provincesLabel.setText(provincesToString());
        candidatesLabel.setText(candidatesToString());
    }

    // EFFECTS: saves provinces and candidates into separate file
    private void doSave() {
        try {
            writerP.open();
            writerP.write(provinces);
            writerP.close();

            writerC.open();
            writerC.writeCandidate(candidates);
            writerC.close();

            System.out.println("Saved");

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_C);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes last province added
    private void doRemoveProvince() {
        int size = provinces.size();
        if (size != 0) {
            provinces.removeProvince(size);
            provincesLabel.setText(provincesToString());
            provincePanel.setVisible(false);
            provincePanel.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes last candidate added
    private void doRemoveCandidate() {
        int size = candidates.size();
        if (size != 0) {
            candidates.removeCandidate(size);
            candidatesLabel.setText(candidatesToString());
            candidatePanel.setVisible(false);
            candidatePanel.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: replace provincesLabel with given name and population
    private void doGenerateProvince() {
        provinces.addProvince(field1.getText(), Integer.parseInt(field2.getText()));
        provincesLabel.setText(provincesToString());
        showProvincePanel();
    }

    // MODIFIES: this
    // EFFECTS: replace candidateLabel with given name and party
    private void doGenerateCandidate() {
        candidates.addCandidate(field3.getText(), field4.getText());
        candidatesLabel.setText(candidatesToString());
        showCandidatePanel();
    }

    // EFFECTS: returns provinces converted into string
    private String provincesToString() {
        Provinces p = provinces;
        String returnString = "Provinces added:";
        for (int i = 0; i < p.size(); i++) {
            returnString = returnString + " " + p.get(i).getName() + " (" + p.get(i).getPopulation() + ")";
        }
        return returnString;
    }

    // EFFECTS: returns candidates converted into string
    private String candidatesToString() {
        Candidates c = candidates;
        String returnString = "Candidates added:";
        for (int i = 0; i < c.size(); i++) {
            returnString = returnString + " " + c.get(i).getName() + " (" + c.get(i).getParty() + ")";
        }
        return returnString;
    }

    // MODIFIES: this
    // EFFECTS: adds buttons and images to mainPanel
    private void addFeaturesToMain() {
        button2 = new JButton("Add/remove candidate");
        button2.setActionCommand("Add/remove candidate");
        button2.addActionListener(this);
        button3 = new JButton("vote");
        button3.setActionCommand("vote");
        button3.addActionListener(this);
        button14 = new JButton("save");
        button14.setActionCommand("save");
        button14.addActionListener(this);
        button15 = new JButton("load");
        button15.setActionCommand("load");
        button15.addActionListener(this);
        button19 = new JButton("exit");
        button19.setActionCommand("exit");
        button19.addActionListener(this);
        mainPanel.add(voteImage);
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(button14);
        mainPanel.add(button15);
        mainPanel.add(button19);
    }

    // MODIFIES: this
    // EFFECTS: assigns image of voting to voteImage
    private void assignVoteImage() {
        String f = "./data/voteImage.jpeg";
        Image i = new ImageIcon(f).getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(i);
        voteImage = new JLabel();
        voteImage.setIcon(imageIcon);
    }

    // EFFECTS: make all panels invisible except addCandidatePanel
    private void showAddCandidatePanel() {
        add(addCandidatePanel);
        addCandidatePanel.setVisible(true);
        mainPanel.setVisible(false);
        provincePanel.setVisible(false);
        candidatePanel.setVisible(false);
        candidatePanel.setVisible(false);
        addProvincePanel.setVisible(false);
    }

    // EFFECTS: make all panels invisible except addProvincePanel
    private void showAddProvincePanel() {
        add(addProvincePanel);
        addProvincePanel.setVisible(true);
        mainPanel.setVisible(false);
        provincePanel.setVisible(false);
        candidatePanel.setVisible(false);
        votePanel.setVisible(false);
        addCandidatePanel.setVisible(false);
    }

    // EFFECTS: make all panels invisible except mainPanel
    private void showMainPanel() {
        mainPanel.setVisible(true);
        provincePanel.setVisible(false);
        candidatePanel.setVisible(false);
        votePanel.setVisible(false);
        addProvincePanel.setVisible(false);
        addCandidatePanel.setVisible(false);
    }

    // EFFECTS: make all panels invisible except candidatePanel
    private void showCandidatePanel() {
        add(candidatePanel);
        candidatePanel.setVisible(true);
        mainPanel.setVisible(false);
        provincePanel.setVisible(false);
        votePanel.setVisible(false);
        addProvincePanel.setVisible(false);
        addCandidatePanel.setVisible(false);
    }

    // EFFECTS: make all panels invisible except provincePanel
    private void showProvincePanel() {
        add(provincePanel);
        provincePanel.setVisible(true);
        mainPanel.setVisible(false);
        candidatePanel.setVisible(false);
        votePanel.setVisible(false);
        addProvincePanel.setVisible(false);
        addCandidatePanel.setVisible(false);
    }

    // EFFECTS: make all panels invisible except votePanel
    private void showVotePanel() {
        add(votePanel);
        votePanel.setVisible(false);
        votePanel.setVisible(true);
        mainPanel.setVisible(false);
        provincePanel.setVisible(false);
        candidatePanel.setVisible(false);
        addProvincePanel.setVisible(false);
        addCandidatePanel.setVisible(false);
    }
}
