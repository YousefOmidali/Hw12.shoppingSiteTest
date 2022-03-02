package service;

import repository.BaseRepository;

import java.util.List;

public abstract class ShopService<E,R extends BaseRepository<E>> {

    private R r;

    public ShopService(R r) {
        this.r = r;
    }

    public void save(E e){
        r.save(e);
    }

    public void update(E e){
        r.update(e);
    }

    public void findAll(){
        List<E> all = r.findAll();
        for (E e: all) {
            System.out.println(e.toString());
        }
    }

    public void delete(int id){
        r.delete(id);
    }

    public E findById(int id){
        E byId = r.findById(id);
        return byId;
    }
}
