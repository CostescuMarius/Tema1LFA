package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AppWind extends JFrame {
    JPanel backgnd;
    JLabel l_string, l_pattern, l_validation;
    JTextField txt_string, txt_pattern;
    JButton checkBtn;

    public AppWind() {
        set_window();
    }

    private void set_window() {
        this.setBounds(400, 200, 500, 170);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setTitle("Tema1");

        set_backgnd();

        add_labels();
        add_text_boxes();

        add_check_button();

        this.setVisible(true);
    }
    private void set_backgnd() {
        backgnd = new JPanel();
        backgnd.setBackground(Color.darkGray);
        backgnd.setLayout(null);
        this.add(backgnd);
    }

    private void add_labels() {
        l_string = new JLabel();
        l_pattern = new JLabel();
        l_validation = new JLabel();

        l_string.setText("String: ");
        l_string.setForeground(Color.white);
        l_string.setBounds(20, 20, 50, 20);

        l_pattern.setText("Pattern: ");
        l_pattern.setForeground(Color.white);
        l_pattern.setBounds(20, 50, 50, 20);

        l_validation.setText("");
        l_validation.setBounds(30, 90, 300, 20);

        backgnd.add(l_string);
        backgnd.add(l_pattern);
        backgnd.add(l_validation);
    }

    private void add_text_boxes() {
        txt_string = new JTextField();
        txt_pattern = new JTextField();

        txt_string.setBounds(80, 20, 200, 20);
        txt_pattern.setBounds(80, 50, 200, 20);

        backgnd.add(txt_string);
        backgnd.add(txt_pattern);
    }

    private void add_check_button() {
        checkBtn = new JButton("Check");

        checkBtn.setForeground(Color.white);
        checkBtn.setBackground(Color.darkGray);
        checkBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        checkBtn.setBounds(350,25, 70, 20);

        add_check_action();

        backgnd.add(checkBtn);
    }

    private void add_check_action() {
        checkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text_pattern = txt_pattern.getText();
                String text_string = txt_string.getText();

                if (text_pattern.equals("") || text_string.equals("")) {
                    l_validation.setForeground(new Color(255, 204, 203));
                    if (text_string.equals("")) {
                        l_validation.setText("Fill string!");
                    }
                    else {
                        l_validation.setText("Fill pattern!");
                    }
                }
                else {
                    try {
                        Pattern pattern = Pattern.compile(text_pattern);
                        Matcher matcher = pattern.matcher(text_string);
                        if (matcher.find()) {
                            l_validation.setForeground(Color.green);
                            l_validation.setText("Match found.");
                        } else {
                            l_validation.setForeground(new Color(255, 204, 203));
                            l_validation.setText("No match found.");
                        }
                    } catch (PatternSyntaxException exception) {
                        l_validation.setForeground(new Color(255, 204, 203));
                        l_validation.setText("Invalid pattern: " + exception.getMessage());
                    }
                }
            }
        });
    }
}
