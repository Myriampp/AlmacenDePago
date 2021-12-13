
/*...............................................................................
CLASE AlmacenPago
...............................................................................*/

package almacenPago;

public class AlmacenPago {

private double montoAinvertir;
private double interesAnual;

public double getMontoAinvertir() {
return montoAinvertir;
}
public void setMontoAinvertir(double montoAinvertir) {
if(esPositivo(montoAinvertir))
this.montoAinvertir = montoAinvertir;
}

public double getInteresAnual() {
return interesAnual;
}
public void setInteresAnual(double interesAnual) {
if(!esPositivo(interesAnual) || interesAnual > 1)
throw new Error("El Interés Ingresado NO ES VÁLIDO, debe ser escrito en el siguiente formato > 0.xx");
this.interesAnual = interesAnual;
}

public AlmacenPago(double montoAinvertir, double interesAnual) {
setMontoAinvertir(montoAinvertir);
setInteresAnual(interesAnual);			
}

public double calcularInversion(int cantidadMaximaDeAnios) {
if (esPositivo(cantidadMaximaDeAnios)) {
for(int i = 1; i <= cantidadMaximaDeAnios; i++) {
setMontoAinvertir(getMontoAinvertir() * (getInteresAnual() + 1));
if (i != cantidadMaximaDeAnios)
	System.out.println(i + "° Año > " + redondearDouble(getMontoAinvertir()));
}
System.out.print(cantidadMaximaDeAnios + "° Año > ");
}
return redondearDouble(getMontoAinvertir());	
}

/**
* EXPLICACIÓN DE LA SIGUIENTE OPERACIÓN
* getMontoAinvertir() * (Math.pow((getInteresAnual()/12) + 1, contadorDeMeses))
* Al Monto Actual se le Multiplica el Interés Actual (en Meses), + 1,
* que simplifica el siguiente formato: (numero * 0.xx) + numero.
* Se transforma en: (numero * 1.xx), elevado por la Cantidad de Meses 
*/

public int consultarCantidadDeMesesEstimados(double saldoFinalDeseado) {
int contadorDeMeses = 0;
if (esPositivo(saldoFinalDeseado)) {
while (getMontoAinvertir() * (Math.pow((getInteresAnual()/12) + 1, contadorDeMeses)) <= saldoFinalDeseado) {				
contadorDeMeses++;
}
}
return contadorDeMeses;
}

// MÉTODOS AUXILIARES .........................
// ............................................

private double redondearDouble(double numero) { // redondea un numero decimal con demasiados dígitos después del punto
return Math.round(numero * 100) / 100d;
}

private boolean esPositivo(double dato) { // verifica que los valores sean mayores que 0
if (dato <= 0) {
throw new Error("El Dato Ingresado NO es positivo, esta acción no puede ejecutarse.");
}
return true;
}

// ............................................
// ............................................

}