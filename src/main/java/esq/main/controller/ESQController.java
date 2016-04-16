package esq.main.controller;

import esq.main.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import esq.main.model.Service;
import java.util.List;

/**
 * Created by nsusoev on 05.04.16.
 */

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ESQController {
    private static final String template = "Service: %s!";

    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping("/service/create/{name}")
    public Service createService(@PathVariable String name) {
        return serviceRepository.save(new Service(name));
    }

    @RequestMapping("/service/delete/{id}")
    public void deleteService(@PathVariable long id) {
        serviceRepository.delete(id);
    }

    @RequestMapping("/service/list")
    public List<Service> getServiceList() {
        Iterable<Service> services = serviceRepository.findAll();
        return (List<Service>) services;
    }

}
