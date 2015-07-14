
/*
 *@author: Vivek Mangla.
 **/

/*******************************************************************
Common.java is part of PROJECT_NAME.

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


abstract class Common{
	
	public static int ACC_NUM=0,ID=0,TOKEN=0;
	public static String PASS=null,TYPE=null,MEMBER=null;
	public static Login login=null;
	public static SignUp su=null;
	public static ReadWrite rw=null;
	public static FileInputStream fis=null;
	public static FileOutputStream fos=null;
	public static ObjectInputStream ois=null;
	public static ObjectOutputStream oos=null;
	public static File f=null;
	
	
}
