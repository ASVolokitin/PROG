package org.example.functionalClasses;

public class SetValues {

    /**
     * Класс, определяющий формат поля ввода.
     */

    private final int key;
    private final String valueType;
    private final boolean isRequired;
    private final String comment;

    /**
     * Метод, задающий формат поля ввода.
     * @param key
     * @param valueType
     * @param isRequired
     * @param comment
     */

    public SetValues(int key, String valueType, boolean isRequired, String comment) {
        this.key = key;
        this.valueType = valueType;
        this.isRequired = isRequired;
        this.comment = comment;
    }

    /**
     * Метод, возвращающий номер поля ввода.
     * @return
     */

    public int getKey() {
        return key;
    }

    /**
     * Метод, возвращающий тип поля ввода.
     * @return
     */

    public String getValueType() {
        return valueType;
    }

    /**
     * Метод, возвращающий информацию о необходимости этого поля.
     * @return
     */

    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Метод, возвращающий описание поля ввода.
     * @return
     */

    public String getComment() {
        return comment;
    }

}
