package repository;

public interface UserInterface<T> extends BaseRepository<T>{

    T login(String username,String password);
}
