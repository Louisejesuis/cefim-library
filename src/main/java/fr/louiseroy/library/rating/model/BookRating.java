package fr.louiseroy.library.rating.model;

import jakarta.persistence.Tuple;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class BookRating implements Comparable<BookRating> {
    private BigDecimal rate;

    public BookRating(Tuple tuple) {
        rate = tuple.get(0, BigDecimal.class);
    }


    @Override
    public int compareTo(BookRating o) {
        BigDecimal thisRate = this.getRate();
        BigDecimal otherRate = o.getRate();

        if (thisRate == null && otherRate == null) {
            return 0;
        } else if (thisRate == null) {
            return -1;
        } else if (otherRate == null) {
            return 1;
        } else {
            return Boolean.compare(thisRate.doubleValue() > otherRate.doubleValue(), true);
        }
    }

}
