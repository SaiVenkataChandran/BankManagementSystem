
import java.util.*;
public class bankManagementSystem
{
    static Map<String,Bank> customers = new HashMap<>();
    static Map<String,bankEmployee> employees = new HashMap<>();
    static Admin admin = new Admin("admin","123");
    
    public static void main(String[] args) 
    {
        // Already existing customers in the bank
        customers.put("Arjun",new Bank("Arjun","123456",0.0));
        customers.put("Mahesh",new Bank("Mahesh","234567",0.0));
        customers.put("Ram",new Bank("Ram","345678",0.0));
        customers.put("Sushmitha",new Bank("Sushmitha","456789",0.0));
        customers.put("Srinivas",new Bank("Srinivas","567890",0.0));
        // Employees working in the bank
        employees.put("123",new bankEmployee("123","Shekar","Clerk"));
        employees.put("234",new bankEmployee("234","Raju","Clerk"));
        employees.put("345",new bankEmployee("345","Ramu","Clerk"));
        employees.put("456",new bankEmployee("456","Hari","Probationary officer"));
        employees.put("567",new bankEmployee("567","Srinu","Probationary officer"));
        
        
        Scanner sc = new Scanner(System.in);
        
        
        while(true)
        {
            System.out.println("Welcome to Bank Management System");
            System.out.println("1.New Customer (Register here)");
            System.out.println("2.Customer Login");
            System.out.println("3.Employee Login");
            System.out.println("4.Admin Login");
            System.out.println("5.Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) 
            {
            
                case 1: customerRegistration(sc);
                        break;
                case 2: customerLogin(sc);
                        break;
                case 3: employeeLogin(sc);
                        break;
                case 4: adminLogin(sc);
                        break;
                case 5: System.out.println("Exiting...Thank you for using the system.");
                        sc.close();
                        return;
                default: System.out.println("Invalid choice, Please try again.");
            }
            
        }
    }
        static void customerRegistration(Scanner sc)
        {
            System.out.print("Enter your Name:");
            String username = sc.nextLine();
            System.out.print("Enter your Password:");
            String password = sc.nextLine();
            double balance = 0.0;
            customers.put(username,new Bank(username,password,balance));
            System.out.println("Registration Successfull.");
            System.out.println("");
        }
        static void customerLogin(Scanner sc)
        {
            System.out.print("Enter your Name:");
            String username = sc.nextLine();
            System.out.print("Enter your Password:");
            String password = sc.nextLine();
            Bank customer = customers.get(username);
            if(customer != null && customer.getPassword().equals(password))
            {
                System.out.println("Login successful!");
                customerMenu(customer,sc);
            }
            else
            {
                System.out.println("Invalid Login Credentials.");
            }
        }
        static void employeeLogin(Scanner sc)
        {
            System.out.print("Enter your ID:");
            String employeeId = sc.nextLine();
            System.out.print("Enter your Name:");
            String name = sc.nextLine();
            bankEmployee employee = employees.get(employeeId);
            if(employee != null && employee.getName().equals(name))
            {
                System.out.println("Login successful!");
                employeeMenu(employee,sc);
            }
            else
            {
                System.out.println("Invalid Login Credentials.");
            }
            
        }
        static void adminLogin(Scanner sc)
        {
            System.out.print("Enter your Name:");
            String name = sc.nextLine();
            System.out.print("Enter your Password:");
            String password = sc.nextLine();
            if(name.equals(admin.getUsername()) && password.equals(admin.getPassword()))
            {
                System.out.println("Login Successful!");
                adminMenu(sc);
            }
            else
            {
                System.out.println("Invalid Login Credentials");
            }
        }
        
        static void customerMenu(Bank customer,Scanner sc)
        {
            while(true)
            {
                System.out.println("\nCustomer Menu");
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. View Balance");
                System.out.println("4. Add Interest");
                System.out.println("5. Logout");
                
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1: System.out.print("Enter Amount:");
                    double depositMoney = sc.nextDouble();
                    customer.deposit(depositMoney);
                    break;
                    
                    case 2: System.out.print("Enter Amount:");
                    double withDraw = sc.nextDouble();
                    customer.WithDraw(withDraw);
                    break;
                    
                    case 3: System.out.print("Balance :" + customer.getBalance());
                    break;
                    
                    case 4: System.out.print("Enter Intrest Rate:");
                    double rate = sc.nextDouble();
                    customer.AddIntrest(rate);
                    break;
                    
                    case 5: System.out.println("Loggin out...");
                    return;
                    
                    default: System.out.println("Invalid choice, Please try again");
                }
            }
        }
        
        public static void employeeMenu(bankEmployee employee,Scanner sc)
        {
            while(true)
            {
                System.out.println("\nEmployee Menu");
                System.out.println("1. View Customer Details");
                System.out.println("2. Logout");
                
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        System.out.print("Enter Customer Name:");
                        String name = sc.nextLine();
                        Bank customer = customers.get(name);
                        if(customer != null)
                        {
                            employee.viewCustomerDetails(customer);
                        }
                        else
                        {
                            System.out.println("Invalid username.");
                        }
                        break;
                    case 2: System.out.println("Logging out...");
                        return;
                    
                    default: System.out.println("Invalid choice, please try again.");
                }
            }
        }
        
        static void adminMenu(Scanner sc)
        {
            while(true)
            {
                System.out.println("\nAdmin Menu");
                System.out.println("1. View Customer Details");
                System.out.println("2. View Employee Details");
                System.out.println("3. Logout");
                
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        System.out.print("Enter Customer Name:");
                        String name = sc.nextLine();
                        Bank customer = customers.get(name);
                        if(customer != null)
                        {
                            admin.ViewCustomerDetails(customer);
                        }
                        else
                        {
                            System.out.println("Customer not found");
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter Employee ID:");
                        String id = sc.nextLine();
                        bankEmployee employee = employees.get(id);
                        if(employee != null)
                        {
                            admin.ViewEmployeeDetails(employee);
                        }
                        else
                        {
                            System.out.println("Employee not found");
                        }
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice, Please try again");
                }
            }
        }
}

class Bank
{
    private String username;
    private String password;
    private double balance;
    
    public Bank(String username,String password,double balance)
    {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public double getBalance()
    {
        return balance;
    }
    
    public void deposit(double amount)
    {
        if(amount > 0)
        {
            balance += amount;
            System.out.println("Deposited:" + balance);
        }
        else
        {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void WithDraw(double amount)
    {
        if(amount > 0 && amount <= balance)
        {
            balance -= amount;
            System.out.println("Withdrawn :" + amount);
        }
        else
        {
            System.out.println("Insufficient balance or Invalid withdrawn");
        }
    }
    
    public void AddIntrest(double rate)
    {
        double intrest = balance * rate / 100;
        balance += intrest;
        System.out.println("Intrest added:" + balance);
    }
    
    
    
}

class bankEmployee
{
    private String EmployeeId;
    private String name;
    private String position;
    public bankEmployee(String EmployeeId,String name,String position)
    {
        this.EmployeeId = EmployeeId;
        this.name = name;
        this.position = position;
    }
    
    public String getEmployeeId()
    {
        return EmployeeId;
    }
    public String getName()
    {
        return name;
    }
    public String getPosition()
    {
        return position;
    }
    
    public void viewCustomerDetails(Bank customer)
    {
        System.out.println("Customer Name:" + customer.getUsername());
        System.out.println("Account Balance:" + customer.getBalance());
    }
}

class Admin
{
    private String username;
    private String password;
    public Admin(String username,String password)
    {
        this.username = username;
        this.password = password;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    
    public void ViewCustomerDetails(Bank customer)
    {
        System.out.println("Customer Name:" + customer.getUsername());
        System.out.println("Account Balance:" + customer.getBalance());
    }
    
    public void ViewEmployeeDetails(bankEmployee employee)
    {
        System.out.println("Employee ID:" + employee.getEmployeeId());
        System.out.println("Employee Name:" + employee.getName());
        System.out.println("Position:" + employee.getPosition());
    }
}
