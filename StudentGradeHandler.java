import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeHandler {
    private static final String FILE_NAME = "students.csv";

    public static void initializeFile() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write("ID,Name,Subject,Marks,Grade\n");
            writer.close();
        }
    }

    public static void addStudent(String[] data) throws IOException {
        FileWriter writer = new FileWriter(FILE_NAME, true);
        writer.write(String.join(",", data) + "\n");
        writer.close();
    }

    public static List<String[]> getAllStudents() throws IOException {
        List<String[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line = reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            list.add(parts);
        }
        reader.close();
        return list;
    }
}