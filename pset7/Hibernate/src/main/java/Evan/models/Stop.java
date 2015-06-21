package Evan.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * SCRIPT BORROWED FROM...
 *
 * User: mpmenne
 * Date: 7/3/14
 * Time: 1:40 AM
 */

@Entity
@Table(name="stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stop_id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "stop_name")
    private String stopName;


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
