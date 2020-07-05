package fr.maxlego08.ztournament.zcore.utils.interfaces;

@FunctionalInterface
public interface StringConsumer<T> {

	String accept(T t);
	
}
