Descripción del programa:

El Conversor de Monedas es una aplicación de consola desarrollada en Java que permite convertir cantidades de una moneda a otra. 

El programa utiliza una API externa (exchangerate-api.com) para obtener las tasas de cambio actualizadas entre diferentes monedas.

Además, cuenta con una funcionalidad para ver el historial de conversiones realizadas anteriormente.

El usuario puede seleccionar entre varias opciones en el menú principal:
1) Convertir monedas.

2) Ver historial de conversiones.

3) Salir del programa. 

Al elegir la opción 1, se desplega un menu con el codigo de las monedas, nombre de la moneda y pais de origen, seguido se le solicita al usuario ingresar la cantidad a convertir, el código de la moneda de origen y el código de la moneda de destino. 

El programa realiza la conversión utilizando las tasas de cambio obtenidas de la API y muestra el resultado. 

Cada conversión realizada se guarda automáticamente en el historial y a la vez se crea un archivo JSON (historial.json), en el cual se graba mediante texto el historial de las conversiones.

![](C:\Users\Asus\Desktop\Conversor-De-Monedas\Screeshots\Menu.png)
![](C:\Users\Asus\Desktop\Conversor-De-Monedas\Screeshots\Ingreso.png)

Si el usuario selecciona la opción 2, se muestra el historial completo de todas las conversiones realizadas previamente, incluyendo la fecha y hora, la cantidad original, la moneda de origen, el resultado y la moneda de destino.
![](C:\Users\Asus\Desktop\Conversor-De-Monedas\Screeshots\Historial.png)

Si el usuario selecciona la opción 3, el programa termina su ejecución y se cierra.