
/*
 *@author: Vivek Mangla.
 **/

/*******************************************************************
Login.java is part of PROJECT_NAME.

    ProductCatalogue is free TYPE_OF_SOFTWARE: you can redistribute it    and/or modify
    it under the terms of the GNU General Public License as published     by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    ProductCatalogue is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with ProductCatalogue.  If not, see <http://www.gnu.org/licenses/>.
*******************************************************************
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Login extends  Common {

	static Scanner s;
	//String member="";
	
	

	public boolean login() {
		if(s!=null)s=new Scanner(System.in);
		System.out.println("Enter Account Number");
		ACC_NUM=s.nextInt();
		System.out.println("Enter Password");
		PASS=s.next();
		System.out.println("Enter Account Type");
		TYPE=s.next();
		if(checkDetails()==true){
			System.out.println("Enter Wether You Are A \n------- m for MANAGER_\n------- o for _OFFICER/CLERK_\n"
					+ "------- r for REST ");
			String mem=s.next();
			if(MEMBER==mem)
				{
					return true;//grantLogin(mem);
				}
			else System.out.println("----\n\nFATAL_ERROR_YOU_ROOTKIT------\n\n");
		}
		
		return false;
	}

	
	public boolean checkDetails(){
		File f=new File("Data.dat");
		if(!f.exists()){return false;}
		ObjectInputStream  ois=null;
		AccountDetail ad[]=null;
		FileInputStream fis=null;
		try{
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis);
			ad=(AccountDetail[])ois.readObject();
			for(int i=0;i<ad.length;i++){
				if((ad[i].type==TYPE)&&(ad[i].Acc_num==ACC_NUM)&&(ad[i].pass==PASS)){MEMBER=ad[i].mem;return true;}
			}
			ois.close();
			fis.close();
		}catch(Exception er){
			try{
				if(ois!=null)ois.close();
				if(fis!=null)fis.close();
				}catch(Exception err){err.printStackTrace();}
			System.out.println(er);
		}
		finally{
			ois=null;fis=null;
		}
		
		return false;
				
	}
	
	
	
	
	
	public void closeAccount(){
		//--Reading current Object and Storing appropriate Data--.
		AccountDetail[] ad1=null;
		try{
			if(rw==null)rw=new ReadWrite();
			AccountDetail[] ad=rw.readData("Data.dat");
			ad1=new AccountDetail[ad.length-1];
			int j=0;
			for(int i=0;i<ad.length;i++){
				if((ad[i].type==TYPE)||(ad[i].Acc_num==ACC_NUM)||(ad[i].pass==PASS)||(ad[i].mem==MEMBER)){
					ad1[j]=new AccountDetail(ad[i].Acc_num,ad[i].pass,ad[i].type,ad[i].mem);
					j++;
				}
			}
			
			ois.close();
			fis.close();
		}catch(Exception er){
			try{
				if(ois!=null)ois.close();
				if(fis!=null)fis.close();
				}catch(Exception err){err.printStackTrace();}
			System.out.println(er);
		}
		finally{
			ois=null;fis=null;
		}
		//-----------Now Inserting new Object-----------.
		f=new File("Data.dat");
		if(!f.exists()){return ;}
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try{
			fos=new FileOutputStream(f);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(ad1);//WRITING
			oos.close();
			fos.close();
		}catch(Exception er){
			try{
				if(oos!=null)oos.close();if(fos!=null)fos.close();
			}catch(Exception err){}
		}
		finally{
			fos=null;oos=null;
		}
		
	}
	
	
}

