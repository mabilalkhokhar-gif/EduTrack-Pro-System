import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentCourseFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    JTextField idField, nameField, ageField, courseField, marksField, gpaField;

    JTextField courseIdField, courseNameField;

    public StudentCourseFrame() {

        setTitle("EduTrack Pro - Management");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        JPanel studentPanel = studentPanel();
        JPanel coursePanel = coursePanel();

        tabs.add("Students", studentPanel);
        tabs.add("Courses", coursePanel);

        tabs.setBounds(10, 10, 860, 540);

        add(tabs);

        setVisible(true);

        refreshTable();
    }

    // ---------------- STUDENT PANEL ----------------

    JPanel studentPanel() {

        JPanel panel = new JPanel(null);

        JLabel l1 = new JLabel("ID");
        JLabel l2 = new JLabel("Name");
        JLabel l3 = new JLabel("Age");
        JLabel l4 = new JLabel("Course");
        JLabel l5 = new JLabel("Marks");
        JLabel l6 = new JLabel("GPA");

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        courseField = new JTextField();
        marksField = new JTextField();
        gpaField = new JTextField();

        l1.setBounds(20, 20, 100, 25);
        idField.setBounds(100, 20, 120, 25);

        l2.setBounds(250, 20, 100, 25);
        nameField.setBounds(330, 20, 120, 25);

        l3.setBounds(480, 20, 100, 25);
        ageField.setBounds(560, 20, 120, 25);

        l4.setBounds(20, 60, 100, 25);
        courseField.setBounds(100, 60, 120, 25);

        l5.setBounds(250, 60, 100, 25);
        marksField.setBounds(330, 60, 120, 25);

        l6.setBounds(480, 60, 100, 25);
        gpaField.setBounds(560, 60, 120, 25);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton undoBtn = new JButton("Undo Delete");

        addBtn.setBounds(20, 100, 120, 30);
        updateBtn.setBounds(150, 100, 120, 30);
        deleteBtn.setBounds(280, 100, 120, 30);
        searchBtn.setBounds(410, 100, 120, 30);
        undoBtn.setBounds(540, 100, 150, 30);

        model = new DefaultTableModel();

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 150, 800, 300);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Course");
        model.addColumn("Marks");
        model.addColumn("GPA");

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());
        undoBtn.addActionListener(e -> {
            DataManager.undoDelete();
            refreshTable();
        });

        panel.add(l1);
        panel.add(idField);
        panel.add(l2);
        panel.add(nameField);
        panel.add(l3);
        panel.add(ageField);
        panel.add(l4);
        panel.add(courseField);
        panel.add(l5);
        panel.add(marksField);
        panel.add(l6);
        panel.add(gpaField);

        panel.add(addBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(searchBtn);
        panel.add(undoBtn);

        panel.add(sp);

        return panel;
    }

    // ---------------- COURSE PANEL ----------------

    JPanel coursePanel() {

        JPanel panel = new JPanel(null);

        JLabel l1 = new JLabel("Course ID");
        JLabel l2 = new JLabel("Course Name");

        courseIdField = new JTextField();
        courseNameField = new JTextField();

        l1.setBounds(20, 20, 100, 25);
        courseIdField.setBounds(120, 20, 150, 25);

        l2.setBounds(300, 20, 120, 25);
        courseNameField.setBounds(420, 20, 150, 25);

        JButton addBtn = new JButton("Add Course");
        JButton deleteBtn = new JButton("Delete Course");

        addBtn.setBounds(120, 70, 150, 30);
        deleteBtn.setBounds(300, 70, 150, 30);

        addBtn.addActionListener(e -> {
            Course c = new Course(
                    Integer.parseInt(courseIdField.getText()),
                    courseNameField.getText()
            );

            DataManager.addCourse(c);

            JOptionPane.showMessageDialog(this, "Course Added");
        });

        deleteBtn.addActionListener(e -> {
            DataManager.deleteCourse(
                    Integer.parseInt(courseIdField.getText())
            );

            JOptionPane.showMessageDialog(this, "Course Deleted");
        });

        panel.add(l1);
        panel.add(courseIdField);
        panel.add(l2);
        panel.add(courseNameField);
        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    // ---------------- ACTIONS ----------------

    void addStudent() {

        Student s = new Student(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                Integer.parseInt(ageField.getText()),
                courseField.getText(),
                Double.parseDouble(marksField.getText()),
                Double.parseDouble(gpaField.getText())
        );

        DataManager.addStudent(s);

        refreshTable();
    }

    void updateStudent() {

        DataManager.updateStudent(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                Integer.parseInt(ageField.getText()),
                courseField.getText(),
                Double.parseDouble(marksField.getText()),
                Double.parseDouble(gpaField.getText())
        );

        refreshTable();
    }

    void deleteStudent() {

        int id = Integer.parseInt(idField.getText());

        DataManager.deleteStudent(id);

        refreshTable();
    }

    void searchStudent() {

        int id = Integer.parseInt(idField.getText());

        Student s = DataManager.searchStudent(id);

        if (s != null) {

            JOptionPane.showMessageDialog(this,
                    "Found:\n" + s.toString());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Not Found");
        }
    }

    void refreshTable() {

        model.setRowCount(0);

        for (Student s : DataManager.students) {

            model.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getAge(),
                    s.getCourse(),
                    s.getMarks(),
                    s.getGpa()
            });
        }
    }
}