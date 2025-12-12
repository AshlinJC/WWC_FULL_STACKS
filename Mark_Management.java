import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId()
    { return id; }
    public int getMarks()
    { return marks; }
    public String getrole()
    { return "Undergrad"; }

    @Override
    public String toString()
    {
        return id + " - " + name + " (" + marks + ") " + getrole();
    }
}

class GraduateStudent extends Student
{
    private String area;

    public GraduateStudent(String id, String name, int marks, String area)
    {
        super(id, name, marks);
        this.area = area;
    }

    public String getArea()
    { return area; }

    @Override
    public String getrole()
    {
        return "Grad (" + area + ")";
    }
}

class HonorStudent extends Student
{
    private String area;
    private int bonus;

    public HonorStudent(String id, String name, int marks, String area, int bonus)
    {
        super(id, name, marks + bonus);
        this.area = area;
        this.bonus = bonus;
    }

    public String getArea()
    { return area; }

    @Override
    public String getrole()
    {
        return "Honor (" + area + ", bonus=" + bonus + ")";
    }
}

class Repository<T extends Student>
{
    Map<String, T> data = new HashMap<>();

    public void save(String id, T obj)
    { data.put(id, obj); }
    public T find(String id)
    { return data.get(id); }
    public void delete(String id)
    { data.remove(id); }

    public T getTopperByArea(String area)
    {
        T topper = null;

        for (T s : data.values())
        {
            String a = null;

            if (s instanceof GraduateStudent)
                a = ((GraduateStudent) s).getArea();
            else if (s instanceof HonorStudent)
                a = ((HonorStudent) s).getArea();

            if (a != null && a.equalsIgnoreCase(area)) {
                if (topper == null || s.getMarks() > topper.getMarks())
                    topper = s;
            }
        }
        return topper;
    }
}

public class Mark_Management
{
    public static void main(String[] args)
    {

        Repository<Student> repo = new Repository<>();

        repo.save("S1", new Student("S1", "Ashlin", 75));
        repo.save("S2", new Student("S2", "Jincy", 85));

        repo.save("G1", new GraduateStudent("G1", "Mercy", 90, "AI"));
        repo.save("G2", new GraduateStudent("G2", "Johny", 88, "ML"));

        repo.save("H1", new HonorStudent("H1", "Minnu", 80, "AI", 10));
        repo.save("H2", new HonorStudent("H2", "Annu", 82, "ML", 15));

        System.out.println("ALL STUDENTS:");
        for (Student s : repo.data.values())
            System.out.println(s);

        System.out.println("\nTopper of AI:");
        System.out.println(repo.getTopperByArea("AI"));

        System.out.println("\nTopper of ML:");
        System.out.println(repo.getTopperByArea("ML"));
    }
}
