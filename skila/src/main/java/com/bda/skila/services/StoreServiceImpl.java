package com.bda.skila.services;

import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.StoreDto;
import com.bda.skila.repositories.StoreRepository;
import com.bda.skila.services.mappers.StoreDtoMapper;
import com.bda.skila.services.mappers.StoreMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository repository;
    private final StoreDtoMapper dtoMapper;
    private final StoreMapper entityMapper;

    public StoreServiceImpl(StoreRepository repository,
                            StoreDtoMapper dtoMapper,
                            StoreMapper entityMapper){
        this.repository = repository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public StoreDto add(StoreDto entity) {
        Optional<Store> store = Stream.of(entity).map(entityMapper).findFirst();
        store.ifPresent(repository::save);
        return store.map(dtoMapper).orElseThrow();
    }

    @Override
    public StoreDto update(StoreDto entity) {
        Optional<Store> store = Stream.of(entity).map(entityMapper).findFirst();
        store.ifPresent(repository::save);
        return store.map(dtoMapper).orElseThrow();
    }

    @Override
    public StoreDto delete(Long id) {
        StoreDto store = this.getById(id);
        if(store!= null){
            Optional<Store> entity = Stream.of(store).map(entityMapper).findFirst();
            entity.ifPresent(repository::delete);

        }
        return store;
    }

    @Override
    public StoreDto getById(Long id) {
        Optional<Store> film = this.repository.findById(id);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<StoreDto> getAll() {
        List<Store> films = this.repository.findAll();
        return films
                .stream()
                .map(dtoMapper)
                .toList();
    }
}
