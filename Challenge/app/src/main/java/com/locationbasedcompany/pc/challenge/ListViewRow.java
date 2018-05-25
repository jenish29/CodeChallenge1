package com.locationbasedcompany.pc.challenge;

/**
 * Created by pc on 5/25/18.
 */

public class ListViewRow {
    private String name, domain, breachDate, description, dataClasses;

    public ListViewRow(String name, String domain, String breachDate, String description, String dataClasses) {
        this.name = name;
        this.domain = domain;
        this.breachDate = breachDate;
        this.description = description;
        this.dataClasses = dataClasses;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getBreachDate() {
        return breachDate;
    }

    public String getDescription() {
        return description;
    }

    public String getDataClasses() {
        return dataClasses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setBreachDate(String breachDate) {
        this.breachDate = breachDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDataClasses(String dataClasses) {
        this.dataClasses = dataClasses;
    }
}
