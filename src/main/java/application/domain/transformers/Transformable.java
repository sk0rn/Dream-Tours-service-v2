package application.domain.transformers;

public interface Transformable<V> {

	default <T> T transformTo(Transformer<T, V> transformer) {
		return transformer.transform((V)this);
	}

}
