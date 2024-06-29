package org.example.functionalClasses;

import org.example.enums.MpaaRating;

import java.util.HashMap;

public class MpaaComparator {

    /**
     * Класс, устанавливающий соответствие значение рейтинга MPAA числам.
     */

    public HashMap <MpaaRating, Integer> rating = new HashMap<>();

    /**
     * Конструктор словаря сопоставления.
     */

    public MpaaComparator() {
        rating.put(MpaaRating.G, 0);
        rating.put(MpaaRating.PG, 1);
        rating.put(MpaaRating.PG_13, 2);
        rating.put(MpaaRating.R, 3);
        rating.put(MpaaRating.NC_17, 4);
    }

    /**
     * Метод, возращающий числовое значение по значению рейтинга MPAA.
     * @param x
     * @return
     */

    public Integer getRating(MpaaRating x) {
        return rating.get(x);
    }

}
