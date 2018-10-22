package application.domain.transformers;

@FunctionalInterface
public interface Transformer<T, V> {

	T transform(V value);
}
