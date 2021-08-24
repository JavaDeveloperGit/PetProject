package ua.com.vovacoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.vovacoffee.model.Model;

public interface MainRepository <T extends Model, E extends Number> extends JpaRepository<T, E> {
}
