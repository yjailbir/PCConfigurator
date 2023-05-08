package program.model.iron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerSupply {
    private int id;
    private String manufacturer;
    private String model;
    private int power;
    private int sataCount;
}
