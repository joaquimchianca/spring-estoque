package dev.joaquim.estoque.service;

import dev.joaquim.estoque.model.Item;
import dev.joaquim.estoque.payload.ItemDTO;
import dev.joaquim.estoque.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }


    public ItemDTO createItem(ItemDTO input) {

        Item item = new Item();
        item.setName(input.getName());
        item.setAmount(input.getAmount());
        item.setDescription(input.getDescription());

        Item itemSaved = estoqueRepository.save(item);

        return mapItemToDTO(itemSaved);

    }

    public Page<ItemDTO> listAllItems(Pageable pageable) {
        Page<Item> items = estoqueRepository.findAll(pageable);
        List<ItemDTO> itemsDTOList = items.getContent().stream().map(this::mapItemToDTO).toList();

        return new PageImpl<>(itemsDTOList, pageable, items.getTotalElements());
    }

    public ItemDTO listOneItem(Long id) {
        Item item = estoqueRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        return mapItemToDTO(item);
    }

    public ItemDTO updateItem(Long id, ItemDTO input) {
        Item target = estoqueRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );

        if (!target.getName().equalsIgnoreCase(input.getName())) {
            target.setName(input.getName());
        }

        if (input.getDescription() != null) {
            if (!target.getDescription().equalsIgnoreCase(input.getDescription())) {
                target.setDescription(input.getDescription());
            }
        }

        if (!target.getAmount().equals(input.getAmount())) {
            target.setAmount(input.getAmount());
        }

        return mapItemToDTO(estoqueRepository.save(target));
    }


    public void deleteItem(Long id) {
        estoqueRepository.deleteById(id);
    }

    private ItemDTO mapItemToDTO(Item item) {
        if (item == null) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setAmount(item.getAmount());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }
}
