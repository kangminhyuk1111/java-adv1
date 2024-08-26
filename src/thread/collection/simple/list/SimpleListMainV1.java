package thread.collection.simple.list;

public class SimpleListMainV1 {

    public static void main(String[] args) {
        SimpleList basicList = new BasicList();

        basicList.add("A");
        basicList.add("B");

        String string = basicList.toString();
        System.out.println(string);
    }
}
