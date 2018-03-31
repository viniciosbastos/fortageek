package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Item;

public interface IItemRepository extends JpaRepository<Item, Integer> {

	public Item findByIdAndNome(Integer id, String nome);
}
