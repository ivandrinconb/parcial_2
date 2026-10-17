package edu.rentcar.model;

import edu.rentcar.model.composite.ServiceComponent;

import java.util.ArrayList;
import java.util.List;

public class ServicePackage implements ServiceComponent {
    private String name;
    private List<ServiceComponent> components = new ArrayList<>();

    public ServicePackage(String name) {
        this.name = name;
    }

    public void addComponent(ServiceComponent component) {
        components.add(component);
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder(name + ": ");
        for (ServiceComponent c : components) {
            desc.append(c.getDescription()).append(" | ");
        }
        return desc.toString();
    }

    @Override
    public double getCost() {
        return components.stream().mapToDouble(ServiceComponent::getCost).sum();
    }
}