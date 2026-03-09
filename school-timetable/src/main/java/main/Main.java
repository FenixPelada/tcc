package main;

import telaPrincipal.TelaPrincipal;

public class Main {
	//ExcelDAO excelDAO;
	//TelaPrincipal telaPrincipal;
	
	public Main(){
		//this.excelDAO = new ExcelDAO();
		//telaPrincipal = new TelaPrincipal();
	}
	public static void main(String[] args) /*throws IOException */{
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		
		telaPrincipal.setVisible(true);
		/*ExcelDAO excelDAO = new ExcelDAO();
		excelDAO.ler();*/
	}
}