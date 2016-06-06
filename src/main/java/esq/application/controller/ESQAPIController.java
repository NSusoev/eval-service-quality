package esq.application.controller;

import esq.application.model.ServiceQualitySurvey;
import esq.application.repository.ServiceQualitySurveyRepository;
import esq.application.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import esq.application.model.Service;
import java.util.List;

/**
 * REST API для взаимодействия с сервисом
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ESQAPIController {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceQualitySurveyRepository serviceQualitySurveyRepository;

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

    @RequestMapping("/survey/create/{name}")
    public ServiceQualitySurvey createServiceQualitySurvey(@PathVariable String name) {
        return serviceQualitySurveyRepository.save(new ServiceQualitySurvey(new Service(name)));
    }

    @RequestMapping("/survey/delete/{id}")
    public void deleteServiceQualitySurvey(@PathVariable long id) {
        serviceQualitySurveyRepository.delete(id);
    }

    @RequestMapping("/survey/list")
    public List<ServiceQualitySurvey> getServiceQualitySurveyList() {
        Iterable<ServiceQualitySurvey> services = serviceQualitySurveyRepository.findAll();
        return (List<ServiceQualitySurvey>) services;
    }

    @RequestMapping("/survey/{id}")
    public ServiceQualitySurvey getServiceQualitySurveyById(@PathVariable long id) {
        return serviceQualitySurveyRepository.findOne(id);
    }

}
