
/*
 *@author: Vivek Mangla.
 **/

/*******************************************************************
ReadWrite.java is part of PROJECT_NAME.

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
import java.io.ObjectInputStream;

public class ReadWrite extends Common{

	
	public AccountDetail[] readData(String fileName){
		f=new File("Data.dat");
		if(!f.exists()){return null;}
		AccountDetail ad[]=null;
		try{
			f=new File(fileName);
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis);
			ad=(AccountDetail[])ois.readObject();
			
		}catch(Exception er){er.printStackTrace();}
		
		return ad;
	}
	
	public void write(AccountDetail[] a,String fileName){
		f=new File(fileName);
		try{
			if(!f.exists()){f.createNewFile();}
			
		}catch(Exception er){
			er.printStackTrace();
		}
		
	}
	
	
}
