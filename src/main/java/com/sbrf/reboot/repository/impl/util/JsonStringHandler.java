package com.sbrf.reboot.repository.impl.util;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс-утилита для работы с файлом.
 */
public class JsonStringHandler {
    private static final char NEXT_LINE_SYMBOL = '\n';
    /**
     * Получить счета клиента по id.
     * <p>
     * Алгоритм:
     * 1. Считываем строку
     * 2. Оставляем в этой строке только цифры
     * 3. Если цифры есть:
     * 3.1. Инициализируем ими поле clientId
     * 3.2. Считываем следующую строку, извлекаем оттуда цифры, инициализируем ими поле number
     * иначе к пункту 1.
     *
     * @param filePath      - путь до файла.
     * @param inputClientId - айди клиента.
     * @return множество счетов клиента.
     * @throws IOException на случай инициализации {@link BufferedReader} и считывания строки
     */
    public static Set<Long> getClientAccountsSet(final String filePath, final long inputClientId) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(filePath));

        final Set<Long> accounts = new HashSet<>();
        String line;
        while ((line = reader.readLine()) != null) {
            final String digitsInLine = line.replaceAll("[^0-9]+", "");

            if (digitsInLine.length() > 0) {
                final long clientId = Long.parseLong(digitsInLine);
                line = reader.readLine();
                final long number = Long.parseLong(line.replaceAll("[^0-9]+", ""));

                if (clientId == inputClientId) {
                    accounts.add(number);
                }
            }
        }
        return accounts;
    }

    /**
     * Замена счёта у клиента.
     *
     * Алгоритм:
     *  1. Считываем строку и помещаем её в буфер строк
     *  2. Если в строке обнаруживается число, то значит это строка с полем clientId
     *      2.1. Считываем следующую строку, достаем оттуда поле number
     *      2.2. Сравниваем clientId и number со входными параметрами
     *          и заменяем значение number в строке, если совпадают
     *      3.3. Записываем строку из 2.1. в буфер
     *  иначе к пункту 1
     *
     * @param filePath - путь до файла.
     * @param clientId - айди клиента.
     * @param numberOld - счёт, который нужно заменить.
     * @param numberNew - новый счёт.
     * @return true если такой счёт был, false - если нет
     * @throws IOException на случай ошибки создания {@link BufferedReader}
     */
    public static boolean updateClientAccount(
            final String filePath,
            final long clientId,
            final long numberOld,
            final long numberNew
    ) throws IOException {
        boolean replaceResult = false;
        final BufferedReader reader = new BufferedReader(new FileReader(filePath));
        final StringBuilder stringBuffer = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            final String digitsInLine = line.replaceAll("[^0-9]+", "");
            stringBuffer.append(line);
            stringBuffer.append(NEXT_LINE_SYMBOL);

            if (digitsInLine.length() > 0) {
                final long clientIdField = Long.parseLong(digitsInLine);
                String lineTwo = reader.readLine();
                final long numberField = Long.parseLong(lineTwo.replaceAll("[^0-9]+", ""));

                if (clientIdField == clientId && numberField == numberOld) {
                    lineTwo = lineTwo.replace(
                            String.valueOf(numberOld),
                            String.valueOf(numberNew)
                    );
                    replaceResult = true;
                }
                stringBuffer.append(lineTwo);
                stringBuffer.append(NEXT_LINE_SYMBOL);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(stringBuffer.toString().getBytes());
        fileOut.close();
        return replaceResult;
    }
}
