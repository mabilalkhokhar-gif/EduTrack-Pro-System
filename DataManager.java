import java.io.*;
import java.util.*;

public class DataManager {

    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();

    public static HashMap<Integer, Student> studentMap = new HashMap<>();

    public static TreeMap<Integer, Student> sortedStudents = new TreeMap<>();

    public static PriorityQueue<Student> topStudents =
            new PriorityQueue<>((a, b) ->
                    Double.compare(b.getGpa(), a.getGpa()));

    public static Stack<Student> deletedStudents = new Stack<>();

    public static Queue<String> notifications = new LinkedList<>();

    public static void addStudent(Student student) {

        students.add(student);

        studentMap.put(student.getId(), student);

        sortedStudents.put(student.getId(), student);

        topStudents.offer(student);

        notifications.offer("Student Added : " + student.getName());
    }

    public static void updateStudent(int id,
                                     String name,
                                     int age,
                                     String course,
                                     double marks,
                                     double gpa) {

        Student s = studentMap.get(id);

        if (s != null) {

            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            s.setMarks(marks);
            s.setGpa(gpa);

            notifications.offer("Student Updated : " + name);
        }
    }

    public static void deleteStudent(int id) {

        Student s = studentMap.get(id);

        if (s != null) {

            students.remove(s);

            studentMap.remove(id);

            sortedStudents.remove(id);

            deletedStudents.push(s);

            notifications.offer("Student Deleted : " + s.getName());
        }
    }

    public static Student searchStudent(int id) {

        return studentMap.get(id);
    }

    public static void undoDelete() {

        if (!deletedStudents.isEmpty()) {

            Student s = deletedStudents.pop();

            addStudent(s);

            notifications.offer("Undo Delete : " + s.getName());
        }
    }

    public static Student getTopStudent() {

        return topStudents.peek();
    }

    public static void addCourse(Course course) {

        courses.add(course);

        notifications.offer("Course Added : "
                + course.getCourseName());
    }

    public static void deleteCourse(int id) {

        courses.removeIf(course ->
                course.getCourseId() == id);

        notifications.offer("Course Deleted");
    }

    public static void saveStudents() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("students.txt"));

            for (Student s : students) {

                writer.write(s.toString());

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void loadStudents() {

        try {

            File file = new File("students.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Student s = new Student(

                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5])

                );

                addStudent(s);
            }

            reader.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void saveCourses() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("courses.txt"));

            for (Course c : courses) {

                writer.write(c.toString());

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void loadCourses() {

        try {

            File file = new File("courses.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                courses.add(

                        new Course(
                                Integer.parseInt(data[0]),
                                data[1]
                        )

                );
            }

            reader.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void mergeSortByGPA() {

        students.sort((a, b) ->
                Double.compare(b.getGpa(), a.getGpa()));
    }

    public static Student binarySearchStudent(int id) {

        ArrayList<Student> temp =
                new ArrayList<>(students);

        temp.sort(Comparator.comparingInt(Student::getId));

        int left = 0;
        int right = temp.size() - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (temp.get(mid).getId() == id) {
                return temp.get(mid);
            }

            if (temp.get(mid).getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}