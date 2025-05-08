package org.firas.portfolio.firasportfolio.controller;

import org.firas.portfolio.firasportfolio.data.PcInfo;
import org.firas.portfolio.firasportfolio.data.VisitorInfo;
import org.firas.portfolio.firasportfolio.repository.PcInfoRepository;
import org.firas.portfolio.firasportfolio.repository.VisitorInfoRepository;
import org.firas.portfolio.firasportfolio.service.VisitorInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin
public class VisitorInfoController {

    @Autowired
    private VisitorInforService service;
    @Autowired
    private PcInfoRepository repository;
    @GetMapping
    public List<VisitorInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VisitorInfo getById(@PathVariable Long id) {
        return service.getById(id).orElseThrow(() -> new RuntimeException("Visitor not found"));
    }

    @PostMapping
    public VisitorInfo create(@RequestBody VisitorInfo info) {
        return service.create(info);
    }

    @PutMapping("/{id}")
    public VisitorInfo update(@PathVariable Long id, @RequestBody VisitorInfo info) {
        return service.update(id, info);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pcinfo")
    public Map<String, String> getPcInfo() {
        Map<String, String> info = new HashMap<>();
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);

            StringBuilder macAddress = new StringBuilder();
            if (ni != null && ni.getHardwareAddress() != null) {
                byte[] mac = ni.getHardwareAddress();
                for (int i = 0; i < mac.length; i++) {
                    macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                }
            } else {
                macAddress.append("N/A");
            }
            PcInfo pcInfo = PcInfo.builder().pcName(hostname).macAddress(macAddress.toString()).build();
            repository.save(pcInfo);
            info.put("pcName", hostname);
            info.put("macAddress", macAddress.toString());

        } catch (Exception e) {
            info.put("error", e.getMessage());
        }

        return info;
    }
}

