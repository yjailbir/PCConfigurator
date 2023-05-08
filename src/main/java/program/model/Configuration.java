package program.model;

import lombok.*;
import program.model.iron.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    //private Case aCase;
    private Cooler cooler;
    private CPU cpu;
    private GPU gpu;
    private HDD hdd;
    //private int hddCount;
    private Motherboard motherboard;
    private PowerSupply powerSupply;
    private RAM ram;
    //private int ramCount;
    private SSD ssd;
    //private int ssdCount;
    private SSDm2 ssdM2;
    //private int ssdM2Count;

    public HashMap<String, ArrayList<String>> checkCompatibility(){
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        if(cooler != null && cpu!= null){
                if(cooler.getTDP() < cpu.getTDP())
                    result.put("cooler", new ArrayList<>(List.of("Слишком высокий TDP процессора")));
        }
        if(cpu != null){
            ArrayList<String> cpuErrors = new ArrayList<>();
            if(cooler != null){
                if(cooler.getTDP() < cpu.getTDP())
                    cpuErrors.add("Слишком низкий TDP кулера");
            }
            if(motherboard != null){
                if(!Objects.equals(motherboard.getSocket(), cpu.getSocket()))
                    cpuErrors.add("Несовместимый сокет материнской платы");
                if(!Objects.equals(motherboard.getMemoryType(), cpu.getMemoryType()))
                    cpuErrors.add("Несовместимый тип памяти материнской платы");
            }
            if(cpuErrors.size() > 0)
                result.put("cpu", cpuErrors);
        }
        if(gpu != null){
            if(powerSupply != null){
                if(powerSupply.getPower() < gpu.getRequiredPowerSupply())
                    result.put("gpu", new ArrayList<>(List.of("Слишком низкая мощность блока питания")));
            }
        }
        if(motherboard != null){
            ArrayList<String> motherboardErrors = new ArrayList<>();
            if(cpu != null){
                if(!Objects.equals(motherboard.getSocket(), cpu.getSocket()))
                    motherboardErrors.add("Несовместимый сокет процессора");
                if(!Objects.equals(motherboard.getMemoryType(), cpu.getMemoryType()))
                    motherboardErrors.add("Несовместимый тип памяти процессора");
            }
            if(ram != null)
                if(!Objects.equals(ram.getType(), motherboard.getMemoryType()))
                    motherboardErrors.add("Несовместимый тип оперативной памяти");
            if(motherboardErrors.size() > 0)
                result.put("motherboard", motherboardErrors);
        }
        if(powerSupply != null){
            if(gpu != null){
                if(powerSupply.getPower() < gpu.getRequiredPowerSupply())
                    result.put("powerSupply", new ArrayList<>(List.of("Слишком высокая мощность видеокарты")));
            }
        }
        if(ram != null){
            if(motherboard != null){
                if(!Objects.equals(ram.getType(), motherboard.getMemoryType())){
                    result.put("ram", new ArrayList<>(List.of("Несовместимый тип памяти материнской платы")));
                }            }
        }
        return result;
    }
}
