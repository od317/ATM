
package com.mycompany.mavenproject1;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;  

public class Mavenproject1 {
   static Scanner in=new Scanner(System.in);     
    public static void starting(){
    int c=0;
        System.out.println("1-login: \n2-signin: ");
        c=in.nextInt();
        switch(c){
            case(1):
                login();
                break;
            case(2):
                signin();
                break;
            default:
                System.out.println("not a choice");
                starting();
                break;
        }
    }
    public static void signin(){
    String username, pass,passc=null;
    try{
        System.out.println("");   
    File f=new File("D:\\projects\\java_projects\\ATM\\usernames.txt");    
    FileReader r=new FileReader(f);
    System.out.println("enter your user name:");
    username=in.next();
    for(int i=0;i<username.length();i++)
    {
        if((username.charAt(i)>'z'&&username.charAt(i)<'a')&&(username.charAt(i)>'Z'&&username.charAt(i)<'A'))
        {
            throw(new Exception("only charactres are allowed"));
        }
    }
    BufferedReader b=new BufferedReader(r);
    String check=b.readLine();
    while(check!=null)
    {   
        if(username.equals(check)||check.contains(username))
            throw(new Exception("user name already exist"));
        check=b.readLine();
    }
    r=new FileReader(f);
    b=new BufferedReader(r);
    char[] temp1=new char[(int)f.length()];
    b.read(temp1);
  
    b.close();
    r.close();
    System.out.println("enter your pass word:");
    pass=in.next();
    if(pass.length()<8)
    {
        throw(new Exception("pass word must be at least 8 charaters"));
    }
    System.out.println("confirm your pass word:");
    while(!pass.equals(passc))
    {int i=0;
    if(i==2)
        throw(new Exception("too much attemps"));
    passc=in.next();
    if(passc.equals(pass))
        break;
    i++;
    }
    System.out.println("enter amount of mony:");
    int mony=in.nextInt();
    if(mony<=0)
        throw(new Exception ("invalid number"));
    b=new BufferedReader(new FileReader("D:\\projects\\java_projects\\ATM\\id.txt"));
    String id=b.readLine();
        System.out.println("your id is :"+id);
    FileWriter w=new FileWriter("D:\\projects\\java_projects\\ATM\\id.txt");
    BufferedWriter bw=new BufferedWriter(w); 
    bw.append(String.valueOf(Integer.parseInt(id)+1));
    bw.close();
    w=new FileWriter("D:\\projects\\java_projects\\ATM\\usernames.txt");
    bw=new BufferedWriter(w);
    String temp=new String(temp1);
    temp+=username;
    bw.append(temp);
    bw.newLine();
    bw.append('.');
    bw.close();
    File ac=new File("D:\\projects\\java_projects\\ATM\\"+username+""+pass+".txt");
    ac.createNewFile();
    w=new FileWriter("D:\\projects\\java_projects\\ATM\\"+username+""+pass+".txt");
    bw=new BufferedWriter(w); 
    bw.append(username);
    bw.newLine();
    bw.append(pass);
    bw.newLine();
    bw.append(id);
    bw.newLine();
    bw.append(String.valueOf(mony));
    bw.newLine();
    bw.append("created acount on "+java.time.LocalDate.now());
    bw.close();
    
    }
    
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        signin();
    }
    
    }
    public static void login(){
    try{
        System.out.println("enter user name :");
        String username=in.next();
        for(int i=0;i<username.length();i++)
    {
        if((username.charAt(i)>'z'&&username.charAt(i)<'a')&&(username.charAt(i)>'Z'&&username.charAt(i)<'A'))
        {
            throw(new Exception("only charactres are allowed"));
        }
    }
        System.out.println("enter password :");
        String pass=in.next();
        String path=username+pass;
        System.out.println(path);
        File f=new File("D:\\projects\\java_projects\\ATM\\"+path+".txt");
        if(f.exists())
        {
            System.out.println("1-withdraw :\n"+"2-deposit :\n"+"3-acount status :\n");
            int c=in.nextInt();
            switch(c){
                case 1:
                    withdraw("D:\\projects\\java_projects\\ATM\\"+path+".txt");
                    break;
                case 2:
                    deposit("D:\\projects\\java_projects\\ATM\\"+path+".txt");
                    break;
                case 3:
                    status("D:\\projects\\java_projects\\ATM\\"+path+".txt");
                    break;
                default:
                    throw new Exception("not a choice");
              
                    
            }
            
        }
        else{
            throw new Exception("ac not found");
        }
        
    }catch(Exception e)   
    {
        System.out.println(e.getMessage());
        login();
    }
    }
    public static void withdraw(String path){
        try{
            FileReader r=new FileReader(path);
            BufferedReader b=new BufferedReader(r);
            for(int i=0;i<3;i++)
                b.readLine();
            r.close();
            int mony=Integer.parseInt(b.readLine());
            System.out.println(mony);
            System.out.println("enter  amount of mony");
            int amount=in.nextInt();
            if(amount<=0||amount>mony)
                throw new Exception("invalid amount");
            else
                mony-=amount;
            int i=1;
            File f1=new File(path);
            File f2=new File("D:\\projects\\java_projects\\ATM\\output.txt");
            Files.copy(f1.toPath(), f2.toPath());
            r=new FileReader("D:\\projects\\java_projects\\ATM\\output.txt");
            b=new BufferedReader(r);
            FileWriter w=new FileWriter(path);
            BufferedWriter bw=new BufferedWriter(w);
            String check=b.readLine();
            while(check!=null)
            {   
                if(i==4){
                bw.append(String.valueOf(mony));
                }else
                bw.append(check);
                bw.newLine();
                check=b.readLine();
                i++;
            }
                bw.append(java.time.LocalDate.now()+" withdrawed :"+amount+"$");
                bw.newLine();
            r.close();
            bw.close();
            f2.delete();
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            withdraw(path);
        }
    }
    public static void deposit(String path){
       try{
            FileReader r=new FileReader(path);
            BufferedReader b=new BufferedReader(r);
            for(int i=0;i<3;i++)
                b.readLine();
            r.close();
            int mony=Integer.parseInt(b.readLine());
            System.out.println(mony);
            System.out.println("enter  amount of mony");
            int amount=in.nextInt();
            if(amount<=0)
                throw new Exception("invalid amount");
            else
                mony+=amount;
            int i=1;
            File f1=new File(path);
            File f2=new File("D:\\projects\\java_projects\\ATM\\output.txt");
            Files.copy(f1.toPath(), f2.toPath());
            r=new FileReader("D:\\projects\\java_projects\\ATM\\output.txt");
            b=new BufferedReader(r);
            FileWriter w=new FileWriter(path);
            BufferedWriter bw=new BufferedWriter(w);
            String check=b.readLine();
            while(check!=null)
            {   
                if(i==4){
                bw.append(String.valueOf(mony));
                }else
                bw.append(check);
                bw.newLine();
                check=b.readLine();
                i++;
            }
                bw.append(java.time.LocalDate.now()+" deposit :"+amount+"$");
                bw.newLine();
            r.close();
            bw.close();
            f2.delete();
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            withdraw(path);
        }
    }
    public static void status(String path){
        try{
        FileReader r=new FileReader(path);
        BufferedReader br=new BufferedReader(r);
        int i=1;
        String check=br.readLine();
        while(check!=null)
        {   
            if(i>=5)
            System.out.println(check);
            check=br.readLine();
            i++;
        }
        r.close();
    }catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    }
    public static void main(String[] args){
        while(true)
        starting();
    
    }
}
