package application.domain.transformers;

public interface Converter<T, V> {

	T convert(V value);
}
