/**
 *
 * @author Rishabh Nigam
 *here various operations for the trivial contact list are implemented
 
 */

package contact_list;
import java.io.*;


//saple contact class that will generate the Linked list
class Contacts{
    String contact_name;
    String contact_no;
    Contacts next;
    Contacts(){
        contact_no="";
        
        contact_name="";
        next=null;
    }
    Contacts(String name,String number,Contacts c){
        contact_name=name;
        contact_no=number;
        next=c;
        
    }
    public void setName(String x){
        contact_name=x;
    }
    public String getName(){
       return contact_name;
    }
public void setNumber(String y){
    contact_no=y;
}
public void setLink(Contacts z){
    next=z;
}
public Contacts getLink(){
    return next;
}
    

}//class Contacts ends here


public class Contact_List {

    
    Contacts ListStart;
    Contacts endPointer;
    static int totalContact=0;
    
    
    
    
    
    public static void main(String[] args)throws IOException {
        Contact_List obj=new Contact_List();
         obj.loadContacts();
        BufferedReader Br=new BufferedReader(new InputStreamReader(System.in));
        
        
        int sw=0;
        while(true){
            System.out.println("Enter the choice"+"\n "
                    + "1:Add to contacts"+"\n "
                    + "2:Search Contact"+"\n "
                    +"3:Read all contacts "+"\n"
                    + "4:Delete Contact "+"\n"
                    +"5:Exit the process ");
            switch(Integer.parseInt(Br.readLine())){
                
                case 1: obj.addToContacts();
                    break;
                    
                case 2: obj.searchContact();
                    break;
                    
                case 3: obj.readAllContacts();
                    break;
                case 4: obj.deleteContact();
                    break;
                case 5:sw=1;
                    break;
                default:System.out.println("Sorry !!!wrong choice");
                    sw=2;
            }
            if(sw==2){
                continue;//forces next iteration of whie loop
                }
            if(sw==1)
                break;
            
            System.out.println("Press 1 to exit and any other key to continue");
            if((Br.readLine()).equals("1")){
                break;
            }
            
            
        }//while loop ends
       
        PrintWriter pw=null;
       try{ 
        FileWriter fout=new FileWriter("names.txt");
        BufferedWriter bout=new BufferedWriter(fout);
        pw=new PrintWriter(bout);
        
        Contacts wptr=obj.ListStart;
        String wtext;
        while(!((wtext=wptr.contact_name)).isEmpty()){
           
            pw.println(wtext);
            pw.println(wptr.contact_no);
            wptr=wptr.next;
          
             
            
            }  // the data has been loaded to the file--ready to exit
       }catch(Exception e){
           e.printStackTrace();
       } 
        finally{
        pw.close();
       }
    }//main method ends
    
    
    
    public void addToContacts()throws IOException{
        BufferedReader Br=new BufferedReader(new InputStreamReader(System.in));
        String cName,cNumber;
        System.out.println("Enter the Contact name");//enter the information
        cName=Br.readLine();
        System.out.println("Enter the contact number");
        cNumber=Br.readLine();
        int flag=-1;
        Contacts marker;
        
        if(totalContact==0){//still ListStart contains an address of an empty object
            ListStart.contact_name=cName;
           
            ListStart.contact_no=cNumber;
            ListStart.next=new Contacts();
            endPointer=ListStart.next;
            totalContact++;
            
        }else if(totalContact==1){
            if(cName.compareTo(ListStart.contact_name)>0){
                endPointer.contact_name=cName;
                endPointer.contact_no=cNumber;
                endPointer.next=new Contacts();
                endPointer=endPointer.next;
                totalContact++;
            }else{
                marker=new Contacts(cName,cNumber,ListStart);
                ListStart=marker;
                endPointer=ListStart.next;
                totalContact++;
            }
          }                     else{
                                                        //here will be three cases formed...add at starting... or in middle... or at the end..            
                                       if(cName.compareTo(ListStart.contact_name)<0){
                                          marker=new Contacts(cName,cNumber,ListStart);
                                           ListStart=marker;     
                                            totalContact++;      //Adding at start
                                         }else 
                                                 {
                                                      marker=ListStart;
                                                      while(((marker.next.contact_name.length())!=0)){
                                                    if(cName.compareTo(marker.contact_name)>0 && cName.compareTo(marker.next.contact_name)<0){
                                                      System.out.println("mai andar hoon");
                                                           Contacts midNode=new Contacts(cName,cNumber,marker.next);
                                                           marker.next=midNode;
                                                       flag=1;//Adding in the middle
                                                       totalContact++;
                                                   }
                                                      marker=marker.next;         
                                                   }
                                           }
            if(flag==-1){
                endPointer.contact_name=cName;
                endPointer.contact_no=cNumber;
                endPointer.next=new Contacts();
                endPointer=endPointer.next;      //Adding at the end
                totalContact++;
            }
                   
        }//else clause ends
           
    }//method Add to contacts ends here
    
    
    
    public void searchContact()throws IOException{
        
        
        Contacts sptr=ListStart;
        BufferedReader Bs=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter name");
        String search=Bs.readLine();
           int flag=-1;
        while(!(sptr.contact_name==null)){
            if(search.equalsIgnoreCase(sptr.contact_name)){
                System.out.print(sptr.contact_name);
                System.out.println(":    "+sptr.contact_no);
               flag=1;
                break;
            }
            sptr=sptr.next;
        
    }//while-search ends
        if(flag==-1){
            System.out.println("No such Contact available");
        }
        
        
    }
    
    public void readAllContacts(){
        //this method will read and display all the contacts stored in linked list
       Contacts rptr=ListStart;int flag=-1;
       System.out.println("total contacts avilable=="+totalContact);
        while(!((rptr.contact_name)).isEmpty()){
            System.out.print(rptr.contact_name);
            System.out.println(":       "+rptr.contact_no);
            rptr=rptr.next;
            flag=1;
        }
        if(flag==-1){
            System.out.println("Sorry there are no contacts to read");
        }
        
    }
   
    
    
    public void deleteContact()throws IOException{
        BufferedReader Bd=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the contact name you want to delete");
        String dName=Bd.readLine();
        int flag=-1;
        Contacts dmark,pdmark=null;
       try{
        dmark=ListStart;
        while(dmark!=null){
            if(dName.equalsIgnoreCase(dmark.contact_name)){
                 pdmark.next=dmark.next;  //the element deleted
                 totalContact--;
                 flag=1;
                break;//since pmark and dmark are just the Contacts variable,the element won't be deleted untill next variable is changed
            }
            
            pdmark=dmark;
            dmark=dmark.next;
        }
    
       }
       catch(Exception e){
           e.printStackTrace();
       }
       if(flag==-1){
           System.out.println("Sorry no such element is found");
       }else{
           System.out.println("The element :"+dName+"is deleted");
       }
       
    }
    
    
    
    
    
    public void loadContacts()throws IOException,NullPointerException{
        try{
                        File file = new File("names.txt");
                  
                 FileReader fin=new FileReader(file);
                  BufferedReader bin=new BufferedReader(fin);
                         String text;
                     endPointer=new Contacts();
                       ListStart=endPointer;
                         
                       if((file.length())!=0){
                         
                              while(!((text=bin.readLine())==null)){    //testing weather the entered String is null or not.
                              endPointer.setName(text);
                               endPointer.setNumber(bin.readLine());
                               endPointer.next=new Contacts();
            
                                   totalContact++;
                               endPointer=endPointer.next;//last wali node ka next null point karega
                              
                              }     
                              
                        }//if ends
                          else{
                          System.out.println("no previous contacts to read");
                          endPointer=ListStart;
                          }
       
        }
        catch(IOException e){
           
       System.out.println("IO-error");
        }
        catch(NullPointerException e){
            System.out.println("Null Pointer Error");
        }
            
    } //all the data is been loaded  

}//class ends




