package org.firas.portfolio.firasportfolio.service;

import org.firas.portfolio.firasportfolio.data.VisitorInfo;
import org.firas.portfolio.firasportfolio.repository.VisitorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorInforService {
    @Autowired
    private VisitorInfoRepository repository;

    public List<VisitorInfo> getAll() {
        return repository.findAll();
    }

    public Optional<VisitorInfo> getById(Long id) {
        return repository.findById(id);
    }

    public VisitorInfo create(VisitorInfo info) {
        return repository.save(info);
    }

    public VisitorInfo update(Long id, VisitorInfo updatedInfo) {
        return repository.findById(id).map(info -> {
            info.setIp(updatedInfo.getIp());
            info.setCity(updatedInfo.getCity());
            info.setRegion(updatedInfo.getRegion());
            info.setCountry(updatedInfo.getCountry());
            info.setOrg(updatedInfo.getOrg());
            info.setTimezone(updatedInfo.getTimezone());
            return repository.save(info);
        }).orElseThrow(() -> new RuntimeException("Visitor not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
