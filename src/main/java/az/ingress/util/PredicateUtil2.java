package az.ingress.util;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PredicateUtil2 {

    private final List<Predicate> predicates = new ArrayList<>();

    public static PredicateUtil2 builder() {
        return new PredicateUtil2();
    }

    public <T> PredicateUtil2 add(T object, Function<T, Predicate> function) {
        predicates.add(function.apply(object));
        return this;
    }

    public <T> PredicateUtil2 addNullSafety(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Object[] build() {
        return predicates.toArray(new Object[0]);
    }



}
