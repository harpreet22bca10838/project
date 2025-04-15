import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class StudentGradeGUI extends JFrame {
    JTextField idField, nameField, subjectField, marksField;
    DefaultTableModel model;

    public StudentGradeGUI() {
        setTitle("Student Grade System");
        setSize(700, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        idField = new JTextField();
        nameField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Subject:"));
        formPanel.add(subjectField);
        formPanel.add(new JLabel("Marks:"));
        formPanel.add(marksField);

        JButton addBtn = new JButton("Add Record");
        formPanel.add(addBtn);

        add(formPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Subject", "Marks", "Grade"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {
            try {
                int marks = Integer.parseInt(marksField.getText());
                String grade = calculateGrade(marks);

                String[] data = {
                    idField.getText(),
                    nameField.getText(),
                    subjectField.getText(),
                    marksField.getText(),
                    grade
                };

                StudentGradeHandler.addStudent(data);
                model.addRow(data);
                JOptionPane.showMessageDialog(this, "Student record added!");
                clearFields();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid input or error saving data.");
            }
        });

        try {
            StudentGradeHandler.initializeFile();
            List<String[]> students = StudentGradeHandler.getAllStudents();
            for (String[] stu : students) {
                model.addRow(stu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private String calculateGrade(int marks) {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 70) return "C";
        else if (marks >= 60) return "D";
        else return "F";
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        subjectField.setText("");
        marksField.setText("");
    }

    public static void main(String[] args) {
        new StudentGradeGUI();
    }
}