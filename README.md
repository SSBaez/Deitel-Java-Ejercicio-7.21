# Deitel-Java-Ejercicio-7.21
Como Programar en Java 10 Edicion Paul Deitel. Ejercicio 7.21 (Gráficos de tortuga)

El lenguaje Logo hizo famoso el concepto de los gráficos de tortuga. Imagine a una tortuga mecánica que camina por todo el cuarto, bajo el control de una aplicación en Java. La tortuga sostiene un bolígrafo en una de dos posiciones, arriba o abajo. Mientras el bolígrafo está abajo, el animalito va trazando figuras a medida que se va moviendo, y mientras el bolígrafo está arriba, la tortuga se mueve alrededor libremente, sin trazar nada. En este problema usted simulará la operación de la tortuga y creará un bloc de dibujo computarizado.

Utilice un arreglo de 20 por 20 llamado piso, que se inicialice con ceros. Lea los comandos de un arreglo que los contenga. Lleve el registro de la posición actual de la tortuga en todo momento, y si el bolígrafo se encuentra arriba o abajo. Suponga que la tortuga siempre empieza en la posición (0, 0) del piso, con su bolígrafo hacia arriba. El conjunto de comandos de la tortuga que su aplicación debe procesar se muestra en la figura 7.29

Comando ///////// Significado

1 ///////// Bolígrafo arriba

2 ///////// Bolígrafo abajo

3 ///////// Voltear a la derecha

4 ///////// Voltear a la izquierda

5,n ///////// Avanzar hacia delante 10 espacios (reemplace el 10 por un numero distinto de espacios)

6 ///////// Mostrar en pantalla el arreglo de 20 por 20

9 ///////// Fin de los datos (centinela)

Suponga que la tortuga se encuentra en algún punto cerca del centro del piso. El siguiente “programa” dibuja e imprime un cuadrado de 12 por 12, dejando el bolígrafo en posición levantada:

2

5,12

3

5,12

3

5,12

3

5,12

1

6

9

A medida que la tortuga se vaya desplazando con el bolígrafo hacia abajo, asigne 1 a los elementos apropiados del arreglo piso. Cuando se dé el comando 6 (mostrar el arreglo en pantalla), siempre que haya un 1 en el arreglo, muestre un asterisco o cualquier carácter que usted elija. Siempre que haya un 0, muestre un carácter en blanco.

Escriba una aplicación para implementar las herramientas de gráficos de tortuga aquí descritas. Escriba varios programas de gráficos de tortuga para dibujar figuras interesantes. Agregue otros comandos para incrementar el poder de su lenguaje de gráficos de tortuga.
