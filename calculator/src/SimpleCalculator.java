import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, equButton;
    JButton decButton, clrButton, delButton, modButton, negButton;
    JPanel panel;

    double num1=0, num2=0, result=0;
    char operator = ' ';

    SimpleCalculator() {
        setTitle("Calculator");
        setSize(350,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(350,60));
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5,4,10,10));
        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.CENTER);

        // operator buttons (no helper)
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton = new JButton("C");
        delButton = new JButton("Del");
        modButton = new JButton("%");
        negButton = new JButton("±");

        JButton[] ops = {addButton, subButton, mulButton, divButton, decButton, equButton, clrButton, delButton, modButton, negButton};
        Color[] colors = {new Color(0,200,0), new Color(0,200,0), new Color(0,200,0), new Color(0,200,0), Color.WHITE,
                          new Color(0,200,0), Color.RED, Color.GRAY, new Color(0,200,0), new Color(0,200,0)};
        for(int i=0;i<ops.length;i++){
            ops[i].setFont(new Font("Arial",Font.BOLD,20));
            ops[i].setBackground(Color.DARK_GRAY);
            ops[i].setForeground(colors[i]);
            ops[i].setFocusPainted(false);
            ops[i].setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
            ops[i].addActionListener(this);
        }

        for(int i=0;i<10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setBackground(Color.DARK_GRAY);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
            numberButtons[i].addActionListener(this);
        }

        // layout
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(divButton);
        panel.add(mulButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(subButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(addButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(equButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(negButton);
        panel.add(modButton);

        setVisible(true);
    }

    private String formatResult(double value){
        if(value == (int)value){
            return String.valueOf((int)value);
        } else {
            return String.valueOf(value);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<10;i++){
            if(e.getSource()==numberButtons[i]){
                textField.setText(textField.getText()+i);
                return;
            }
        }
        if(e.getSource()==decButton){
            textField.setText(textField.getText()+".");
            return;
        }

        if(e.getSource()==addButton || e.getSource()==subButton || e.getSource()==mulButton || e.getSource()==divButton || e.getSource()==modButton){
            String current = textField.getText();
            if(operator==' '){
                num1 = Double.parseDouble(current);
                if(e.getSource()==addButton) operator = '+';
                if(e.getSource()==subButton) operator = '-';
                if(e.getSource()==mulButton) operator = '*';
                if(e.getSource()==divButton) operator = '/';
                if(e.getSource()==modButton) operator = '%';
                textField.setText(current+" "+operator+" ");
            }
            return;
        }

        if(e.getSource()==equButton){
            String current = textField.getText();
            String[] parts = current.split(" ");
            if(parts.length<3) return;
            num1 = Double.parseDouble(parts[0]);
            operator = parts[1].charAt(0);
            num2 = Double.parseDouble(parts[2]);

            switch(operator){
                case '+': result = num1+num2; break;
                case '-': result = num1-num2; break;
                case '*': result = num1*num2; break;
                case '/': result = num1/num2; break;
                case '%': result = num1%num2; break;
            }
            textField.setText(formatResult(result));
            operator = ' ';
            return;
        }

        if(e.getSource()==clrButton){
            textField.setText("");
            operator = ' ';
            return;
        }

        if(e.getSource()==delButton){
            String str = textField.getText();
            if(str.length()>0){
                textField.setText(str.substring(0,str.length()-1));
            }
            return;
        }

        if(e.getSource()==negButton){
            String str = textField.getText();
            if(str.length()>0 && operator==' '){
                double temp = Double.parseDouble(str);
                temp = -temp;
                textField.setText(formatResult(temp));
            }
        }
    }

    public static void main(String[] args){
        new SimpleCalculator();
    }
}
