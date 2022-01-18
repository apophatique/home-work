package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        final List<String> students = new ArrayList<>();
        students.add("Иванов");
        students.add("Петров");
        students.add("Сидоров");
        students.add("Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        final Set<Integer> moneyBox = new HashSet<>();
        IntStream.range(0, 10).forEach(moneyBox::add);


        //...

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        final Map<Integer, Book> bookshelf = new HashMap<Integer, Book>() {{
            put(0, new Book());
            put(1, new Book());
            put(2, new Book());
        }};

        //...

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задание на 5+.
     *
     * задача аналогичная первой, но с дополнительным условием:
     *  - имеющийся рейтинг в живом режиме транслируется на сайт университета,
     *    так что рейтинг всегда нужно держать в порядке первенства по очкам.
     */
    @Test
    public void addStudentToRatingAdvanced() {
        class Student {
            final String name;

            public Student(final String name) {
                this.name = name;
            }

            public int getRating() {
                return 1;
            }
        }

        final Queue<Student> students = new PriorityQueue<Student>(
                Comparator.comparing(Student::getRating).reversed()
        ) {{
            add(new Student("Иванов"));
            add(new Student("Сидоров"));
            add(new Student("Петров"));
            add(new Student("Козлов"));
        }};

        assertEquals(4, students.size());
    }
}
