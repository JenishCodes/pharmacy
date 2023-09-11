package view;

import javax.faces.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.MyADFUtil;


public class AddButton {
    
    private int quantity;
    private int cid=101;
    private int pid=101;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public AddButton() {
        String query = "select quantity from cart where user_id="+cid+" and product_id="+pid;
        ResultSet ans = executeQuery(query);
        if(ans!= null){
            System.out.println("bi");
            try {
                quantity = ans.getInt("quantity");
            } catch (SQLException e) {
                quantity=0;
            }
        }else{
            System.out.println("Hi");
            quantity=0;
        }
    }
    
    public ResultSet executeQuery(String str){
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection conn = null;
        try
            {
              
              conn = MyADFUtil.getConnection();
              stat = conn.prepareStatement(str);
              rs = stat.executeQuery();
              return rs;
            }
            catch (Exception e)
            {
              // TODO: Add catch code
              e.printStackTrace();
            }
            MyADFUtil.showErrorMessage("Invalid");
            return null;
        
    }
    
    public void increaseQuantity(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("ci");
        String query = "update cart set quantity = quantity+1 where user_id="+cid+" and product_id="+pid;
        int temp= MyADFUtil.executeDML(query);
        int andSaveChangeSet = MyADFUtil.getDefaultDBTransaction().commitAndSaveChangeSet();
    }
    
    public void decreaseQuantity(ActionEvent actionEvent) {
        // Add event code here...
        String query = "update cart set quantity = (select quantity from cart where user_id="+cid+" and product_id="+pid +")-1 where user_id="+cid+" and product_id="+pid;
        ResultSet ans = executeQuery(query);
    }
}
