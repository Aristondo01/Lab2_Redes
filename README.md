# Laboratorio 2 parte 2
***
## Clonar el repositorio
git clone https://github.com/Aristondo01/Lab2_Redes.git3

Acá es necesario irse a la branch `parte2` para poder ver el modelo de capas y ejecutarlo.

## Ejecución del emisor en java

Se utilizó un socket para poder enviar los mensajes. Para poder compilar y ejecutarlo es necesario correr en la terminar los siguientes comandos:  
$ javac *.java  
$ java EmisorTransmision  

Esto compilará todos los programas de java y ejecutará la capa de transmisión que es la entrada a todo el modelo.
Se deberá ingresar la probabilidad de error de bytes y el método que se desea usar para la detección de errores, el cual puede ser CRC o Hamming.

## Ejecución del receptor en python
Acá solo es necesario ejecutar el siguiente comando:
$ python ReceptorTransmision.py.

Será solicitado el método que se desea usar para la detección de errores, el cual puede ser CRC o Hamming.

Es importante mencionar que en la última versión del código no se mandan los 10k mensajes, si no que solo se solicita un mensaje que se desea enviar y se envía por medio del socket, obviamente haciendo todo el proceso correspondiente a todo el algoritmo seleccionado.
