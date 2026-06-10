import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    JLabel totalStudents;
    JLabel totalCourses;
    JLabel topStudent;

    public DashboardFrame() {

        setTitle("EduTrack Pro Dashboard");
        setSize(700, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 700, 60);
        header.setBackground(new Color(44, 62, 80));

        JLabel title = new JLabel("EduTrack Pro Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(title);

        JPanel panel1 = createCard("Total Students");
        panel1.setBounds(50, 100, 180, 100);

        JPanel panel2 = createCard("Total Courses");
        panel2.setBounds(260, 100, 180, 100);

        JPanel panel3 = createCard("Top Student");
        panel3.setBounds(470, 100, 180, 100);

        totalStudents = new JLabel();
        totalCourses = new JLabel();
        topStudent = new JLabel();

        updateData();

        panel1.add(totalStudents);
        panel2.add(totalCourses);
        panel3.add(topStudent);

        JButton manageBtn = new JButton("Manage Students & Courses");
        manageBtn.setBounds(200, 250, 250, 40);

        manageBtn.addActionListener(e -> {
            new StudentCourseFrame();
        });

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(200, 310, 250, 40);

        refreshBtn.addActionListener(e -> updateData());

        JButton saveBtn = new JButton("Save Data");
        saveBtn.setBounds(200, 370, 250, 40);

        saveBtn.addActionListener(e -> {
            DataManager.saveStudents();
            DataManager.saveCourses();
            JOptionPane.showMessageDialog(this, "Data Saved");
        });

        add(header);
        add(panel1);
        add(panel2);
        add(panel3);
        add(manageBtn);
        add(refreshBtn);
        add(saveBtn);

        setVisible(true);
    }

    JPanel createCard(String text) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(label);

        return panel;
    }

    void updateData() {

        totalStudents.setText(String.valueOf(DataManager.students.size()));

        totalCourses.setText(String.valueOf(DataManager.courses.size()));

        if (DataManager.getTopStudent() != null) {
            topStudent.setText(
                    DataManager.getTopStudent().getName()
            );
        } else {
            topStudent.setText("N/A");
        }
    }
}