package program.model.iron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GPU {
    private int id;
    private String manufacturer;
    private String model;
    private int memory;
    private int MHzFrequency;
    private String memoryType;
    private String rayTracing;
    private int requiredPowerSupply;
}
