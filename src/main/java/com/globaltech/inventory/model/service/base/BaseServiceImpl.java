package com.globaltech.inventory.model.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Base implementation of CrudService
 * 
 * @param <T> the entity type
 * @param <ID> the ID type
 * @param <R> the repository type
 */
@Transactional
public abstract class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements CrudService<T, ID> {
    
    protected final R repository;
    
    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }
    
    @Override
    @Transactional
    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAllSorted(String sortField) {
        return repository.findAll(Sort.by(sortField));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findByField(String fieldName, Object value) {
        return repository.findByField(fieldName, value);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<T> findOneByField(String fieldName, Object value) {
        return repository.findOneByField(fieldName, value);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
    
    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }
    
    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
} 