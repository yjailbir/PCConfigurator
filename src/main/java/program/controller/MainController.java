package program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import program.DAO.IronDAO;
import program.DAO.UserDAO;
import program.model.Configuration;
import program.model.User;
import program.model.iron.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/PCConfigurator")
@SessionScope
public class MainController {

    private final UserDAO userDAO;
    private final IronDAO ironDAO;
    private boolean isAdmin;
    private boolean isUser;
    private int userId;

    @Autowired
    public MainController(UserDAO userDAO, IronDAO ironDAO){
        this.userDAO = userDAO;
        this.ironDAO = ironDAO;
        this.isAdmin = false;
        this.isUser = false;
    }

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);

        return "home";

    }

    @GetMapping("/catalog")
    public String catalog(Model model){
        model.addAttribute("isAdmin", isAdmin);

        return "catalog";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") User user){return "enterPages/register";}

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "enterPages/register";
        else if (userDAO.isExist(user))
            return "alertPages/userExistError";
        else {
            userDAO.save(user);
            isUser = true;
            userId = userDAO.getUser(user.getUsername()).getUser_id();
            isAdmin = false;
            return "redirect:/PCConfigurator/catalog";
        }
    }

    @GetMapping("/logIn")
    public String logIn(@ModelAttribute("user") User user){
        return "enterPages/login";
    }

    @PostMapping("/logIn")
    public String logInPost(@ModelAttribute("user") User user){
        if(!userDAO.isExist(user) || !userDAO.getUser(user.getUsername()).getUserPassword().equals(user.getUserPassword())){
            return "alertPages/passwordError";
        }
        else {
            if(user.getUsername().equals("admin") && user.getUserPassword().equals("adminPassword")){
                isAdmin = true;
                isUser = false;
            }
            else{
                isUser = true;
                userId = userDAO.getUser(user.getUsername()).getUser_id();
                isAdmin = false;

            }
            return "redirect:/PCConfigurator";
        }
    }

    @GetMapping("/logOut")
    public String logOut(){
        isAdmin = false;
        isUser = false;
        userId = -1;

        return "redirect:/PCConfigurator";
    }

    @GetMapping("catalog/{ironType}")
    public String ironList(Model model, @PathVariable("ironType") String ironType){
        model.addAttribute("isAdmin", isAdmin);

        switch (ironType) {
            case "cpu" -> model.addAttribute("cpuList", ironDAO.getAllCpus());
            case "gpu" -> model.addAttribute("gpuList", ironDAO.getAllGpus());
            case "motherboards" -> model.addAttribute("motherboardList", ironDAO.getAllMotherboards());
            case "ram" -> model.addAttribute("ramList", ironDAO.getAllRam());
            case "ssd" -> model.addAttribute("ssdList", ironDAO.getAllSsd());
            case "ssdM2" -> model.addAttribute("ssdM2List", ironDAO.getAllSsdM2());
            case "hdd" -> model.addAttribute("hddList", ironDAO.getAllHdd());
            case "powerSupplies" -> model.addAttribute("powerSupplyList", ironDAO.getAllPowerSupply());
            case "coolers" -> model.addAttribute("coolerList", ironDAO.getAllCoolers());
            //case "cases" -> model.addAttribute("casesList", ironDAO.getAllCases());
        }

        return "ironList";
    }

    @GetMapping("/catalog/{ironType}/{currentIron}")
    public String currentIron(Model model, @PathVariable("ironType") String ironType, @PathVariable("currentIron") String currentIron){
        String[] ironMainAttributes = currentIron.split("_");
        model.addAttribute("manufacturer", ironMainAttributes[0]);
        model.addAttribute("model", ironMainAttributes[1]);
        model.addAttribute("isUser", isUser);

        switch (ironType){
            case "cpu" -> model.addAttribute("cpuModel", (CPU) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "gpu" -> model.addAttribute("gpuModel", (GPU) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "motherboard" -> model.addAttribute("motherboardModel", (Motherboard) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "ram" -> model.addAttribute("ramModel", (RAM) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "ssd" -> model.addAttribute("ssdModel", (SSD) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "ssdM2" -> model.addAttribute("ssdM2Model", (SSDm2) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "hdd" -> model.addAttribute("hddModel", (HDD) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "powerSupply" -> model.addAttribute("powerSupplyModel", (PowerSupply) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            case "cooler" -> model.addAttribute("coolerModel", (Cooler) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0].strip(), ironMainAttributes[1].strip()));
            //case "case" -> model.addAttribute("caseModel", ironDAO.getCurrentIron(ironType, ironMainAttributes[0], ironMainAttributes[1]));
        }

        return "currentIron";
    }

    @DeleteMapping("/catalog/{ironType}/{currentIron}")
    public String deleteIron(
            @PathVariable("ironType") String ironType,
            @PathVariable("currentIron") String currentIron
    ){
        String[] ironMainAttributes = currentIron.split("_");

        if(isAdmin){
            ironDAO.deleteCurrentIron(ironType, ironMainAttributes[0], ironMainAttributes[1]);

            return "alertPages/successDelete";
        }
        else
            return "redirect: /PCConfigurator/catalog/{ironType}";
    }

    @GetMapping("/add/{ironType}")
    public String add(
            //@ModelAttribute("case") Case aCase,
            @ModelAttribute("cooler") Cooler cooler,
            @ModelAttribute("cpu") CPU cpu,
            @ModelAttribute("gpu") GPU gpu,
            @ModelAttribute("hdd") HDD hdd,
            @ModelAttribute("motherboard") Motherboard motherboard,
            @ModelAttribute("powerSupply") PowerSupply powerSupply,
            @ModelAttribute("ram") RAM ram,
            @ModelAttribute("ssd") SSD ssd,
            @ModelAttribute("ssdM2") SSDm2 ssDm2,
            @PathVariable("ironType") String ironType
    ){
        if(!isAdmin)
            return "redirect:/PCConfigurator";
        else
            return "newIron";

    }

    @PostMapping("/add/{type}")
    public String addPost(
            //@ModelAttribute("case") Case aCase,
            @ModelAttribute("cooler") Cooler cooler,
            @ModelAttribute("cpu") CPU cpu,
            @ModelAttribute("gpu") GPU gpu,
            @ModelAttribute("hdd") HDD hdd,
            @ModelAttribute("motherboard") Motherboard motherboard,
            @ModelAttribute("powerSupply") PowerSupply powerSupply,
            @ModelAttribute("ram") RAM ram,
            @ModelAttribute("ssd") SSD ssd,
            @ModelAttribute("ssdM2") SSDm2 ssDm2,
            @PathVariable("type") String ironType
    ){
        if(isAdmin){
            switch (ironType) {
                //case "case" -> ironDAO.saveCase(aCase);
                case "cooler" -> ironDAO.saveCooler(cooler);
                case "cpu" -> ironDAO.saveCpu(cpu);
                case "gpu" -> ironDAO.saveGpu(gpu);
                case "hdd" -> ironDAO.saveHdd(hdd);
                case "motherboard" -> ironDAO.saveMotherboard(motherboard);
                case "powerSupply" -> ironDAO.savePowerSupply(powerSupply);
                case "ram" -> ironDAO.saveRam(ram);
                case "ssd" -> ironDAO.saveSsd(ssd);
                case "ssdM2" -> ironDAO.saveSsdM2(ssDm2);
            }
        }

        return "alertPages/successAdd";
    }

    @PostMapping("/myConfiguration/add/{ironType}/{currentIron}")
    public String addIronToConfiguration(
            Model model,@PathVariable("ironType") String ironType,
            @PathVariable("currentIron") String currentIron
    ){
        String[] ironMainAttributes = currentIron.split("_");
        model.addAttribute("manufacturer", ironMainAttributes[0]);
        model.addAttribute("model", ironMainAttributes[1]);
        model.addAttribute("isUser", isUser);

        switch (ironType){
            case "cpu" -> {
                CPU cpu = (CPU) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, cpu.getId(), userId);
            }
            case "gpu" -> {
                GPU gpu = (GPU) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, gpu.getId(), userId);
            }
            case "motherboard" -> {
                Motherboard motherboard = (Motherboard) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, motherboard.getId(), userId);
            }
            case "ram" -> {
                RAM ram = (RAM) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, ram.getId(), userId);
            }
            case "ssd" -> {
                SSD ssd = (SSD) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, ssd.getId(), userId);
            }
            case "ssdM2" -> {
                SSDm2 ssdM2 = (SSDm2) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, ssdM2.getId(), userId);
            }
            case "hdd" -> {
                HDD hdd = (HDD) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, hdd.getId(), userId);
            }
            case "powerSupply" -> {
                PowerSupply powerSupply = (PowerSupply) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, powerSupply.getId(), userId);
            }
            case "cooler" -> {
                Cooler cooler = (Cooler) ironDAO.getCurrentIronByName(ironType, ironMainAttributes[0], ironMainAttributes[1]);
                ironDAO.addIronToConfiguration(ironType, cooler.getId(), userId);
            }
        }

        return "alertPages/successConfigurationUpdate";
    }

    @GetMapping("/myConfiguration")
    public String configurationPage(Model model){
        if(isUser){
            Configuration userPC = ironDAO.getConfiguration(userId);
            model.addAttribute("configuration", userPC);
            model.addAttribute("errors", userPC.checkCompatibility());

            return "configurationPage";
        }

        return "redirect:/PCConfigurator";
    }

}
