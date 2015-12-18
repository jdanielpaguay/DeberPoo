package erick.hiberante.Main;

import java.awt.Component;
import java.util.List;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import erick.hibernate.controller.ClienteController;
import erick.hibernate.persona.Taller;
import erick.hibernate.util.HibernateUtil;



public class main {

	public static void main(String[] args) 
	{
	
	
	int opcion;
	do{
		
		Scanner sc = new Scanner(System.in);
		System.out.print("SELECCIONE UNA OPCION: \n");
		System.out.print("1. MOSTRAR REGISTROS: \n");
		System.out.print("2. INGRESAR NUEVO REGISTRO: \n");
		System.out.print("3. CLOSE PROGRAM: ");
		opcion = sc.nextInt();
		switch (opcion)
		{
		case 1: buscar();
		        break;
		case 2: ingresar();
				break;
		case 3: System.out.println("Close Data Base");
		 }
		}while (opcion != 3);
	
	//JOptionPane.showMessageDialog(null, "Elija una opcion "+"\n"+"1. buscar por ciudad"+"\n"+"2. consultar todos"+"\n"+"3. Salir");
	}
	public static void buscar()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Taller> result = (List<Taller>)session.createQuery("from Taller").list();
        session.getTransaction().commit();
        
		for(int i=0; i<result.size();i++)
		{
			 String uno = "Codigo: "+" ' "+result.get(i).getCodigo()+" ' \n"
					 +" Cedula: "+" ' "+result.get(i).getCedula()+" ' \n"
					 +" Nombre: "+" ' "+result.get(i).getNombre()+" ' \n"
					 +" Apellido: "+" ' "+result.get(i).getApellido()+" ' ";
			 System.out.println("******");
			 System.out.println(uno);
        	System.out.println("******");

		}
	}
	public static void ingresar()
	{		
		ApplicationContext context=new GenericXmlApplicationContext("appContext.xml");
		ClienteController clienteController=context.getBean("clienteController",ClienteController.class);
		Taller nuevoDato = new Taller(2,976543652,"Daniel","Paguay");
		clienteController.ingresar(nuevoDato);
		
	}
	
	
}
