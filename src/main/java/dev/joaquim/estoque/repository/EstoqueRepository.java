package dev.joaquim.estoque.repository;

import dev.joaquim.estoque.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Item, Long> {
}
