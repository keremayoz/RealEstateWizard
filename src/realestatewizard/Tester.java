/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestatewizard;

/**
 *
 * @author Emre
 */
public class Tester
{
    
    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector();
        
        db.removeUser(db.getUser(db.findIdMail("nurefsanmusevitoglu@hotmail.com")));
        db.removeUser(db.getUser(db.findIdMail("yasinbalcanci@hotmail.com")));
    }
    
}
