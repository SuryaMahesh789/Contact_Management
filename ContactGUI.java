
package unit4;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

class MyContactException extends Exception

{
public MyContactException(String str)
{
	
	System.out.println(str+" EXCEPTION....");
}

}


class contact 
{
public String name;
public String mobilenum;
public String type;

public contact()
{
	this.mobilenum="";
	this.name="";
	this.type="";
	}

public contact(String n,String mn,String t)
{
	this.mobilenum=mn;
	this.name=n;
	this.type=t;
	}

	public Boolean match(contact c) 
	{
		Boolean found=false;
	if(mobilenum.equals(c.mobilenum) && name.equals(c.name) && type.equals(c.type))
	{
		found=true;
	}
	else if(mobilenum.equals(c.mobilenum) && (!name.equals(c.name) && type.equals(c.type) ))
	{
		found=true;
		System.out.println(" SIMILAR CONTACT FOUND BUT WITH DIFFERENT NAME ");
		System.out.println(" U ARE SEARCHING FOR "+c.name+" BUT IN RECORDS "+" CONTACT SAVED AS "+name);
	}
	
	else if(mobilenum.equals(c.mobilenum) && name.equals(c.name) && (!type.equals(c.type)))
	{
		found=true;
		System.out.println(" SIMILAR CONTACT FOUND BUT WITH DIFFERENT TYPE ");
		System.out.println(" U ARE SEARCHING FOR "+c.type+" BUT IN RECORDS "+" CONTACT SAVED AS "+type);
	}
	else if(mobilenum.equals(c.mobilenum) && (!name.equals(c.name)) && (!type.equals(c.type)))
	{
		found= true;
		System.out.println("  CONTACT ALREADY EXISTS BUT WITH DIFFERENT TYPE,NAME,MODIFYING type ");
		System.out.println(" U ARE SEARCHING FOR "+c.type+" BUT IN RECORDS "+" CONTACT SAVED AS "+name+" "+type);
	}
	else
	{
		found =false;
	}
		return found;
		
	}
	
	public String show()
	{
		return "NAME :"+name+"  NUMBER : "+mobilenum+"  TYPE :"+type ;
	}
}

class contactManagement  implements ActionListener 
{
	
	JFrame jf;
	JTextField jtf1 ,jtf2;
	JPanel jp;
	JRadioButton jr1,jr2;
	ButtonGroup bg;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	JLabel jll,jll2,jll3,jll4;
	JTextArea jta,jta2,jta3;

	contact c=new contact();
	ArrayList <contact>l=new ArrayList<contact>();
	
	
	contact cc=new contact();
	public contactManagement()
	{
		contact c3=new contact("DADDY","9908586991","FAMILY");
		contact c2=new contact("MY","789370513","FAMILY");
		contact c1=new contact("SAI","9398489312","OFFICIAL");
	l.add(c1);
	l.add(c2);
	l.add(c3);
	
		jf=new JFrame();
		jf.setVisible(true);
		jf.setSize(500,500);
		jf.setLayout(new FlowLayout());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jl1 =new JLabel(" NAME ");
		jf.add(jl1);
		
		jtf1=new JTextField(15);
		jf.add(jtf1);
		
		JLabel jl2 =new JLabel(" NUMBER ");
		jf.add(jl2);
		jtf2=new JTextField(10);
		
		jf.add(jtf2);
		JLabel jl3 =new JLabel(" TYPE OF CONTACT ");
		jf.add(jl3);
		
	
		bg=new ButtonGroup();
		jr1=new JRadioButton("FAMILY");
		jr2=new JRadioButton("OFFICIAL");
		bg.add(jr1);
		bg.add(jr2);
		
		jf.add(jr1);
		jf.add(jr2);
		
	jb1=new JButton(" ADD TO CONTACTS ");
	jf.add(jb1);
	
	jb2=new JButton(" SEARCH CONTACT  ");
	jf.add(jb2);
	
	jb3=new JButton(" DISPLAY CONTACT ");
	jf.add(jb3);
	
	 jb4=new JButton("CLEAR");
	    jf.add(jb4);
	    jb4.addActionListener(this);
	    
	jb1.addActionListener(this);
	jb2.addActionListener(this);
	jb3.addActionListener(this);
	
	jll=new JLabel("");
	jf.add(jll);
	
	jll2=new JLabel("");
	jf.add(jll2);
	
	jta=new JTextArea();
	jf.add(jta);
		
	}
	

public void actionPerformed(ActionEvent e) 
{
	String s6=e.getActionCommand();
	String Name=jtf1.getText();
	String Num=jtf2.getText();
	String str="";
	
	int l1=Name.length();
	int l2=Num.length();
	int l3=str.length();
	
	
	jll.setText("");
	jll2.setText("");
	jta.setText("");
	
	if(jr1.isSelected())
	{
		str=jr1.getActionCommand();
	}
	else
	{
			str=jr2.getActionCommand();
		
	}
	
	contact cob=new contact(Name,Num,str);
	if(s6.equals(" ADD TO CONTACTS "))
	{
	if(l1!=0 && l2!=0)
	{

		
		int siz=l.size();
		Boolean found=false;
		
		for(int i=0;i<siz;i++)
		{
			
		if(	l.get(i).match(cob))
		{
			found =true;
			
			if(Num.equals(cob.mobilenum) && Name.equals(cob.name) && str.equals(cob.type))
			{
			
				System.out.println(" CONTACT ALREADY EXISTS");
				jll.setText(" CONTACT ALREADY EXISTS .... ");
				jta.setText(l.get(i).show());
				
			}
			else 
			{
				jll.setText(" MODIFYING EXISTING CONTACT .... ");
				jll2.setText("SIMILAR CONTACT FOUND ");
				jta.setText(l.get(i).show());
				l.add(cob);
				l.remove(i);
			
				System.out.println(" SEARCH CONTACT FOUND BUT WITH DIFFERENT NAME , MODIFYING NAME");
				System.out.println(" U ARE SEARCHING FOR "+cob.name+" BUT IN RECORDS "+" CONTACT SAVED AS "+Name+" "+str);
			}
			i=siz;
		}
		
		
		}
		
		if(found==false)
		{
		jll.setText(" ADDING CONTACT.... ");
		l.add(cob);
		}
		
	}
	
	else
	{
		jll.setText(" FIELDS MUST NOT BE EMPTY......");
		jta.setText(" FILL NAME , NUMBER FIELD AND SELECT APPROPRIATE TYPE OF CONTACT");
	}
			
	}
	
	 if(s6.equals(" SEARCH CONTACT  "))
	{
		 
		 if(l1!=0 && l2!=0)
			{
				jll.setText(" SEARCHING CONTACT.... ");
				String searchNum=jtf2.getText();
				int siz=l.size();
				Boolean found=false;
				for(int i=0;i<siz;i++)
				{
					
				if(	l.get(i).match(cob))
				{
					found =true;
					
					jll.setText("SEARCH RESULTS ");
					
					jta.setText(l.get(i).show());
					
					
					if(Num.equals(cob.mobilenum) && Name.equals(cob.name) && str.equals(cob.type))
					{
					
						System.out.println(" CONTACT FOUND ");
						jll2.setText(" CONTACT FOUND ");
					}
					else if(Num.equals(cob.mobilenum) && (!Name.equals(cob.name)&& str.equals(cob.type) ))
					{
						jll2.setText("SIMILAR CONTACT FOUND ");
						System.out.println(" SEARCH CONTACT FOUND BUT WITH DIFFERENT NAME , MODIFYING NAME");
						System.out.println(" U ARE SEARCHING FOR "+cob.name+" BUT IN RECORDS "+" CONTACT SAVED AS "+Name);
					}
					
					else if(Num.equals(cob.mobilenum) && Name.equals(cob.name) && (!str.equals(cob.type)))
					{
						jll2.setText("SIMILAR CONTACT FOUND ");
						System.out.println("  CONTACT ALREADY EXISTS BUT WITH DIFFERENT TYPE,MODIFYING type ");
						System.out.println(" U ARE SEARCHING FOR "+c.type+" BUT IN RECORDS "+" CONTACT SAVED AS "+str);
					}
					else if(Num.equals(cob.mobilenum) &&(! Name.equals(cob.name)) && (!str.equals(cob.type)))
					{
						jll2.setText("SIMILAR CONTACT FOUND ");
						System.out.println("  CONTACT ALREADY EXISTS BUT WITH DIFFERENT TYPE,NAME,MODIFYING type ");
						System.out.println(" U ARE SEARCHING FOR "+c.type+" BUT IN RECORDS "+" CONTACT SAVED AS "+Name+" "+str);
					}
					i=siz;
				}
				
					
				}
				
				if(found==false)
				{
					try
					{
						jll.setText("SEARCH UNSUCCESSFULL ");
						jll2.setText(" CONTACT NOT FOUND.... ");
					throw new MyContactException(" CONTACT NOT FOUND ");
					}
					catch(Exception ee)
					{
						System.out.println(ee);
					}

				}
			
				
			}
			
			else
			{
				jll.setText(" FIELDS MUST NOT BE EMPTY......");
				jta.setText(" FILL NAME , NUMBER FIELD AND SELECT APPROPRIATE TYPE OF CONTACT");
			}
		
	
		
	}
	 if(s6.equals(" DISPLAY CONTACT "))
	{
	
		 jll.setText(" DISPLAYING CONTACT.... ");
			String searchNum=jtf2.getText();
			int siz=l.size();
		String ds="";
			for(int i=0;i<siz;i++)
			{
			ds+=l.get(i).show();
			ds+="\n";
			}
			jta.setText(ds);
		
	}
	 
	 if(s6.equals("CLEAR"))
		{
			jtf1.setText("");
			jtf2.setText("");
			jll.setText("");
			jta.setText("");
		}
	
}

}


public class ContactGUI 
{

public static void main(String[] args) 
    {
	contactManagement cm=new contactManagement();
	}
}