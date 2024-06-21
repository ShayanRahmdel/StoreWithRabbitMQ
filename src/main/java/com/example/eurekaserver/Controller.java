package com.example.eurekaserver;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class Controller {
    ServerInstanceService service;

    @GetMapping("/see-services")
    public List<ServerInfo> getServices() {
        return service.getServerInfoList();
    }

}
