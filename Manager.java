import java.io.*;
import java.sql.*;
import java.util.*;

class Employee {
    Scanner sc = new Scanner(System.in);
    Connection con;
    long loginId;
    String loginPassword;

    Employee(Connection con) {
        this.con = con;
    }

    void employeelogin() throws Exception {
        System.out.println(" --> Enter your Id Number :");
        this.loginId = sc.nextLong();
        boolean b = true;
        String sql = "select * from employee";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            if (loginId == rs.getLong("Emp_Id")) {
                System.out.println(" --> Enter " + loginId + "'s PassWord :");
                this.loginPassword = sc.next();
                if (loginPassword.equals(rs.getString("Emp_Password"))) {
                    System.out.println("\n --> Login Successfully");
                    set();
                } else {
                    System.out.println(" --> PassWord Doesn't Match");
                    employeelogin();
                    return;
                }
                b = false;
                return;
            }
        }
        if (b) {
            System.out.println(" --> ID Not Found \n  Re-Enter Id ");
            employeelogin();
        }
        pst.close();

    }

    void set() throws Exception {
        int ee;
        do {
            System.out.println("\n --> 1 : Show your Data\n --> 2 : Edit\n --> 3 : Exit\n --> Enter your choice");
            ee = sc.nextInt();
            switch (ee) {
                case 1: {
                    String sql1 = "select * from employee where Emp_Id=? and Emp_Password=?";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    pst1.setLong(1, loginId);
                    pst1.setString(2, loginPassword);
                    ResultSet r = pst1.executeQuery();
                    if (r.next()) {
                       print(r);
                    }
                    pst1.close();
                    break;
                }
                case 2: {
                    System.out.println("\n --> 1 : Change Your Surname\n --> 2 : Change Your Name\n --> 3 : Change Your Age\n --> 4 : Change Your Phone No\n --> 5 : Change Your Email-Id\n --> 6 : Change Your ID Photo\n --> Enter Your Choice :");
                    int u = sc.nextInt();
                    switch (u) {
                        case 1: {
                            System.out.println(" --> Enter New Surname for Employee ID :" + loginId);
                            String uSurname = sc.next();
                            String sql1 = "update employee set Emp_Surname=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setString(1, uSurname);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Updation Failed");
                            }
                            pst1.close();
                            break;
                        }
                        case 2: {
                            System.out.println(" --> Enter New Name for Employee ID :" + loginId);
                            String uname = sc.next();
                            String sql1 = "update employee set Emp_Name=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setString(1, uname);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Updation Failed");
                            }
                            pst1.close();
                            break;
                        }
                        case 3: {
                            System.out.println(" --> Enter New Age for Employee ID :" + loginId);
                            int uage = sc.nextInt();
                            String sql1 = "update employee set Emp_Age=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setInt(1, uage);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Updation Failed");
                            }
                            pst1.close();
                            break;
                        }
                        case 4: {
                            System.out.println(" --> Enter New Phone No for Employee ID :" + loginId);
                            long uphone = sc.nextLong();
                            String s = Long.toString(uphone);
                            if (s.length() != 10) {
                                System.out.println(" --> Enter No is Not Valid");
                                break;
                            }
                            String sql1 = "update employee set Emp_Phone=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setLong(1, uphone);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Updation Failed");
                            }
                            pst1.close();
                            break;
                        }
                        case 5: {
                            System.out.println(" --> Enter New Email-Id for Employee ID :" + loginId);
                            String uEmail = sc.next();
                            String sql1 = "update employee set Emp_Email=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setString(1, uEmail);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Updation Failed");
                            }
                            pst1.close();
                            break;
                        }
                        case 6: {
                            System.out.println(" --> Enter New ID Photo path for Employee ID :" + loginId);
                            String uPhoto = sc.next();
                            FileInputStream fis = new FileInputStream(uPhoto);
                            String sql1 = "update employee set Emp_Photo=? where Emp_Id=?";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setBinaryStream(1, fis);
                            pst1.setLong(2, loginId);
                            int r = pst1.executeUpdate();
                            if (r > 0) {
                                System.out.println(" " + loginId + "'s Updatetion Successfully");
                            } else {
                                System.out.println(" " + loginId + "'s Data not found");
                            }
                            pst1.close();
                            break;
                        }
                        default: {
                            System.out.println(" -- Enter Valid Nunber");
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    System.out.println(" --> Enter valid Number");
                    break;
                }
            }
        } while (ee != 3);
        return;
    }
    void print(ResultSet r)throws Exception
    {
        System.out.println("\n -- Employee's SR NO : " + r.getInt("SR_NO")+"\n -- Employee's ID : " + r.getLong("Emp_Id")+"\n -- Employee's Password : " + r.getString("Emp_Password")+"\n -- Employee's Name : " + r.getString("Emp_Surname") + " " + r.getString("Emp_Name")+"\n -- Employee's Age : " + r.getString("Emp_Age")+"\n -- Employee's Deperment Name : " + r.getString("Emp_Dept")+"\n -- Employee's Phone No : " + r.getLong("Emp_Phone")+"\n -- Employee's Email Id : " + r.getString("Emp_Email")+"\n -- Employee's Salary : " + r.getDouble("Emp_Salary"));
}
}

public class Manager extends Employee {
    Manager(Connection con) {
        super(con);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);
        String url = "jdbc:mysql://localhost:3306/lj";
        String userName = "root";
        String pass = "";
        Connection con = DriverManager.getConnection(url, userName, pass);
        int c;
        do {
            System.out.println("\n --> 1 : Maneger\n --> 2 : Employee\n --> 3 : Exit\n --> Enter your choice");
            c = sc.nextInt();
            switch (c) {
                case 1: {
                    Manegerrlogin(con);
                    break;
                }
                case 2: {
                    Employee emp = new Employee(con);
                    emp.employeelogin();
                    break;
                }
                case 3: {
                    System.out.println("\n ---> Thank You For Visit <---\n");
                    break;
                }
                default: {
                    System.out.println("\n --> Enter valid Number");
                    break;
                }
            }
        } while (c != 3);
        con.close();
        sc.close();
    }

    static Hashtable<String, String> ht = new Hashtable<>();

    public static void Manegerrlogin(Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        ht.put("M1", "123M1");
        System.out.println(" --> Enter UserName");
        String username = sc.next();
        System.out.println(" --> Enter Password");
        String PassWord = sc.next();
        int check = 0;
        Enumeration<String> keys = ht.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.equals(username)) {
                check = 1;
                if (PassWord.equals(ht.get(key))) {
                    System.out.println("\n --> Login Successfully");
                    System.out.println("\n --> 1 : Add Maneger\n --> 2 : Menu\n --> Enter your choice");
                    int l = sc.nextInt();
                    switch (l) {
                        case 1: {
                            System.out.println(" --> Enter UserName");
                            String addus = sc.next();
                            System.out.println(" --> Enter Password");
                            String addpa = sc.next();
                            ht.put(addus, addpa);
                            System.out.println("\n --> Username And Password Add Successfully");
                            check=2;
                            break;
                        }
                        case 2: {
                            EmployeeData(con);
                            check = 3;
                            break;
                        }
                        default: {
                            System.out.println(" --> Enter valid Number");
                            break;
                        }
                    }
                }
            }
        }
        if (check == 1) {
            System.out.println("\n --> In-Valid Password");
            Manegerrlogin(con);
        } else if (check == 0) {
            System.out.println("\n --> Username Doesn't Find");
            Manegerrlogin(con);
        } else if(check==2){
            Manegerrlogin(con);
        }
        else{}
    }

    public static void EmployeeData(Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("\n --> 1 : Add Employee Data\n --> 2 : Delete Employee Data\n --> 3 : Update Employee Data\n --> 4 : Show Employee Data\n --> 5 : Employee Data Store in File\n --> 6 : Total/Average/Maximum/Minimum Employee Salary\n --> 7 : Exit\n --> Enter your choice");
            c = sc.nextInt();
            switch (c) {
                case 1: {
                    System.out.println("How many Employee's details are enter you ?");
                    int n = sc.nextInt();
                    for (int i = 1; i <= n; i++) {
                        String sql = "insert into employee(Emp_Id,Emp_Password,Emp_Surname,Emp_Name,Emp_Age,Emp_Dept,Emp_Phone,Emp_Email,Emp_Salary,Emp_Photo) values (?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                        System.out.println(" --> Insert For " + i + " Employee");
                        System.out.println("\n --> Enter Employee ID :");
                        long EId = sc.nextLong();
                        String sql1 = "select * from employee";
                        ResultSet rs = pst.executeQuery(sql1);
                        boolean b = false;
                        while (rs.next()) {
                            if (EId == rs.getLong("Emp_Id")) {
                                System.out.println("\n --> Employee ID Allready Exist \n --> Please Enter another ID");
                                b = true;
                            }
                        }
                        if (b) {
                            break;
                        }
                        System.out.println(" --> Enter Employee Password :");
                        String EPassword = sc.next();
                        System.out.println(" --> Enter Employee Sarname :");
                        String ESurname = sc.next();
                        System.out.println(" --> Enter Employee Name :");
                        String EName = sc.next();
                        System.out.println(" --> Enter Employee Age :");
                        int EAge = sc.nextInt();
                        System.out.println(" --> Enter Employee Depertment Name :");
                        String EDept = sc.next();
                        System.out.println(" --> Enter Employee Phone No :");
                        long EpNo = sc.nextLong();
                        String s = Long.toString(EpNo);
                        if (s.length() != 10) {
                            System.out.println(" --> Enter No is Not Valid");
                            System.out.println(" Please Re-Enter " + EId + "'s Data");
                            EmployeeData(con);
                        }
                        System.out.println(" --> Enter Employee Email ID :");
                        String EeId = sc.next();
                        System.out.println(" --> Enter Employee Salary :");
                        double ESal = sc.nextDouble();
                        System.out.println(" --> Enter ID Photo path :");
                        String EPhoto = sc.next();
                        FileInputStream fis = new FileInputStream(EPhoto);
                        pst.setLong(1, EId);
                        pst.setString(2, EPassword);
                        pst.setString(3, ESurname);
                        pst.setString(4, EName);
                        pst.setInt(5, EAge);
                        pst.setString(6, EDept);
                        pst.setLong(7, EpNo);
                        pst.setString(8, EeId);
                        pst.setDouble(9, ESal);
                        pst.setBinaryStream(10, fis);
                        int r = pst.executeUpdate();
                        if (r > 0) {
                            System.out.println("\n " + EId + "'s Insertion Successfully");
                        } else {
                            System.out.println("\n " + EId + "'s Insertion Failed");
                            System.out.println(" Please Re-Enter " + EId + "'s Data");
                        }
                        pst.close();
                    }
                    break;
                }
                case 2: {
                    System.out.println("\n --> Enter Employee ID Which you want to Delete :");
                    long dId = sc.nextLong();
                    String sql = "delete from employee where Emp_Id=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setLong(1, dId);
                    int r = pst.executeUpdate();
                    if (r > 0) {
                        System.out.println("\n " + dId + "'s Deletion Successfully");
                    } else {
                        System.out.println("\n " + dId + "'s Data not fount");
                    }
                    pst.close();
                    break;
                }
                case 3: {
                    System.out.println(" --> Enter Employee ID which you want to Change Data");
                    long uId = sc.nextLong();
                    String sql1="select * from employee";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs1=pst1.executeQuery();
                    int find=0;
                    while(rs1.next())
                    {
                    if(rs1.getLong("Emp_Id")==uId)
                    {
                    find=1;
                    System.out.println("\n --> 1 : Change Employee ID\n --> 2 : Change Employee Surname\n --> 3 : Change Employee Name\n --> 4 : Change Employee Age\n --> 5 : Change Employee Depatment\n --> 6 : Change Employee Phone No\n --> 7 : Change Employee Email-Id\n --> 8 : Change Employee Salary\n --> 9 : Change Employee ID Photo\n --> Enter Your Choice :");
                    int u = sc.nextInt();
                    switch (u) {
                        case 1: {
                            System.out.println(" --> Enter New ID for Employee ID :" + uId);
                            long uid = sc.nextLong();
                            String sql = "update employee set Emp_Id=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setLong(1, uid);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 2: {
                            System.out.println(" --> Enter New Surname for Employee ID :" + uId);
                            String uSurname = sc.next();
                            String sql = "update employee set Emp_Surname=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, uSurname);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 3: {
                            System.out.println(" --> Enter New Name for Employee ID :" + uId);
                            String uname = sc.next();
                            String sql = "update employee set Emp_Name=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, uname);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 4: {
                            System.out.println(" --> Enter New Age for Employee ID :" + uId);
                            int uage = sc.nextInt();
                            String sql = "update employee set Emp_Age=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setInt(1, uage);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 5: {
                            System.out.println(" --> Enter New Deparment Name for Employee ID :" + uId);
                            String uDept = sc.next();
                            String sql = "update employee set Emp_Dept=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, uDept);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 6: {
                            System.out.println(" --> Enter New Phone No for Employee ID :" + uId);
                            long uphone = sc.nextLong();
                            String s = Long.toString(uphone);
                            if (s.length() != 10) {
                                System.out.println(" --> Enter No is Not Valid");
                                break;
                            }
                            String sql = "update employee set Emp_Phone=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setLong(1, uphone);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 7: {
                            System.out.println(" --> Enter New Email-Id for Employee ID :" + uId);
                            String uEmail = sc.next();
                            String sql = "update employee set Emp_Email=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, uEmail);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 8: {
                            System.out.println(" --> Enter New Salary for Employee ID :" + uId);
                            double uSalary = sc.nextDouble();
                            String sql = "update employee set Emp_Salary=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setDouble(1, uSalary);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        case 9: {
                            System.out.println(" --> Enter New ID Photo path for Employee ID :" + uId);
                            String uPhoto = sc.next();
                            FileInputStream fis = new FileInputStream(uPhoto);
                            String sql = "update employee set Emp_Photo=? where Emp_Id=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setBinaryStream(1, fis);
                            pst.setLong(2, uId);
                            int r = pst.executeUpdate();
                            if (r > 0) {
                                System.out.println("\n " + uId + "'s Updatetion Successfully");
                            } else {
                                System.out.println("\n " + uId + "'s Updatetion Failed");
                            }
                            pst.close();
                            break;
                        }
                        default: {
                            System.out.println("\n -- Enter Valid Nunber");
                            break;
                        }
                    }
                    }
                    }
                    if(find==0){
                        System.out.println("\n " + uId + "'s Data not found");
                    }
                    break;
                }
                case 4: {
                    String sql = "select * from employee order by Emp_Id asc";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet r = pst.executeQuery();
                    while (r.next()) {
                       Employee e=new Employee(con);
                       e.print(r); 
                    }
                    pst.close();
                    break;
                }
                case 5: {
                    String sql = "select * from employee where Emp_Id=?";
                    String sql1 = "select * from employee";
                    PreparedStatement pst = con.prepareStatement(sql);
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    System.out.println(" --> Enter Employee Id Which you want to store Data");
                    long sId = sc.nextLong();
                    pst.setLong(1, sId);
                    ResultSet rs1 = pst1.executeQuery();int ab=0;
                    while(rs1.next())
                    {
                        if(rs1.getLong("Emp_Id")==sId)
                        {
                            ResultSet rs = pst.executeQuery();
                            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                            while (rs.next()) {
                            String name = rs.getString("Emp_Name");
                            File f = new File("D://EmployeeDetails/" + name);
                            f.mkdir();
                            BufferedWriter bfw = new BufferedWriter(new FileWriter(f + "//" + name + ".txt"));
                            for (int i = 1; i <= 10; i++) {
                                Clob fclob = rs.getClob(i);
                                Reader r = fclob.getCharacterStream();
                                int j;
                                bfw.write(rsmd.getColumnName(i) + " : ");
                                while ((j = r.read()) != -1) {
                                    bfw.write((char) j);
                                }
                                bfw.newLine();
                                r.close();
                            }
                            Blob b = rs.getBlob(11);
                            FileOutputStream fos = new FileOutputStream(f + "//" + name + ".jpg");
                            byte[] by = b.getBytes(1, (int) b.length());
                            fos.write(by);
                            bfw.close();
                            }
                            System.out.println("\n -->" + sId + "'s Data Stroed In D://Employee Folder");
                            ab=1;
                        }
                    }
                    if(ab==0){System.out.println("\n -->" + sId + "'s Data not found");}
                    pst.close();
                    break;
                }
                case 6: {
                    System.out.println("\n --> 1 : Total All Employee Salary\n --> 2 : Avreage Employee Salary\n --> 3 : Max Salary's Data\n --> 4 : Min Salary's Data\n --> Enter your choice");
                    int t = sc.nextInt();
                    Employee e=new Employee(con);
                    switch (t) {
                        case 1: {
                            String sql = "select sum(Emp_Salary) from employee ";
                            PreparedStatement pst = con.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            if (rs.next()) {
                                System.out.println(" --> Total All Employee Salary : " + rs.getDouble(1));
                            }
                            pst.close();
                            break;
                        }
                        case 2: {
                            String sql = "select avg(Emp_Salary) from employee ";
                            PreparedStatement pst = con.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            if (rs.next()) {
                                System.out.println(" --> Average Employee Salary : " + rs.getDouble(1));
                            }
                            pst.close();
                            break;
                        }
                        case 3: {
                            String sql = "select max(Emp_Salary) from employee ";
                            PreparedStatement pst = con.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            if (rs.next()) {
                                System.out.println(" --> Maximum Employee Salary : " + rs.getDouble(1));
                                String sql2 = "select * from employee where Emp_Salary=?";
                                PreparedStatement pst2 = con.prepareStatement(sql2);
                                pst2.setDouble(1, rs.getDouble(1));
                                ResultSet rs2 = pst2.executeQuery();
                                while (rs2.next()) {
                                   e.print(rs2);
                                }
                                pst2.close();
                            }
                            pst.close();
                            break;
                        }
                        case 4: {
                            String sql = "select min(Emp_Salary) from employee ";
                            PreparedStatement pst = con.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            if (rs.next()) {
                                System.out.println(" --> Minimum Employee Salary : " + rs.getLong(1));
                                String sql2 = "select * from employee where Emp_Salary=?";
                                PreparedStatement pst2 = con.prepareStatement(sql2);
                                pst2.setDouble(1, rs.getDouble(1));
                                ResultSet rs2 = pst2.executeQuery();
                                while (rs2.next()) {
                                    e.print(rs2);
                                }
                                pst2.close();
                            }
                            pst.close();
                            break;
                        }
                        default: {
                            System.out.println(" -- Enter Valid Number");
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    break;
                }
                default: {
                    System.out.println(" --> Enter valid Number");
                    break;
                }
            }
        } while (c != 7);
    }
}