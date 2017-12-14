package pe.edu.cibertec.recursos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import pe.edu.cibertec.beans.Pedido;

public class ExportarExcel {

	
	public void exportarProducto(List<Pedido> lstPedido) throws IOException{
		
		if(lstPedido != null){
		
	//		String rutaArchivo = "d:/ejemplo/ejemploExcelJava.xls";
			String rutaArchivo = System.getProperty("user.home") + "/Desktop/ListaPedidos.xls";
			File
			archivoXLS = new File(rutaArchivo);
			if(archivoXLS.exists()) archivoXLS.delete();
	
			archivoXLS.createNewFile();
			Workbook libro = new HSSFWorkbook();
			
			
			FileOutputStream archivo = new FileOutputStream(archivoXLS);
			Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
	
			
			
			
			
			Row fila = hoja.createRow(0);
			
				
				
				  Cell celda = fila.createCell(0);
				   celda.setCellValue( "CODIGO PEDIDO");
				
				   Cell celda1 = fila.createCell(1);
				   celda1.setCellValue( "CODIGO CLIENTE");
				   
				   Cell celda2 = fila.createCell(2);
				   celda2.setCellValue( "NOMBRES CLIENTE");
			
				   Cell celda3 = fila.createCell(3);
				   celda3.setCellValue("REMITENTE" );
				   
				   Cell celda4 = fila.createCell(4);
				   celda4.setCellValue( "CEL REMITENTE" );
				   
				   Cell celda5 = fila.createCell(5);
				   celda5.setCellValue(" RECEPTOR" );
				   
				   Cell celda6 = fila.createCell(6);
				   celda6.setCellValue( "CEL RECEPTOR" );
				   
				   Cell celda7 = fila.createCell(7);
				   celda7.setCellValue("DIRECCION");
				   
				   Cell celda8 = fila.createCell(8);
				   celda8.setCellValue("CODIGO DE DISTRITO");
				   
				   Cell celda9 = fila.createCell(9);
				   celda9.setCellValue("DISTRITO");
				   
				   
				   Cell celda10 = fila.createCell(10);
				   celda10.setCellValue("CODIGO DISTRITO" );
				   
				   Cell celda11 = fila.createCell(11);
				   celda11.setCellValue("DISTRITO");
				   
				   
				   Cell celda12 = fila.createCell(12);
				   celda12.setCellValue("INSTRUCCIONES");
				   
				   
				   Cell celda13 = fila.createCell(13);
				   celda13.setCellValue("ESTADO");
				   
				   
				   Cell celda14 = fila.createCell(14);
				   celda14.setCellValue("KILOMETROS");
				   
				   Cell celda15 = fila.createCell(15);
				   celda15.setCellValue("PRECIO");
				   
				   Cell celda16 = fila.createCell(16);
				   celda16.setCellValue("SOLES");
				   
				   Cell celda17 = fila.createCell(17);
				   celda17.setCellValue("PAGO");
				   
				   
				   Cell celda18 = fila.createCell(18);
				   celda18.setCellValue("FECHA REGISTRO");

				
				SimpleDateFormat sdf= new SimpleDateFormat("dd 'de' MMMM 'del' yyyy hh:mm:ss");
			
					int a = 1;
					for (Pedido cell : lstPedido) {
						

						Row fil = hoja.createRow(a);
						
						
						  Cell celdaa = fil.createCell(0);
						  celdaa.setCellValue( cell.getCodigo());
						 
						
						   Cell celdaa1 = fil.createCell(1);
						   celdaa1.setCellValue( cell.getCod_cliente().getCod_cliente());
						   
						   Cell celdaa2 = fil.createCell(2);
						   celdaa2.setCellValue( cell.getCod_cliente().getNombre() + " "+ cell.getCod_cliente().getApellido() );
					
						   Cell celdaa3 = fil.createCell(3);
						   celdaa3.setCellValue( cell.getRemitente() );
						   
						   Cell celdaa4 = fil.createCell(4);
						   celdaa4.setCellValue( cell.getCelular1() );
						   
						   Cell celdaa5 = fil.createCell(5);
						   celdaa5.setCellValue( cell.getReceptor() );
						   
						   Cell celdaa6 = fil.createCell(6);
						   celdaa6.setCellValue( cell.getCelular2() );
						   
						   Cell celdaa7 = fil.createCell(7);
						   celdaa7.setCellValue( cell.getDireccion_ini() );
						   
						   Cell celdaa8 = fil.createCell(8);
						   celdaa8.setCellValue(cell.getDistrito1().getCod_distrito() );
						   
						   Cell celdaa9 = fil.createCell(9);
						   celdaa9.setCellValue(cell.getDistrito1().getDescripcion());
						   
						   
						   Cell celdaa10 = fil.createCell(10);
						   celdaa10.setCellValue(cell.getDistrito2().getCod_distrito() );
						   
						   Cell celdaa11 = fil.createCell(11);
						   celdaa11.setCellValue(cell.getDistrito2().getDescripcion());
						   
						   
						   Cell celdaa12 = fil.createCell(12);
						   celdaa12.setCellValue(cell.getInstrucciones());
						   
						   
						   Cell celdaa13 = fil.createCell(13);
						   celdaa13.setCellValue(cell.getEstado());
						   
						   
						   Cell celdaa14 = fil.createCell(14);
						   celdaa14.setCellValue(cell.getKilometros());
						   
						   Cell celdaa15 = fil.createCell(15);
						   celdaa15.setCellValue(cell.getPrecio());
						   
						   Cell celdaa16 = fil.createCell(16);
						   celdaa16.setCellValue(cell.getSoles());
						   
						   Cell celdaa17 = fil.createCell(17);
						   celdaa17.setCellValue(cell.getPago());
						   
						   
						   Cell celdaa18 = fil.createCell(18);
						   celdaa18.setCellValue(sdf.format( cell.getFechaRegistro()));
						   
					
						a++;
						
					}
					
			
		
	
			
			
			libro.write(archivo);
			
			archivo.close();
			
			
			Desktop.getDesktop().open(archivoXLS);
	

		
	}else{
		
		
		
		System.out.println("Lista nula");
	}
	
	}
	
}
