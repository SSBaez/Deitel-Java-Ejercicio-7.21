package com.ssbaez.ejercicio721;

import java.util.Scanner;

public class GraficoTortuga {
	
	//La tortuga sostiene un bolígrafo en una de dos posiciones, arriba (1) o abajo (2).
	//Mientras el bolígrafo está abajo (2), la tortuga va trazando figuras a medida que se va moviendo
	//Mientras el bolígrafo está arriba (1), la tortuga se mueve alrededor libremente, sin trazar nada
	//La tortuga siempre empieza en la posición (0, 0) del piso, con su bolígrafo hacia arriba
	
	//Va a servir para determinar hacia que direccion se esta moviendo a traves del arreglo
	public static enum Estado {DERECHA, IZQUIERDA, ARRIBA, ABAJO};
	
	//Se utilizan dos arreglos de 20x20 uno para las 0's y 1's y el otro para los * y espacios en blanco 
	public static int[][] piso = new int[20][20];
	public static String[][] pisoI = new String[20][20];
	public static String[] comandos = new String[21];
	
	public static int estadoBoligrafo = 1;
	
	//Estas var son para determinar cuanto espacio queda de acuerdo a la posicion actual
	//La posicion incial es 0,0
	public static int filArriba = 0;
	public static int filAbajo = 19;
	public static int colDer = 19;
	public static int colIzq = 0;
	
	//Orientacion por defecto hacia donde se va a mover 
	public static Estado movimiento = Estado.DERECHA;
	
	//Var para determinar la pos actual
	public static int filAct = 0;
	public static int colAct = 0;
	
	//Se crea como var de clase para que pueda ser accesada desde cualquier metodo, esto es para el 6 en especial
	public static boolean valLong = false;
	
	public static void main(String[] args) {
		
		piso[0][0] = 1; //Donde se inicia el recorrido
		
		Scanner input = new Scanner(System.in);
		
		System.out.printf("%s%15s%n%s%26s%n%s%25s%n%s%30s%n%s%32s%n%s%42s%n%s%53s%n%s%26s%n",
						  "Comando", "Significado",
						  "1", "Boligrafo arriba",
						  "2", "Boligrafo abajo",
						  "3", "Voltear a la derecha",
						  "4", "Voltear a la izquierda",
						  "5,n", "Avanzar hacia delante 'n' espacios",
						  "6", "Mostrar en pantalla el arreglo de 20 por 20",
						  "9", "Fin de los datos");
		
		System.out.println("\nIngrese los comandos: (9 para finalizar)");
		
		int comando = 0;
		String entrada;
		int entradaInt = 0;
		
		while(entradaInt != 9) {
			
			System.out.print("----> ");
			entrada = input.nextLine();
			
			if(validarEntrada(entrada) == true) {
				entradaInt = Integer.parseInt(entrada.substring(0, 1));
				comandos[comando] = entrada;
				comando++;
			}
			
			if(comando == 20 && entradaInt != 9) {
				System.out.println("INSTRUCCIONES MAXIMAS");
				break;
			}
				
		}//Fin de while
		
		//Despues de realizar las eval se regresa el valor predeterminado para no generar errores al asignar
		movimiento = Estado.DERECHA;
		
		ejecutarComandos();
		
	}//Fin de main
	
	public static void ejecutarComandos() {
		
		//Se ejecutan los comandos almacenados en el arreglo
		for(int i = 0; i < comandos.length; i++) {
			
			if(comandos[i] == null) {
				break;
			}
			
			int comando = Integer.parseInt(comandos[i].substring(0, 1));
			
			switch(comando) {
			
			case 3:
				voltearDer();
				break;
			case 4:
				voltearIzq();
				break;
			case 5:
				if(comandos[i].length() == 4)
					avanzar(Integer.parseInt(comandos[i].substring(2, 4)));
				else
					avanzar(Integer.parseInt(comandos[i].substring(2, 3)));
				break;
			case 6:
				imprimir();
				break;
			default:
				break;
			}//Fin de switch
			
		}//Fin de for
		
	}//Fin de metodo ejecutarComandos()
	
	public static void imprimir() {
		
		System.out.println("R  E  C  O  R  R  I  D  O");
		
		for(int i = 0; i < piso.length; i++) {
			for(int j = 0; j < piso[i].length; j++) {
				if(piso[i][j] == 0) {
					pisoI[i][j] = " ";
					System.out.printf("%s ", pisoI[i][j]);
				}
				else {
					pisoI[i][j] = "*";
					System.out.printf("%s ", pisoI[i][j]);
				}
			}
			System.out.println();
		}
		
	}
	
	public static void avanzar(int mov) {
		
		int colDesp;
		int filDesp;
		
		switch(movimiento) {
		
		case DERECHA:
			colDesp = colAct + mov;
			for(int fil = filAct; fil == filAct; fil++) {
				for(int col = colAct + 1; col <= colDesp; col++) { //El +1 es para que no se vuelva asignar 1
					piso[fil][col] = 1; // a la misma posicion desde la cual se inicia, se puede o no poner
				}
			}
			colAct += mov;
			break;
			
		case IZQUIERDA:
			colDesp = colAct - mov;
			for(int fil = filAct; fil == filAct; fil++) {
				for(int col = colAct - 1; col >= colDesp; col--) {
					piso[fil][col] = 1;
				}
			}
			colAct -= mov;
			break;
			
		case ARRIBA:
			filDesp = filAct - mov;
			for(int fil = filAct - 1; fil >= filDesp; fil--) {
				for(int col = colAct; col == colAct; col++) {
					piso[fil][col] = 1;
				}
			}
			filAct -= mov;
			break;
			
		case ABAJO:
			filDesp = filAct + mov;
			for(int fil = filAct + 1; fil <= filDesp; fil++) {
				for(int col = colAct; col == colAct; col++) {
					piso[fil][col] = 1;
				}
			}
			filAct += mov;
			break;
		
		}
		
	}
	
	public static void voltearIzq() {
		
		switch(movimiento) {
		
		case DERECHA:
			movimiento = Estado.ARRIBA;
			break;
		case ARRIBA:
			movimiento = Estado.IZQUIERDA;
			break;
		case IZQUIERDA:
			movimiento = Estado.ABAJO;
			break;
		case ABAJO:
			movimiento = Estado.DERECHA;
			break;
		
		}
		
	}
	
	public static void voltearDer() {
		
		switch(movimiento) {
		
		case DERECHA:
			movimiento = Estado.ABAJO;
			break;
		case ABAJO:
			movimiento = Estado.IZQUIERDA;
			break;
		case IZQUIERDA:
			movimiento = Estado.ARRIBA;
			break;
		case ARRIBA:
			movimiento = Estado.DERECHA;
			break;
		
		}
		
	}//Fin de metodo voltearDer()
	
	public static boolean estBoligrafo(int n) {
		
		if(n == 1 || n == 2)
			estadoBoligrafo = n;
		
		if(estadoBoligrafo == 2)
			return true;
		else if(n == 9)
			return true;
		else if(n == 6) {
			valLong = true;
			return true;
		}
		else
			return false;
			
	}
	
	public static boolean validarEntrada(String input) {
		
		boolean boli = false;
		boolean val1 = false;
		boolean val9 = false;
		
		boolean valComa = false;
		boolean val5 = false;
		boolean valR = false;
		
		//Se llama al metodo para ev el estado del boligrafo, o si se ingreso el 9; 2 == true, 1 y 9 == false
		if(estBoligrafo(Integer.parseInt(input.substring(0, 1))) == true)
			boli = true;
		else
			System.out.println("CAMBIE EL BOLIGRAFO A 2, O ESCRIBA 6 y/o 9 PARA IMPRIMIR Y TERMINAR");
		
		//Si el boligrafo no esta en 1 (false) se evalua q la long del val ing sea valida, de 1 a 4 caracteres
		if(boli == true)
			if(input.length() > 0 && input.length() <= 4)
				val1 = true;
		
		//Evalua si el valor ingresado es o no 9, si se ingreso sera true
		if(val1 == true)
			if(input.length() == 1 && Integer.parseInt(input) == 9) {
				System.out.println("FIN DE LOS DATOS\n");
				val9 = true;
			}
		
		//Si no se ingreso el 9(false) y el bligrafo esta en 2(true), se continua con la evaluacion
		if(val9 == false && boli == true) {
			
			//Se evalua que de ingresarse mas de un caracter el segundo sea coma, y cumpla con el formato
			if(input.length() == 3 || input.length() == 4) {
				if(val9 == false)
					if(input.charAt(1) == ',')
						valComa = true; //Esta asignacion sirv para determianr q el comando cumpla con el formato
					else
						System.out.println("COMANDO ERRONEO HOMMS");
			}
			//Evalua los comandos de tamaño 1, y si estos cumplen con la logica de lo que se ingreso antes
			else if(input.length() == 1) { 
				
				//Evalua que los comandos esten dentro del rango de 1 a 6, recordar que si es 9 se evaluo antes
				if(Integer.parseInt(input.substring(0, 1)) > 0 && Integer.parseInt(input.substring(0, 1)) <= 6){
					
					//Si el val entra dentro del rango, dependiendo el valor se asignan dist ev para dev true 
					switch(Integer.parseInt(input.substring(0, 1))) {
					
					//Vuelta a la derecha
					case 3:
						if(movimiento == Estado.DERECHA) {
							if(filAbajo > 0) {
								movimiento = Estado.ABAJO;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE INFERIROR DEL PISO");
						}
						else if(movimiento == Estado.ABAJO) {
							if(colIzq > 0) {
								movimiento = Estado.IZQUIERDA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE LATERAL DEL PISO");
						}
						else if(movimiento == Estado.IZQUIERDA) {
							if(filArriba > 0) {
								movimiento = Estado.ARRIBA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE SUPERIOR DEL PISO");
						}
						else if(movimiento == Estado.ARRIBA) {
							if(colDer > 0) {
								movimiento = Estado.DERECHA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE LATERAL DEL PISO");
						}
						break;
					
					//Vuelta a la Izquierda	
					case 4:
						if(movimiento == Estado.DERECHA) {
							if(filArriba > 0) {
								movimiento = Estado.ARRIBA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE SUPERIOR DEL PISO");
						}
						else if(movimiento == Estado.ARRIBA) {
							if(colIzq > 0) {
								movimiento = Estado.IZQUIERDA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE LATERAL DEL PISO");
						}
						else if(movimiento == Estado.IZQUIERDA) {
							if(filAbajo > 0) {
								movimiento = Estado.ABAJO;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE INFERIOR DEL PISO");
						}
						else if(movimiento == Estado.ABAJO) {
							if(colDer > 0) {
								movimiento = Estado.DERECHA;
								valLong = true;
							}
							else
								System.out.println("ERROR: LLEGO AL LIMITE LATERAL DEL PISO");
						}
						break;
						
					case 5:
						System.out.println("ERROR: FALTAN LOS ESPACIOS A DESPLAZAR");
						break;
					default:
						valLong = true;
						break;
					
					}//Fin de switch
					
				}
				else
					System.out.println("COMANDO ERRONEO IMBECILL");
					
			}
			else {
				System.out.println("TE EXCEDISTE");
			}
		}
		
		if(valComa == true) {
			//Se evalua que de ser mas de un caracter, el 1ro sea forzosamente 5 para cumplir con el formato
			if(input.length() == 4 && Integer.parseInt(input.substring(0, 1)) == 5)
				val5 = true;
			else if(input.length() == 3 && Integer.parseInt(input.substring(0, 1)) == 5)
				val5 = true;
			else
				System.out.println("comando erroneo putinn");
		}
		
		//Se ev al ser 4 valores q los ultimos dos entren en el parametro correcto (el 1er y 2do ya fueron ev)
		if(val5 == true && input.length() == 4) { //Es menor a 20 x q el cursor inicia sobre una posicion
			if(Integer.parseInt(input.substring(2, 4)) > 0 && Integer.parseInt(input.substring(2, 4)) < 20) {
				
				int espMov = Integer.parseInt(input.substring(2, 4));
				
				switch(movimiento) {
				
				case DERECHA:
					if(colDer >= espMov) {
						colDer -= espMov;
						colIzq += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + colDer + " espacios");
					break;
					
				case IZQUIERDA:
					if(colIzq >= espMov) {
						colIzq -= espMov;
						colDer += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + colIzq + " espacios");
					break;
					
				case ARRIBA:
					if(filArriba >= espMov) {
						filArriba -= espMov;
						filAbajo += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + filArriba + " espacios");
					break;
					
				case ABAJO:
					if(filAbajo >= espMov) {
						filAbajo -= espMov;
						filArriba += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + filAbajo + " espacios");
					break;
				
				}//Fin de switch
				
			}
			else
				System.out.println("estas bien wey");
		}
		//Se ev q de ser 3 valores, los ultimos dos entren en el parametro correcto (el 1er y 2do ya fueron ev)
		else if(val5 == true && input.length() == 3) {
			if(Integer.parseInt(input.substring(2, 3)) > 0 && Integer.parseInt(input.substring(2, 3)) < 20) {
				
				int espMov = Integer.parseInt(input.substring(2, 3));
				
				switch(movimiento) {
				
				case DERECHA:
					if(colDer >= espMov) {
						colDer -= espMov;
						colIzq += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + colDer + " espacios");
					break;
					
				case IZQUIERDA:
					if(colIzq >= espMov) {
						colIzq -= espMov;
						colDer += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + colIzq + " espacios");
					break;
					
				case ARRIBA:
					if(filArriba >= espMov) {
						filArriba -= espMov;
						filAbajo += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + filArriba + " espacios");
					break;
					
				case ABAJO:
					if(filAbajo >= espMov) {
						filAbajo -= espMov;
						filArriba += espMov;
						valR = true;
					}
					else
						System.out.println("ESPACIO INSUFICIENTE: ingrese maximo " + filAbajo + " espacios");
					break;
				
				}//Fin de switch
				
			}
			else
				System.out.println("estas bien wey");
		}
		
		if(val9 == true)
			return true;
		else if(valLong == true || valR == true)
			return true;
		else
			return false;
		
	}


}//Fin declaracion de clase