package TutorialCode;

public class Tutorial9 {
    //-----------------------------------------------------------------------------------------
// Class to test the Scheduling (the main driver class)
//-----------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Tutorial9 t = new Tutorial9();
        t.runTask();
    }

    public void runTask() {
        Schedule autumnSchedule = new Schedule();
        autumnSchedule.display();
    }

    //-----------------------------------------------------------------------------------------
// Class for a Schedule
//-----------------------------------------------------------------------------------------
    public class Schedule {
        private Course[] courseList;

        public Schedule() {
            courseList = new Course[5];

            courseList[0] = new MajorCourse("Intro to Computer Science",
                    "CSC101",
                    "An introduction to the basics of good program design.",
                    "Computer Science", "Computer Science", "none");

            courseList[1] = new MajorCourse("Discreet Mathematics",
                    "CSC215",
                    "Instruction on the mathematical modelling of real-world"
                            + " problems using discreet math concepts.",
                    "Computer Science", "Computer Science", "CSC101");

            courseList[2] = new GeneralEducation("Basic Art", "ART101",
                    "Instruction on basic concepts in art. Lecture and studio "
                            + " time required.", "Art", "Fine Arts");

            courseList[3] = new GeneralEducation("Spanish for Beginners 1",
                    "SPA101",
                    "Introduction to the Spanish language. Course focus on "
                            + " concepts of the language and speaking exercises.",
                    "Modern Languages", "Language");

            courseList[4] = new Elective("Golf", "REC210",
                    "Introduction to the game of golf, including rules of play and "
                            + "basic techniques. Course consists of instructional class time "
                            + "as well as weekly round of play.", "Physical Education");
        }

        public void display() {
            for(Course courseList1 : courseList) { // for each loop
                System.out.println(courseList1);
                System.out.println("------------------------------------------");
            }
        }
    }

    //-----------------------------------------------------------------------------------------
// Class for a Course
//-----------------------------------------------------------------------------------------
    public abstract class Course {
        // instance variables
        protected String title;
        protected String number;
        protected String description;
        protected String department;

        public Course(String title, String number, String description, String department) {
            this.title = title;
            this.number = number;
            this.description = description;
            this.department = department;
        }

        public String toString() {
            String result = "Course title: " + title + "\n";
            result += "Course Number: " + number + "\n";
            result += "Course Description: " + description + "\n";
            result += "Department: " + department + "\n";
            return result;
        }
        // Course c = new Elective(….);
        // System.out.println(c);
    }

    //-----------------------------------------------------------------------------------------
// Class for a General Education Course
//-----------------------------------------------------------------------------------------
    public class GeneralEducation extends Course {
        // instance variables
        protected String requirement;

        public GeneralEducation(String title, String number, String description, String department, String requirementFilled) {
            super(title, number, description, department);
            requirement = requirementFilled;
        }

        public String toString() {
            String result = super.toString();
            result += "\nGeneral Education Requirement Filled: " + requirement;
            return result;
        }
    }

    //-----------------------------------------------------------------------------------------
// Class for a Major Course
//-----------------------------------------------------------------------------------------
    public class MajorCourse extends Course {
        // instance variables
        protected String major;
        protected String requisite;

        public MajorCourse(String title, String number, String description,
                           String department, String major, String requisite) {
            super(title, number, description, department);
            this.major = major;
            this.requisite = requisite;
        }

        public String toString() {
            String result = super.toString();
            result += "\nCourse Major: " + major + "\n";
            result += "Pre/Co-Requisite(s): " + requisite;
            return result;
        }
    }

    //-----------------------------------------------------------------------------------------
// Class for an Elective Course
//-----------------------------------------------------------------------------------------
    public class Elective extends Course {
        public Elective(String title, String number, String description, String department) {
            super(title, number, description, department);
        }

        public String toString() {
            return "Elective course:\n" + super.toString();
        }
    }


}
