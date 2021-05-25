package dao;

import exceptions.*;
import java.util.*;

public interface DAO<T> {

    List<T> getAll();

    T get(int cod);

    void save(T t) throws WrongOrderDayCreationException, WrongDeliveryDayCreationException, WrongClientCreateException;

    void delete(int cod);
}