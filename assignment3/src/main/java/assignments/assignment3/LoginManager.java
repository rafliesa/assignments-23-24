package assignments.assignment3;

import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;

// Kelas yang merepresentasikan login manager
public class LoginManager {
    private final AdminSystemCLI adminSystem;
    private final CustomerSystemCLI customerSystem;

    public LoginManager(AdminSystemCLI adminSystem, CustomerSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

    // fungsi ini berfungsi sebagai portal yang mengantarkan pengguna 
    // ke antarmuka yang sesuai rolenya
    public void getSystem(String role){
        if(role == "Customer"){
            customerSystem.run();
        }else{
            adminSystem.run();
        }
    }
}
