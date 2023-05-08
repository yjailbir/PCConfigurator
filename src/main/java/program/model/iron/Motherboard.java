package program.model.iron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Motherboard {
    private int id;
    private String manufacturer;
    private String model;
    private String formFactor;
    private String socket;
    private int countOfRamPlanks;
    private String memoryType;
    private int m2Count;
    private int sataCount;
}
