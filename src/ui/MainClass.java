package ui;

import jdbc.Database;

public class MainClass {
	//public static ArrayList<Book> books = new ArrayList<Book>();
	//public static ArrayList<User> users = new ArrayList<User>();
	//public static Formatter formatter= new Formatter(System.out);
	public static Database db = new Database();
	public static MainPage mp = new MainPage();
	public static ForgetPwd fp = new ForgetPwd();
	public static LoginPage lp = new LoginPage();
	public static SignUp su = new SignUp();
	
	public MainClass() {
		mp.jf.setVisible(false);
		lp.jf.setVisible(true);
		fp.jf.setVisible(false);
		su.jf.setVisible(false);
	}
	
	public static void main(String []args) {
		MainClass mc = new MainClass();
		
		//mc.db.dbUpdate("insert into books values('boom','zhangle','0.1','1000003',1)");
		//ForgetPwdPage fp = new ForgetPwdPage();
		//SignUpPage sup = new SignUpPage();
		
		/*JFrame f=new JFrame();
		Sidebar sbar=new Sidebar();
		f.getContentPane().add(sbar.scrollPane,BorderLayout.WEST);
		f.setVisible(true);
		f.pack();
		f.setSize(400, 400);
		*/
		/*System.out.println("=======Welcome to the Book Manager System!=======");
		mc.showMenu();
		Scanner sn= new Scanner(System.in);
		
		while(true) {
			int menuChoice;
			System.out.print("Please enter you choice:  ");
			
			menuChoice=sn.nextInt();
			mc.doOperation(menuChoice);
		}
		 */
	}

}


