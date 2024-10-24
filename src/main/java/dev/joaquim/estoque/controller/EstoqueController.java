package dev.joaquim.estoque.controller;

import dev.joaquim.estoque.payload.ItemDTO;
import dev.joaquim.estoque.service.EstoqueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO input) {
        return new ResponseEntity<ItemDTO>(
                estoqueService.createItem(input), HttpStatus.CREATED
        );
    }

//    //READ
    @GetMapping
    public ResponseEntity<Page<ItemDTO>> listAllItems(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(estoqueService.listAllItems(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> listOneItem(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.listOneItem(id));
    }

//    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(
            @PathVariable Long id,
            @RequestBody ItemDTO input
    ) {
        return new ResponseEntity<>(
                estoqueService.updateItem(id, input), HttpStatus.NO_CONTENT
        );
    }
//
    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        estoqueService.deleteItem(id);
    }
}
