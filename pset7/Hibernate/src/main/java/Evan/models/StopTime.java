package Evan.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="stop_times")
public class StopTime {


    @Column(name = "stop_id")
    private Integer id;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "arrival_time", unique = true, nullable = false)
    private String arrivalTime;


    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
