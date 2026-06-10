public class Main {

    public static void main(String[] args) {

        DataManager.loadStudents();
        DataManager.loadCourses();

        new LoginFrame();
    }
}
