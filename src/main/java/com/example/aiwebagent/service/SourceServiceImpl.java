package com.example.aiwebagent.service;

import com.example.aiwebagent.contract.source.SourceDto;
import com.example.aiwebagent.contract.source.SourceService;
import com.example.aiwebagent.model.Source;
import com.example.aiwebagent.repository.SourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the SourceService contract.
 * 
 * Provides business logic for managing news sources including validation,
 * CRUD operations, and status management.
 * 
 * This implementation follows:
 * - TDD principles (tested before implementation)
 * - Clean Code (no Lombok, explicit getters/setters)
 * - SOLID principles (Single Responsibility)
 * - Java 25 features where applicable
 * 
 * @author isalnikov
 * @version 1.0.0
 */
@Service
@Transactional
public class SourceServiceImpl implements SourceService {

    private static final Logger logger = LoggerFactory.getLogger(SourceServiceImpl.class);

    private final SourceRepository sourceRepository;

    /**
     * Constructs a new SourceServiceImpl with the required dependencies.
     * 
     * @param sourceRepository the repository for source data access
     */
    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
        logger.info("SourceServiceImpl initialized");
    }

    @Override
    public SourceDto addSource(SourceDto sourceDto) {
        logger.debug("Adding new source: {}", sourceDto.name());

        if (sourceDto.url() == null || sourceDto.url().isBlank()) {
            throw new IllegalArgumentException("Source URL cannot be empty");
        }

        if (existsByUrl(sourceDto.url())) {
            throw new IllegalArgumentException("Source with URL '" + sourceDto.url() + "' already exists");
        }

        Source source = new Source(
                sourceDto.name(),
                sourceDto.url(),
                sourceDto.type(),
                sourceDto.isActive()
        );
        source.setParseSelector(sourceDto.parseSelector());

        Source savedSource = sourceRepository.save(source);
        logger.info("Source added successfully with ID: {}", savedSource.getId());

        return mapToDto(savedSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SourceDto> listSources() {
        logger.debug("Listing all sources");
        List<Source> sources = sourceRepository.findAll();
        logger.info("Found {} sources", sources.size());
        return sources.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeSource(Long id) {
        logger.debug("Removing source with ID: {}", id);
        
        if (!sourceRepository.existsById(id)) {
            throw new IllegalArgumentException("Source with ID " + id + " not found");
        }

        sourceRepository.deleteById(id);
        logger.info("Source removed successfully with ID: {}", id);
    }

    @Override
    public SourceDto toggleSource(Long id) {
        logger.debug("Toggling source status for ID: {}", id);
        
        Source source = sourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Source with ID " + id + " not found"));

        source.setActive(!source.isActive());
        Source updatedSource = sourceRepository.save(source);
        
        logger.info("Source status toggled. New status: {}", updatedSource.isActive());
        return mapToDto(updatedSource);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SourceDto> getSourceById(Long id) {
        logger.debug("Getting source by ID: {}", id);
        return sourceRepository.findById(id).map(this::mapToDto);
    }

    @Override
    public SourceDto updateSource(Long id, SourceDto sourceDto) {
        logger.debug("Updating source with ID: {}", id);
        
        Source existingSource = sourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Source with ID " + id + " not found"));

        existingSource.setName(sourceDto.name());
        existingSource.setUrl(sourceDto.url());
        existingSource.setType(sourceDto.type());
        existingSource.setParseSelector(sourceDto.parseSelector());
        existingSource.setActive(sourceDto.isActive());

        Source updatedSource = sourceRepository.save(existingSource);
        logger.info("Source updated successfully with ID: {}", updatedSource.getId());
        
        return mapToDto(updatedSource);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return sourceRepository.existsByUrl(url);
    }

    /**
     * Maps a Source entity to a SourceDto record.
     * 
     * @param source the source entity to map
     * @return the corresponding source DTO
     */
    private SourceDto mapToDto(Source source) {
        return new SourceDto(
                source.getId(),
                source.getName(),
                source.getUrl(),
                source.getType(),
                source.getParseSelector(),
                source.isActive(),
                source.getLastParsedAt(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
