package repository;

import java.util.List;

public interface BaseRepository<T> {

    Integer save(T t);

    void update(T t);

    List<T> findAll();

    void delete(int id);

    T findById(int id);
}
