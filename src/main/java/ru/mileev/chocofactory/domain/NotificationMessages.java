package ru.mileev.chocofactory.domain;

public enum NotificationMessages {
    CHOCOLATE_PREPARED("Шоколад подготовлен"),
    CREAM_WHIPPED("Сливки взбиты"),
    FILLER_EXPLORED("Начинка исследована"),
    NUTS_CLEANED("Орехи очищены"),
    BATCH_PACKED("Партия упакована");

    private String msg;

    NotificationMessages(String msg) {
        this.msg = msg;
    }

    public String value() {
        return msg;
    }

}
