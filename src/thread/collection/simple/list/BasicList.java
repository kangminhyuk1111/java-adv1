package thread.collection.simple.list;

import java.util.Arrays;

import static thread.control.ThreadUtils.sleep;

public class BasicList implements SimpleList{

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    public BasicList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    // add - 메서드 한개이니까 원자적 연산 아닌가
    // 아님. elements에 넣는거 한번 size++ 한번
    // 멀티스레드에서의 문제 발생
    @Override
    public void add(Object o) {
        elements[size] = o;
        sleep(100);
        size++;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) + " size = " + size + " capacity = " + elements.length;
    }
}
