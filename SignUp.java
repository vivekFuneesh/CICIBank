
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SignUp extends Common{
	
	boolean write=false,duplicate=false,present=true;
	File f=null;
	FileOutputStream fos=null;
	FileInputStream fis=null;
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	AccountDetail[] ad1=null,ad=null;
	int index=-1;
	
	
	
	void reset(){
		present=true;
		fos=null;
		fis=null;
		ois=null;
		oos=null;
		ad=null;
		ad1=null;
		duplicate=false;index=-1;
		
	}
	
	void read(){
		reset();
		f=new File("Data.dat");
		try{
			if(!f.exists()){present=false;f.createNewFile();return;}
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis);
			ad=(AccountDetail[])ois.readObject();
			if(ad==null){
				return;//File is Present BUT Somebody Deleted Data Completely.
			}
			if(write)ad1=new AccountDetail[ad.length+1];
			for(int i=0;i<ad1.length-1;i++){
				if((ad[i].Acc_num==ACC_NUM)&&(ad[i].pass==PASS)&&(ad[i].type==TYPE)&&(ad[i].mem==MEMBER)){
					duplicate=true;
					break;
				}
				else if((ad[i].Acc_num>ACC_NUM)){index=i;break;}
				//Adding Details to new ad1 ObjectArray.
				if(write)
				ad1[i]=new AccountDetail(ad[i].Acc_num,ad[i].pass,ad[i].type,ad[i].mem);
			}
			
		}catch(Exception er){er.printStackTrace();System.exit(0);}
		
	}
	
	void enterDetails(){
		if(ACC_NUM<0){
			System.out.println("\n\n--Negative_Account_Number_IS_Not_Allowed_--\n\n");
			return;
		}
		write=true;
		read();
		try{
			if(present==false){
				AccountDetail[] ad=new AccountDetail[1];
				ad[0]=new AccountDetail(ACC_NUM,PASS,TYPE,MEMBER);
				fos=new FileOutputStream(f);
				oos=new ObjectOutputStream(fos);
				oos.writeObject(ad);
				close(fis,ois,fos,oos);
			}
			else{
				fis=new FileInputStream(f);
				ois=new ObjectInputStream(fis);
				if(ad==null){//File is Present BUT Somebody Deleted Data Completely.
					ad=null;
					close(fis,ois,fos,oos);
					ad=new AccountDetail[1];
					ad[0]=new AccountDetail(ACC_NUM,PASS,TYPE,MEMBER);
					fos=new FileOutputStream(f);
					oos=new ObjectOutputStream(fos);
					oos.writeObject(ad);
					close(fis,ois,fos,oos);
				}
				else{
					
					if(duplicate){
						System.out.println("\n\n--YOu_Are_Already_A_User_Of_This_Bank--\n\n");
						ad1=null;
						close(fis,ois,fos,oos);
					}
					else{
						if(index!=-1){
							ad1[index]=new AccountDetail(ACC_NUM,PASS,TYPE,MEMBER);
							for(int i=index+1;i<ad1.length;i++){
								ad1[i]=new AccountDetail(ad[i].Acc_num,ad[i].pass,ad[i].type,ad[i].mem);
							}
						}
						else{
							ad1[ad1.length-1]=new AccountDetail(ACC_NUM,PASS,TYPE,MEMBER);
						}
						close(fis,ois,fos,oos);
						fos=new FileOutputStream(f);
						oos=new ObjectOutputStream(fos);
						oos.writeObject(ad1);
						close(fis,ois,fos,oos);
				    
				    }
					
				}
			    
			}
		}catch(Exception er){
			er.printStackTrace();
			try{
				if(oos!=null)oos.close();
				if(fos!=null)fos.close();
			}catch(Exception ere){ere.printStackTrace();}
		}
		finally{
			oos=null;
			fos=null;write=false;
		}
	}
	
	void close(FileInputStream fis,ObjectInputStream ois,FileOutputStream fos,ObjectOutputStream oos){
		if(fis!=null){
			try{fis.close();fis=null;}catch(IOException er){}
			}
		if(fos!=null){
			try{fos.close();fos=null;}catch(IOException er){}
		}
		if(ois!=null){	
			try{ois.close();ois=null;}catch(IOException er){}
		}
		if(oos!=null){
			try{oos.close();oos=null;}catch(IOException er){}
		}
		fis=null;fos=null;ois=null;oos=null;
	}
	
}
