package StudentManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Management {
    private final Scanner scan;
    private final List<Student> studentList;

    private final String folderRoot = "Student";
    private final String fileData = "student.dat";
    private final String fileDataLog = "studentDataLog.txt"; // to use check if before exit program, data miss when write to file

    private final StringBuilder tmp; // for keep input user enter from keyboard

    public Management() {
        File folderRoot = new File(this.folderRoot);
        if (!folderRoot.exists())
            folderRoot.mkdirs();
        studentList = new ArrayList<Student>();
        this.scan = new Scanner(System.in);
        this.tmp = new StringBuilder();
    }

    public void beforeSetupMenu() {
        File stuFile = new File(this.folderRoot.concat("/".concat(fileData)));
        if (!stuFile.exists())
            return;
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(stuFile));
            while (reader.available() != 0) { // check read whether last file or not
                Student stu = (Student) reader.readObject();
                this.studentList.add(stu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void menu() {
        boolean run = true;
        beforeSetupMenu();

        while (run) {
            System.out.println("--------------Student Management-------------");
            System.out.println("1. Add new student");
            System.out.println("2. Display all students");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Find students who has score > 5");
            System.out.println("6. Find students who has score <= 5");
            System.out.println("0. Exit");
            System.out.print("You choose: ");
            do {
                this.tmp.append(this.scan.next());
                if (!this.tmp.toString().matches("[0-6]")) {
                    System.out.println("Your choice you choose currently unavailable, please exactly enter in option above!");
                    System.out.print("You choose: ");
                    this.tmp.setLength(0);
                }
            } while (this.tmp.length() == 0);

            switch (this.tmp.toString()) {
                case "0":
                    beforeExitProgram();
                    System.out.println("Exited successfully!");
                    run = false;
                    break;
                case "1":
                    addStudent();
                    break;
                case "2":
                    System.out.println("-----------List students--------------");
                    displayStudentInformation(this.studentList);
                    break;
                case "3":
                    System.out.println("-----------Update student--------------");
                    updateStudent();
                    break;
                case "4":
                    System.out.println("-----------Delete student--------------");
                    deleteStudent();
                    break;
                case "5":
                    System.out.println("-----------List students that hava score > 5 --------------");
                    displayStudentInformation(this.studentList.stream().filter(stu -> stu.getScore() > 5).collect(Collectors.toList()));
                    break;
                case "6":
                    System.out.println("-----------List students that hava score <= 5 --------------");
                    displayStudentInformation(this.studentList.stream().filter(stu -> stu.getScore() <= 5).collect(Collectors.toList()));
                    break;
                default:
                    break;
            }
            this.tmp.setLength(0); // set default keeper
        }
    }

    public void beforeExitProgram() {
        File stuFile = new File(this.folderRoot + "/" + this.fileData);
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream(stuFile));
            for (int i = 0; i < this.studentList.size(); ++i) {
                writer.writeObject(this.studentList.get(i));
                writer.flush(); // flush cache
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public Student enterInformation(Student stu, String type) {
        if (!type.equalsIgnoreCase("update")) {
            System.out.print("Enter student id: ");
            Integer stuId = null;
            do {
                this.tmp.append(this.scan.next());
                if (!this.tmp.toString().matches("[0-9]*") || this.tmp.length() == 0) {
                    System.out.println("Student id invalid, please try again!");
                    this.tmp.setLength(0);
                } else {
                    stuId = Integer.valueOf(this.tmp.toString());
                    if (searchStudentById(stuId) != null) {
                        System.out.print("Student with current id has existed, please enter another id!");
                        stuId = null;
                        this.tmp.setLength(0);
                    }
                }
            } while (this.tmp.length() == 0);
            stu.setId(stuId);
            this.tmp.setLength(0); // set default for reuse at other
        }

        System.out.print("Enter student name: ");
        do {
            this.tmp.append(this.scan.nextLine());
            if (this.tmp.length() == 0) {
                System.out.print("Student name cannot leave blank, please enter it!");
            }
        } while (this.tmp.length() == 0);
        stu.setName(this.tmp.toString());
        this.tmp.setLength(0); // set default for reuse at other

        System.out.print("Enter student score( 1-10): ");
        Float score = null;
        do {
            this.tmp.append(this.scan.nextLine());
            if (!this.tmp.toString().matches("[0-9]*") || this.tmp.length() == 0) {
                System.out.println("Student id invalid, please try again!");
                this.tmp.setLength(0);
            } else {
                score = Float.valueOf(this.tmp.toString());
                if (score < 1 || score > 10) {
                    System.out.println("Student score outbound range, please try again!");
                    this.tmp.setLength(0);
                }
            }
        } while (this.tmp.length() == 0);
        stu.setScore(score);
        this.tmp.setLength(0); // set default for reuse at other
        return stu;
    }

    public Student searchStudentById(Integer stuId) {
        // use stream to search student by id
        return this.studentList.stream().filter(stu -> stu.getId().equals(stuId)).collect(Collectors.toList()).stream().findFirst().orElse(null);
    }

    public void displayStudentInformation(List<Student> stus) {
        stus.forEach(System.out::println);
    }

    public void addStudent() {
        Student stu = new Student();
        stu = enterInformation(stu, "save");
        if (stu != null) {
            this.studentList.add(stu);
            System.out.println("Add new student succcessfully!");
        } else
            System.out.println("Add new student failed, please try later or contact with admin!");
    }

    public void updateStudent() {
        Student stu = null;
        System.out.print("Enter student id: ");
        do {
            this.tmp.append(scan.nextLine());
            if (!this.tmp.toString().matches("[0-9]*") || this.tmp.length() == 0) {
                System.out.print("Student id invalid, please try again!");
                this.tmp.setLength(0);
            } else {
                stu = searchStudentById(Integer.valueOf(this.tmp.toString()));
                if (stu != null) {
                    Student newStuInfo = new Student();
                    newStuInfo = enterInformation(newStuInfo, "update");
                    newStuInfo.setId(stu.getId());

                    for (int i = 0; i < this.studentList.size(); ++i) {
                        if (this.studentList.get(i).getId().equals(stu.getId())) {
                            this.studentList.set(i, newStuInfo);
                            break;
                        }
                    }
                    System.out.println("Updated successfully!");
                    this.tmp.append("1412");
                } else {
                    System.out.println("Student with id=".concat(this.tmp.toString().concat(" not found, do you want continue? (y/n)")));
                    if (scan.next().equalsIgnoreCase("y")) {
                        System.out.print("Enter student id: ");
                        this.tmp.setLength(0); // set default keeper}
                    }
                }
            }

        } while (this.tmp.length() == 0);
        this.tmp.setLength(0);
    }

    public void deleteStudent() {
        System.out.println("Enter student id: ");
        do {
            this.tmp.append(this.scan.next());
            if (!this.tmp.toString().matches("[0-9]*") || this.tmp.length() == 0) {
                System.out.println("Student id invalid, please try again!");
                this.tmp.setLength(0);
            } else {
                Student stu = searchStudentById(Integer.valueOf(this.tmp.toString()));
                if (stu != null) {
                    this.studentList.remove(stu);
                    System.out.print("Delete successfully!");
                } else {
                    System.out.println("Student with id=".concat(this.tmp.toString().concat(" not found, do you want do continue?(y/n) ")));
                    if (scan.next().equalsIgnoreCase("y")) {
                        System.out.print("Enter student id: ");
                        this.tmp.setLength(0); // set default keeper}
                    }
                }
            }
        } while (this.tmp.length() == 0);
        this.tmp.setLength(0); // set default keeper
    }

    public static void main(String[] args) {
//        new Management().menu();
    }
}
