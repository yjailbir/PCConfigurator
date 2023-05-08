package program.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import program.model.Configuration;
import program.model.iron.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class IronDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IronDAO(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public Object getCurrentIronByName(String type, String manufacturer, String model){
        switch (type){
            /*case "case" -> {
                return getAllCases().stream()
                        .filter(aCase -> aCase.getManufacturer().equals(manufacturer) && aCase.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }*/
            case "cooler" -> {
                return getAllCoolers().stream()
                        .filter(cooler -> cooler.getManufacturer().equals(manufacturer) && cooler.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "cpu" -> {
                return getAllCpus().stream()
                        .filter(cpu -> cpu.getManufacturer().equals(manufacturer) && cpu.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "gpu" -> {
                return getAllGpus().stream()
                        .filter(gpu -> gpu.getManufacturer().equals(manufacturer) && gpu.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "hdd" -> {
                return getAllHdd().stream()
                        .filter(hdd -> hdd.getManufacturer().equals(manufacturer) && hdd.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "motherboard" -> {
                return getAllMotherboards().stream()
                        .filter(motherboard -> motherboard.getManufacturer().equals(manufacturer) && motherboard.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "powerSupply" -> {
                return getAllPowerSupply().stream()
                        .filter(powerSupply -> powerSupply.getManufacturer().equals(manufacturer) && powerSupply.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "ram" -> {
                return getAllRam().stream()
                        .filter(ram -> ram.getManufacturer().equals(manufacturer) && ram.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "ssd" -> {
                return getAllSsd().stream()
                        .filter(ssd -> ssd.getManufacturer().equals(manufacturer) && ssd.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            case "ssdM2" -> {
                return getAllSsdM2().stream()
                        .filter(ssDm2 -> ssDm2.getManufacturer().equals(manufacturer) && ssDm2.getModel().equals(model))
                        .findFirst()
                        .orElse(null);
            }
            default -> {
                return null;
            }
        }
    }

    public Object getCurrentIronById(String type, int id){
        switch (type){
            case "cooler" -> {
                return getAllCoolers().stream()
                        .filter(cooler -> cooler.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "cpu" -> {
                return getAllCpus().stream()
                        .filter(cpu -> cpu.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "gpu" -> {
                return getAllGpus().stream()
                        .filter(gpu -> gpu.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "hdd" -> {
                return getAllHdd().stream()
                        .filter(hdd -> hdd.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "motherboard" -> {
                return getAllMotherboards().stream()
                        .filter(motherboard -> motherboard.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "powerSupply" -> {
                return getAllPowerSupply().stream()
                        .filter(powerSupply -> powerSupply.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "ram" -> {
                return getAllRam().stream()
                        .filter(ram -> ram.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "ssd" -> {
                return getAllSsd().stream()
                        .filter(ssd -> ssd.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            case "ssdM2" -> {
                return getAllSsdM2().stream()
                        .filter(ssDm2 -> ssDm2.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
            default -> {
                return null;
            }
        }
    }

    public void deleteCurrentIron(String type, String manufacturer, String model){
        switch (type){
            case "cooler" -> jdbcTemplate.update("DELETE FROM coolertable where manufacturer = ? and model = ?", manufacturer, model);
            case "cpu" -> jdbcTemplate.update("DELETE FROM cputable where manufacturer = ? and model = ?", manufacturer, model);
            case "gpu" -> jdbcTemplate.update("DELETE FROM gputable where manufacturer = ? and model = ?", manufacturer, model);
            case "hdd" -> jdbcTemplate.update("DELETE FROM hddtable where manufacturer = ? and model = ?", manufacturer, model);
            case "motherboard" -> jdbcTemplate.update("DELETE FROM motherboardtable where manufacturer = ? and model = ?", manufacturer, model);
            case "powerSupply" -> jdbcTemplate.update("DELETE FROM powersupplytable where manufacturer = ? and model = ?", manufacturer, model);
            case "ram" -> jdbcTemplate.update("DELETE FROM ramtable where manufacturer = ? and model = ?", manufacturer, model);
            case "ssd" -> jdbcTemplate.update("DELETE FROM ssdtable where manufacturer = ? and model = ?", manufacturer, model);
            case "ssdM2" -> jdbcTemplate.update("DELETE FROM ssdm2table where manufacturer = ? and model = ?", manufacturer, model);
        }
    }

    public void addIronToConfiguration(String type, int ironId, int userId){
        switch (type){
            case "cooler" -> jdbcTemplate.update("UPDATE configuration SET cooler_id = ? WHERE user_id = ?", ironId, userId);
            case "cpu" -> jdbcTemplate.update("UPDATE configuration SET cpu_id = ? WHERE user_id = ?", ironId, userId);
            case "gpu" -> jdbcTemplate.update("UPDATE configuration SET gpu_id = ? WHERE user_id = ?", ironId, userId);
            case "hdd" -> jdbcTemplate.update("UPDATE configuration SET hdd_id = ? WHERE user_id = ?", ironId, userId);
            case "motherboard" -> jdbcTemplate.update("UPDATE configuration SET motherboard_id = ? WHERE user_id = ?", ironId, userId);
            case "powerSupply" -> jdbcTemplate.update("UPDATE configuration SET powersupply_id = ? WHERE user_id = ?", ironId, userId);
            case "ram" -> jdbcTemplate.update("UPDATE configuration SET ram_id = ? WHERE user_id = ?", ironId, userId);
            case "ssd" -> jdbcTemplate.update("UPDATE configuration SET ssd_id = ? WHERE user_id = ?", ironId, userId);
            case "ssdM2" -> jdbcTemplate.update("UPDATE configuration SET ssdm2_id = ? WHERE user_id = ?", ironId, userId);
        }
    }

    public Configuration getConfiguration(int user_id){
        Cooler cooler;
        CPU cpu;
        GPU gpu;
        HDD hdd;
        Motherboard motherboard;
        PowerSupply powerSupply;
        RAM ram;
        SSD ssd;
        SSDm2 ssdM2;
        try{cooler = (Cooler) getCurrentIronById("cooler", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT cooler_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){cooler = null;}

        try{cpu = (CPU) getCurrentIronById("cpu", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT cpu_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){cpu = null;}

        try{gpu = (GPU) getCurrentIronById("gpu", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT gpu_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){gpu = null;}

        try{hdd = (HDD) getCurrentIronById("hdd", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT hdd_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){hdd = null;}

        try{motherboard = (Motherboard) getCurrentIronById("motherboard", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT motherboard_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){motherboard = null;}

        try{powerSupply = (PowerSupply) getCurrentIronById("powerSupply", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT powersupply_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){powerSupply = null;}

        try{ram = (RAM) getCurrentIronById("ram", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT ram_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){ram = null;}

        try{ssd = (SSD) getCurrentIronById("ssd", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT ssd_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){ssd = null;}

        try{ssdM2 = (SSDm2) getCurrentIronById("ssdM2", Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT ssdm2_id FROM configuration WHERE user_id = ?", new Object[]{user_id}, Integer.class)));}
        catch (NullPointerException e){ssdM2 = null;}

        return new Configuration(
                cooler,
                cpu,
                gpu,
                hdd,
                motherboard,
                powerSupply,
                ram,
                ssd,
                ssdM2
        );
    }

    /*public List<Case> getAllCases(){
        return jdbcTemplate.query("SELECT * FROM caseTable", new BeanPropertyRowMapper<>(Case.class));
    }

    public void saveCase(Case aCase){
        jdbcTemplate.update(
                "INSERT INTO casetable(manufacturer, model)  VALUES(?, ?)",
                aCase.getManufacturer(),
                aCase.getModel()
        );
    }*/

    public List<Cooler> getAllCoolers(){
        return jdbcTemplate.query("SELECT * FROM coolerTable", new BeanPropertyRowMapper<>(Cooler.class));
    }

    public void saveCooler(Cooler cooler){
        jdbcTemplate.update(
                "INSERT INTO coolertable(manufacturer, model, tdp) VALUES(?, ?, ?)",
                cooler.getManufacturer(),
                cooler.getModel(),
                cooler.getTDP());
    }

    public List<CPU> getAllCpus(){
        return jdbcTemplate.query("SELECT * FROM cpuTable", new BeanPropertyRowMapper<>(CPU.class));
    }

    public void saveCpu(CPU cpu){
        jdbcTemplate.update(
                "INSERT INTO cputable(manufacturer, model, socket, cores, threads, frequency, memoryType, tdp) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                cpu.getManufacturer(),
                cpu.getModel(),
                cpu.getSocket(),
                cpu.getCores(),
                cpu.getThreads(),
                cpu.getFrequency(),
                cpu.getMemoryType(),
                cpu.getTDP());
    }

    public List<GPU> getAllGpus(){
        return jdbcTemplate.query("SELECT * FROM gpuTable", new BeanPropertyRowMapper<>(GPU.class));
    }

    public void saveGpu(GPU gpu){
        jdbcTemplate.update(
                "INSERT INTO gputable(manufacturer, model, memory, MHzFrequency, memoryType, rayTracing, requiredPowerSupply) VALUES(?, ?, ?, ?, ?, ?, ?)",
                gpu.getManufacturer(),
                gpu.getModel(),
                gpu.getMemory(),
                gpu.getMHzFrequency(),
                gpu.getMemoryType(),
                gpu.getRayTracing(),
                gpu.getRequiredPowerSupply());
    }

    public List<HDD> getAllHdd(){
        return jdbcTemplate.query("SELECT * FROM hddTable", new BeanPropertyRowMapper<>(HDD.class));
    }

    public void saveHdd(HDD hdd){
        jdbcTemplate.update("INSERT INTO hddtable(manufacturer, model, memory) VALUES(?, ?, ?)",
                hdd.getManufacturer(),
                hdd.getModel(),
                hdd.getMemory());
    }

    public List<Motherboard> getAllMotherboards(){
        return jdbcTemplate.query("SELECT * FROM motherboardTable", new BeanPropertyRowMapper<>(Motherboard.class));
    }

    public void saveMotherboard(Motherboard motherboard){
        jdbcTemplate.update("INSERT INTO motherboardtable(manufacturer, model, formFactor, socket, countOfRamPlanks, memoryType, m2Count, sataCount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                motherboard.getManufacturer(),
                motherboard.getModel(),
                motherboard.getFormFactor(),
                motherboard.getSocket(),
                motherboard.getCountOfRamPlanks(),
                motherboard.getMemoryType(),
                motherboard.getM2Count(),
                motherboard.getSataCount());
    }

    public List<PowerSupply> getAllPowerSupply(){
        return jdbcTemplate.query("SELECT * FROM powerSupplyTable", new BeanPropertyRowMapper<>(PowerSupply.class));
    }

    public void savePowerSupply(PowerSupply powerSupply){
        jdbcTemplate.update("INSERT INTO powersupplytable(manufacturer, model, power, sataCount) VALUES(?, ?, ?, ?)",
                powerSupply.getManufacturer(),
                powerSupply.getModel(),
                powerSupply.getPower(),
                powerSupply.getSataCount());
    }

    public List<RAM> getAllRam(){
        return jdbcTemplate.query("SELECT * FROM ramTable", new BeanPropertyRowMapper<>(RAM.class));
    }

    public void saveRam(RAM ram){
        jdbcTemplate.update("INSERT INTO ramtable(manufacturer, model, type, memory, MHzFrequency) VALUES(?, ?, ?, ?, ?)",
                ram.getManufacturer(),
                ram.getModel(),
                ram.getType(),
                ram.getMemory(),
                ram.getMHzFrequency());
    }

    public List<SSD> getAllSsd(){
        return jdbcTemplate.query("SELECT * FROM ssdTable", new BeanPropertyRowMapper<>(SSD.class));
    }

    public void saveSsd(SSD ssd){
        jdbcTemplate.update("INSERT INTO ssdtable(manufacturer, model, memory) VALUES(?, ?, ?)",
                ssd.getManufacturer(),
                ssd.getModel(),
                ssd.getMemory());
    }

    public List<SSDm2> getAllSsdM2(){
        return jdbcTemplate.query("SELECT * FROM ssdM2Table", new BeanPropertyRowMapper<>(SSDm2.class));
    }

    public void saveSsdM2(SSDm2 ssDm2){
        jdbcTemplate.update("INSERT INTO ssdm2table(manufacturer, model, memory) VALUES(?, ?, ?)",
                ssDm2.getManufacturer(),
                ssDm2.getModel(), ssDm2.getMemory());
    }
}
