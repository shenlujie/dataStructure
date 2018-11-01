package 动态数组;

public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>(15);
        for (int i = 0; i < 15; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.addLast(16);
        System.out.println(array);

        array.removeFirst();
        array.removeFirst();
        System.out.println(array);


//        Array<Student> studentArray = new Array<>();
//        studentArray.addLast(new Student("slj","man"));
//        studentArray.addLast(new Student("dl","woman"));
//        studentArray.addLast(new Student("ck","man"));
//        studentArray.addLast(new Student("dy","woman"));
//        studentArray.addLast(new Student("zgw","man"));
//        studentArray.addLast(new Student("ghw","woman"));
//        System.out.println(studentArray);
    }
}
