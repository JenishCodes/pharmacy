package view;

import java.io.Serializable;

import java.sql.ResultSet;

import java.sql.SQLException;


import java.util.List;

import javax.faces.event.ActionEvent;

import model.AppModuleImpl;


import oracle.adf.model.generic.ListBinding;

import oracle.adf.view.rich.component.rich.RichQuery;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import view.MyADFUtil;

public class Login implements Serializable {
    private int cust_id=101;
    private String email;
    private String password;
    

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCust_id(Integer cust_id) {
        this.cust_id = cust_id;
    }

    public Integer getCust_id() {
        return cust_id;
    }

    public Login() {
    }
    
    
    

    public void doLogin(ActionEvent actionEvent) {
        
        // Add event code here...
        String query = "select id from customers where email='"+email+"' and password='"+password+"'";
        ResultSet ans = MyADFUtil.executeQuery(query);
        System.out.println(query);
            if(ans!= null){
                System.out.println("bi");
                try {
                    ans.next();
                    cust_id = ans.getInt("id");
                    BindingContainer bindings = MyADFUtil.getBindings();
                    //List operationBindings = bindings.getOperationBindings();
                    //System.out.println(operationBindings);
                    OperationBinding ob = bindings.getOperationBinding("customfn");
                    System.out.println(ob);
                    ob.getParamsMap().put("id",cust_id);
                    ob.execute();
                    
                } catch (SQLException e) {
                    System.out.println(e);
                    cust_id=0;
                }
                
            }else{
                System.out.println("Hi");
                cust_id=0;
            }
    }

}
