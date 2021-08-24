Andrea, una estudiante de primer semestre de Nutrición quiere calcular el nivel de riesgo que tienen las personas en su Universidad de sufrir problemas del corazón. Una persona puede estar en riesgo de sufrir estas enfermedades dependiendo de su edad y su índice de masa corporal (IMC). La siguiente tabla presenta los diferentes niveles de riesgo.

            Edad ≤ 45	    Edad > 45
IMC < 22	RIESGO BAJO	    RIESGO MEDIO
IMC ≥ 22	RIESGO MEDIO	RIESGO ALTO


Para calcular el índice de masa corporal (IMC) calculamos el cociente entre el peso del individuo en kilos y el cuadrado de su alturaen metros.

Debido a que se requiere analizar la información de varias personas, la mejor alternativa es construir un programa que realice los cálculos de forma automática. Andrea le pide ayuda a su hermana que es ingeniera de software y entre ambas logran definir los requerimientos del programa utilizando las siguientes historias de usuario.


Hisotoria de usuario Nro.	1
Título:	Ingresar valores de masa, altura y edad.
Descripción	
COMO:	Usuario.
QUIERO:	Ingresar los valores de masa, altura y edad.
PARA:	Poder calcular el IMC.
Criterios de aceptación	
Los valores de masa deben ingresarse en kilogramos.
Los valores de altura deben ingresarse en metros.
Los valores de edad deben ingresarse en años.
Los valores de peso, altura y edad deben ingresarse en una sola línea separada por un espacio.


Hisotoria de usuario Nro.	2
Título:	Calcular IMC y el nivel de riesgo.
Descripción	
COMO:	Usuario.
QUIERO:	Poder calcular el IMCde los valores de peso, altura y edad disponibles.
PARA:	Poder conoce el riesgo de una persona.
Criterios de aceptación	
El IMC debe imprimirse con un número decimal.
Los valores de masa permitidos están entre 0 y 150.
Los valores de altura permitidos están entre 0.1 y 2.5.
Los valores de años permitidos están entre 0 y 110.
Si alguno de los valores está fuera del rango permitido se debe imprimir un mensaje de error.


Usted es contratado por camilo para construir un programa en Java que cumpla las funcionalidades requeridas por Camilo teniendo como referencia las historias de usuario presentadas previamente.

------
Entrada:
------
Cada caso de prueba estará formado por una línea formada por 3 valores separados por un espacio:

La masa de la persona en kilogramos.
La altura de la persona en metros.
La edad de la persona en Años.

------
Salida:
------
El programa imprimirá una línea con dos valores:

El IMC calculado con un número decimal.
El nivel de riesgo actual.
En caso de ingresar algún valor de masa, altura o edad fuera de los rangos permitidos se debe imprimir la palabra 'ERROR'.