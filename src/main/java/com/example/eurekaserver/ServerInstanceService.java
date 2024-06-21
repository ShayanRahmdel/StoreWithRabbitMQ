package com.example.eurekaserver;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServerInstanceService {

    ServerRepository repository;

    public List<ServerInfo> getServerInfoList() {
        List<ServerInfo> serverInfoList = new ArrayList<>();
        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();
        List<Application> registeredApplications = applications.getRegisteredApplications();

        for (Application application : registeredApplications) {
            List<InstanceInfo> instances = application.getInstances();

            for (InstanceInfo instance : instances) {

                ServerInfo serverInfo = new ServerInfo();
                serverInfo.setServerName(instance.getAppName());
                serverInfo.setHostName(instance.getHostName());
                serverInfo.setPort(instance.getPort());

                serverInfoList.add(serverInfo);

                repository.saveAll(serverInfoList);
            }
        }

        return serverInfoList;
    }
}
